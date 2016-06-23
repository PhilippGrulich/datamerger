package de.pg.dm.view;

import java.util.ArrayList;

/**
 * Created by Philipp on 23.06.2016.
 */
public class Row {

    public ArrayList<Object> values = new ArrayList<>();

    public Row(){

    }

    public void addItem(Object item){
        values.add(item);
    }


    @Override
    public String toString() {
        return values.toString()+"\n";
    }
}
