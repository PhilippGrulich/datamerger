package de.pg.dm.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Philipp on 23.06.2016.
 */
public class Row {

    public ArrayList<Object[]> values = new ArrayList<>();

    public Row() {

    }

    public void addItem(String column, Object item) {
        values.add(new Object[]{column,item});
    }

    public Object getValue(String column){
        for(Object[] arr : values){
            if(arr[0].equals(column)){
                return arr[1];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Object[] arr : values){
            sb.append(arr[1]).append(", ");
        }
        return sb.toString();
    }
}
