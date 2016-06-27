package de.pg.dm.test;

import de.pg.dm.exporter.CSVOutput;
import de.pg.dm.importer.CSVImporter;
import de.pg.dm.view.Row;
import de.pg.dm.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Created by Philipp on 23.06.2016.
 */
public class DBtest {

    public static void main(String[] args) {
        View csvView_q1 = new CSVImporter("C:\\Users\\Philipp\\Desktop\\AMI Datasets\\Bike\\2012-Q1-cabi-trip-history-data.csv", ",");
        View csvView_q2 = new CSVImporter("C:\\Users\\Philipp\\Desktop\\AMI Datasets\\Bike\\2012-Q2-cabi-trip-history-data.csv", ",");
        View csvView_q3 = new CSVImporter("C:\\Users\\Philipp\\Desktop\\AMI Datasets\\Bike\\2012-Q3-cabi-trip-history-data.csv", ",");
        View csvView_q4 = new CSVImporter("C:\\Users\\Philipp\\Desktop\\AMI Datasets\\Bike\\2012-Q4-cabi-trip-history-data.csv", ",");
        View weather = new CSVImporter("C:\\Users\\Philipp\\Desktop\\AMI Datasets\\Weather DC.txt", ",");
        csvView_q1.explane();
        csvView_q2.explane();
        csvView_q3.explane();
        csvView_q4.explane();
        weather.explane();

        View compleatCSV = csvView_q1.append(csvView_q2, csvView_q3, csvView_q4);
        compleatCSV.explane();


        compleatCSV = compleatCSV.select("Duration", "Start date", "End date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:m");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-M-d");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        compleatCSV = compleatCSV.addCalculatedColumn("new date", row -> {

            TemporalAccessor date = formatter.parse((String) row.getValue("Start date"));
            return outputFormatter.format(date);

        });

        weather = weather.addCalculatedColumn("EST new date", row -> {

            TemporalAccessor date = formatter2.parse((String) row.getValue("EST"));
            return outputFormatter.format(date);

        });

        View joinedData = compleatCSV.join(weather, "new date", "EST new date");


        joinedData.explane();


        //for (Row r : joinedData)
        //    System.out.println(r);
        new CSVOutput("output.csv").write(joinedData);


    }
}
