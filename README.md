# Datamerger
This project provides a data processing framework, that supports various data-pre processing tasks.
It was created to merge, filter or join text files before they are used for further analysis in tools like tableau.

The main purpose of this project is to provide a simple and fast api.

At the moment it supports only CSV files and the following operrations.

#### Load a CSV
```java
View csvView = new CSVImporter(Path, Separator);
```

#### explain / print columns
```java
csvView.explain();
```

#### append multiple CSVs
```java
View completeCSV = csvView.append(csvView2, csvView3, csvView4);`
```

#### select columns by name
```java
View subView = csvView.select("Duration", "Start date", "End date");
```

#### select columns by name
```java
View subView = csvView.select("Duration", "Start date", "End date");
```

#### add a calculated columns
```java
View newView = csvView.addCalculatedColumn("abs", row -> 
    {
        int inputNumber = (int) row.getValue("number"));
        return Math.abs(input);
    }
);
```

#### join
```java
View joinedData = leftView.join(rightView, on_left, on_right);
```


