package de.pg.dm.view;


import java.util.function.Function;

/**
 * Created by Philipp on 23.06.2016.
 */
public interface Column<DataType> {


    Column map(Function<Column<DataType>,Column> mapFunction);

    String getName();


}
