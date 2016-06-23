package de.pg.dm.view;

import java.util.function.Function;

/**
 * Created by Philipp on 23.06.2016.
 */
public interface View extends Iterable<Row> {


    Column column(String columnName);
    Column column(int nr);

    View filter(Column column, Function<Object,Boolean> filterFunction);

    View join(View otherView, Column on);

    View append(View otherView);

    String explane();

    View rename(String columnName, String newName);

    View delete(Column column);

    View select(String... columns);


}
