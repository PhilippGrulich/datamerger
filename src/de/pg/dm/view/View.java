package de.pg.dm.view;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Philipp on 23.06.2016.
 */
public interface View extends Iterable<Row> {


    Column column(String columnName);
    Column column(int nr);

    List<Column> getColumns();

    View filter(Column column, Function<Object,Boolean> filterFunction);

    View join(View otherView, String onLeft, String onRight);

    View append(View... otherViews);

    String explane();

    View rename(String columnName, String newName);

    View delete(Column column);

    View select(String... columns);

    View addCalculatedColumn(String column, Function<Row,Object> function);

    long count();




}
