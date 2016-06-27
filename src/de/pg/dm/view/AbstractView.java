package de.pg.dm.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Philipp on 23.06.2016.
 */
public abstract class AbstractView implements View {

    protected List<Column> columns;

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
    public List<Column> getColumns() {
        return columns;
    }


    @Override
    public View filter(Column column, Function<Object, Boolean> filterFunction) {
        return new FilteredView(this, column, filterFunction);
    }

    @Override
    public View join(View otherView, String onLeft, String onRight) {

        return new JoinedView(this,onLeft,otherView,onRight);
    }

    @Override
    public View append(View... otherViews) {
        View[] newViewArray = new View[otherViews.length + 1];
        newViewArray[0] = this;
        System.arraycopy(otherViews, 0, newViewArray, 1, otherViews.length);

        return new CombinedView(newViewArray);
    }

    @Override
    public String explane() {
        System.out.println(columns.toString());
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
    public View select(String... selectedColumns) {

        List<Column> arrayList = Arrays.stream(selectedColumns).map(this::column).collect(Collectors.toList());
        return new SelectView(this, arrayList);
    }

    @Override
    public View addCalculatedColumn(String column, Function<Row, Object> function) {
        return new CalculatedView(this, column, function);
    }

    @Override
    public long count() {
        long count =0;
        for(Row r : this){
            count++;
        }
        return count;
    }
}
