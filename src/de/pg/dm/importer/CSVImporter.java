package de.pg.dm.importer;

import de.pg.dm.view.AbstractView;
import de.pg.dm.view.Column;
import de.pg.dm.view.ColumnImpl;
import de.pg.dm.view.Row;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by Philipp on 23.06.2016.
 */
public class CSVImporter extends AbstractView {

    private final String seperator;
    private String path;


    public CSVImporter(String path, String seperator) {
        this.path = path;
        this.seperator = seperator;
        loadCSV();
    }


    private BufferedReader loadCSV() {

        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(path));
            readHeader(reader);
            return reader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void readHeader(BufferedReader reader) throws IOException {
        String headerline = reader.readLine();
        String[] columnNames = headerline.split(this.seperator);
        for (String columnName : columnNames) {
            super.columns.add(new ColumnImpl<String>(columnName));
        }
    }


    @Override
    public Iterator<Row> iterator() {
        return new CSVIterator();
    }

    private class CSVIterator implements Iterator<Row> {


        String line;
        BufferedReader reader = loadCSV();

        CSVIterator() {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean hasNext() {
            return line != null;
        }

        @Override
        public Row next() {
            String[] items = line.split(seperator);
            Row inputRow = new Row();
            for (int i = 0; i < items.length; i++) {
                inputRow.addItem(column(i).getName(), items[i]);
            }
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputRow;

        }


        ;


    }
}
