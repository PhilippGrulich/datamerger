package de.pg.dm.view;

import java.util.Iterator;

/**
 * Created by Philipp on 23.06.2016.
 */
public class CombinedView extends AbstractView {

    private final View[] views;

    public CombinedView(View... views) {
        this.views = views;
        for (View view : this.views) {
            for (Column column : view.getColumns()) {
                if (this.column(column.getName()) == null) { //Check if Column not exist
                    this.columns.add(column);
                }
            }
        }
    }

    @Override
    public Iterator<Row> iterator() {

        return new Iterator<Row>() {
            int iteratorNr = 0;
            Iterator<Row> iterator = views[iteratorNr].iterator();

            @Override
            public boolean hasNext() {
                if (!iterator.hasNext() && views.length-1 > iteratorNr) {
                    iteratorNr++;
                    iterator = views[iteratorNr].iterator();
                }

                return iterator.hasNext();
            }

            @Override
            public Row next() {
                Row viewRow = iterator.next();
                return generateRow(viewRow);
            }

            private Row generateRow(Row row) {
                View view = views[iteratorNr];
                Row combinedRow = new Row();
                for (Column c : columns) {
                    if (view.column(c.getName()) != null) {
                        combinedRow.addItem(c.getName(), row.getValue(c.getName()));
                    } else
                        combinedRow.addItem(c.getName(), null);
                }
                return combinedRow;
            }
        };
    }
}
