package de.pg.dm.test;

import de.pg.dm.importer.CSVImporter;
import de.pg.dm.view.Row;
import de.pg.dm.view.View;

/**
 * Created by Philipp on 23.06.2016.
 */
public class DBtest {

    public static void main(String[] args) {
        View csvView = new CSVImporter("C:\\Users\\Philipp\\Desktop\\AMI Datasets\\Bike\\2012-Q1-cabi-trip-history-data.csv", ",");
        csvView.explane();
        View filtered = csvView.filter(csvView.column("Type"), value -> value.equals("Registered"));
        for (Row row : filtered) {
            System.out.print(row);
        }


    }
}
