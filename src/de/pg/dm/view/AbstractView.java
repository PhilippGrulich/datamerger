package de.pg.dm.view;

import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by Philipp on 23.06.2016.
 */
public abstract class AbstractView implements View {

    protected ArrayList<Column> columns;

    protected AbstractView() {
        columns = new ArrayList<>();
    }

    @Override
    public Column column(String columnName) {
        for (Column c : columns) {
            if (c.getName().equals(columnName))
                return c;
        }
        return null;
    }

    @Override
    public Column column(int nr) {
        return columns.get(nr);
    }

    @Override
    public View filter(Column column, Function<Object, Boolean> filterFunction) {
       return new FilteredView(this,column,filterFunction);
    }

    @Override
    public View join(View otherView, Column on) {
        return null;
    }

    @Override
    public View append(View otherView) {
        return null;
    }

    @Override
    public String explane() {
        System.out.print(columns.toString());
        return columns.toString();
    }

    protected Row apply(Row row) {

        return row;
    }

    @Override
    public View rename(String columnName, String newName) {
        return null;
    }

    @Override
    public View delete(Column column) {
        return null;
    }

    @Override
    public View select(String... columns) {
        return null;
    }
}
