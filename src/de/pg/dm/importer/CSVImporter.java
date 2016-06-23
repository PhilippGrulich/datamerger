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
    private BufferedReader reader;

    public CSVImporter(String path, String seperator) {
        this.path = path;
        this.seperator = seperator;
        loadCSV();
    }


    private void loadCSV() {

        try {
            reader = new BufferedReader(new FileReader(path));
            readHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHeader() throws IOException {
        String headerline = reader.readLine();
        String[] columnNames = headerline.split(this.seperator);
        for (String columnName : columnNames) {
            super.columns.add(new ColumnImpl<String>(columnName));
        }
    }


    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {


            public String line;

            @Override
            public boolean hasNext() {
                try {
                    line = reader.readLine();
                    return line != null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public Row next() {
                String[] items = line.split(seperator);
                Row inputRow = new Row();
                for (String item : items) {
                    inputRow.addItem(item);
                }
                Row resultRow = CSVImporter.super.apply(inputRow);
                if (resultRow == null && hasNext())
                    return next();
                return resultRow;

            }
        };
    }
}
