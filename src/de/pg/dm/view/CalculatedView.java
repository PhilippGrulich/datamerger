package de.pg.dm.view;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by Philipp on 26.06.2016.
 */
public class CalculatedView extends AbstractView {

    private View baseView;
    private String columnName;
    private final Function<Row, Object> function;

    public CalculatedView(View baseView, String columnName, Function<Row, Object> function) {
        this.baseView = baseView;
        this.columnName = columnName;
        this.function = function;
        assert baseView.column(columnName) == null;
        this.columns.addAll(baseView.getColumns());
        this.columns.add(new ColumnImpl(columnName));
    }

    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {
            Iterator<Row> baseIterrator = baseView.iterator();
            @Override
            public boolean hasNext() {
                return baseIterrator.hasNext();
            }

            @Override
            public Row next() {
                Row row = baseIterrator.next();
                row.addItem(columnName,function.apply(row));
                return row;
            }
        };
    }
}
