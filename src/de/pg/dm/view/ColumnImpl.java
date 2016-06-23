package de.pg.dm.view;

import javax.xml.crypto.Data;
import java.util.function.Function;

/**
 * Created by Philipp on 23.06.2016.
 */
public class ColumnImpl<DataType> implements Column<DataType> {

    String name;
    public ColumnImpl(String columnName){
        this.name = columnName;
    }

    @Override
    public Column map(Function<Column<DataType>, Column> mapFunction) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "'"+name+"'";
    }
}
