package de.pg.dm.view;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by Philipp on 23.06.2016.
 */
public class FilteredView extends AbstractView {
    private final Function<Object, Boolean> filterFunction;
    private final Column column;
    private final AbstractView root;
    private final int columnIndex;

    public FilteredView(AbstractView root, Column column, Function<Object, Boolean> filterFunction) {
        this.root = root;
        this.column = column;
        this.filterFunction = filterFunction;
        this.columnIndex = root.columns.indexOf(column);
    }

    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {
            public Row actualRow;
            Iterator<Row> rootIterrator = root.iterator();


            Row findNextValue(){
                while(rootIterrator.hasNext()){
                    Row next = rootIterrator.next();
                    if (filterFunction.apply(next.values.get(columnIndex)))
                        return next;
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                actualRow = findNextValue();
                return actualRow != null;
            }

            @Override
            public Row next() {
                return actualRow;
            }
        };
    }
}
