package de.pg.dm.view;

import java.util.*;

/**
 * Created by Philipp on 26.06.2016.
 */
public class JoinedView extends AbstractView {

    private View left;
    private String onLeft;
    private View right;
    private String onRight;

    public JoinedView(View left, String onLeft, View right, String onRight) {
        this.left = left;
        this.onLeft = onLeft;
        this.right = right;
        this.onRight = onRight;

        left.getColumns().forEach(x->{
            this.columns.add(new ColumnImpl("left_"+x.getName()));
        });
        right.getColumns().forEach(x->{
            this.columns.add(new ColumnImpl("right_"+x.getName()));
        });

    }

    @Override
    public Iterator<Row> iterator() {
        return new JoinIterator();
    }

    private class JoinIterator implements Iterator<Row> {
        Iterator<Row> leftIterrator = left.iterator();
        Map<Object, List<Row>> hashmap = new HashMap<>();
        Iterator<Row> matchIterrator;
        Row leftRow;

        JoinIterator() {
            for (Row rightRow : right) {
                Object value = rightRow.getValue(onRight);
                if (!hashmap.containsKey(value))
                    hashmap.put(value, new ArrayList<>());
                hashmap.get(value).add(rightRow);
            }
        }

        @Override
        public boolean hasNext() {
            return leftIterrator.hasNext();
        }

        @Override
        public Row next() {
            //Left Join
            Row outputRow = new Row();
            if (leftIterrator.hasNext()) {


                if (matchIterrator == null || !matchIterrator.hasNext()) {
                    leftRow = leftIterrator.next();
                    Object leftValue = leftRow.getValue(onLeft);
                    List<Row> rightRow = hashmap.get(leftValue);
                    if (rightRow == null) {
                        for (Column columns : left.getColumns()) {
                            outputRow.addItem("left_" + columns.getName(), leftRow.getValue(columns.getName()));
                        }
                        for (Column columns : right.getColumns()) {
                            outputRow.addItem("right_" + columns.getName(), null);
                        }
                        return outputRow;
                    } else
                        matchIterrator = rightRow.iterator();
                }

                for (Column columns : left.getColumns()) {
                    outputRow.addItem("left_" + columns.getName(), leftRow.getValue(columns.getName()));
                }

                if (matchIterrator.hasNext()) {
                    Row rightRow = matchIterrator.next();
                    for (Column columns : right.getColumns()) {
                        outputRow.addItem("right_" + columns.getName(), rightRow.getValue(columns.getName()));
                    }
                }
            }

            return outputRow;
        }
    }
}
