package de.pg.dm.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Philipp on 26.06.2016.
 */
public class SelectView extends AbstractView {

    private final View rootView;

    public SelectView(View view, List<Column> columns) {
        this.columns = columns;
        this.rootView = view;

    }

    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {
            Iterator<Row> i = rootView.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public Row next() {
                Row groundRow = i.next();
                Row outputRow = new Row();
                for (Column c : columns)
                    outputRow.addItem(c.getName(), groundRow.getValue(c.getName()));
                return outputRow;
            }
        };
    }
}
