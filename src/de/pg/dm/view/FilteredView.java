package de.pg.dm.view;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by Philipp on 23.06.2016.
 */
public class FilteredView extends AbstractView {
    private final Function<Object, Boolean> filterFunction;
    private final Column filterColumn;
    private final AbstractView root;

    public FilteredView(AbstractView root, Column column, Function<Object, Boolean> filterFunction) {
        this.root = root;
        this.filterColumn = column;
        this.filterFunction = filterFunction;
    }

    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {
            public Row actualRow;
            Iterator<Row> rootIterrator = root.iterator();


            Row findNextValue(){
                while(rootIterrator.hasNext()){
                    Row next = rootIterrator.next();
                    if (filterFunction.apply(next.getValue(filterColumn.getName())))
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
