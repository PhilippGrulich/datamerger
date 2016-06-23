package de.pg.dm.view;

import java.util.Iterator;

/**
 * Created by Philipp on 23.06.2016.
 */
public class CompinedView extends AbstractView {

    private final View[] views;

    CompinedView(View... views){
        this.views = views;
    }

    @Override
    public Iterator<Row> iterator() {

        return new Iterator<Row>() {
            int iterratorNr= 0;
            Iterator<Row> iterrator = views[iterratorNr].iterator();
            @Override
            public boolean hasNext() {
                if(!iterrator.hasNext() && views.length<iterratorNr) {
                    iterratorNr++;
                    iterrator = views[iterratorNr].iterator();
                }
                return iterrator.hasNext();
            }

            @Override
            public Row next() {
                return iterrator.next();
            }
        };
    }
}
