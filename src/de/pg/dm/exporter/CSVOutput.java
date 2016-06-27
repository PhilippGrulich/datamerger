package de.pg.dm.exporter;

import de.pg.dm.view.Column;
import de.pg.dm.view.Row;
import de.pg.dm.view.View;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Created by Philipp on 23.06.2016.
 */
public class CSVOutput {

    private String path;
    public CSVOutput(String path){
        this.path = path;
    }

    public void write(View view){
        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bf = new BufferedWriter(fw);
            for(Column item : view.getColumns())
                bf.write(item.getName()+", ");
                bf.newLine();

            for(Row r : view){
                for(Column item : view.getColumns())
                    bf.write(r.getValue(item.getName())+",");
                bf.newLine();

            }
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
