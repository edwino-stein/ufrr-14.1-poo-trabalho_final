package com.correios.edwinos.consultacorreios.util.database;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by EdwinoS on 15/11/14.
 */
public abstract class Entity {

    public abstract String getTableName();
    protected abstract String[] getEntityModelArray();
    public abstract void setValues(Cursor c);
    public abstract ContentValues getValues();

    public String getEntityModel(){

        String model = "";
        String[] entityModel = this.getEntityModelArray();

        for(int i = 0; i < entityModel.length; i++){
            model += entityModel[i];

            if(i < entityModel.length - 1){
                model += ", ";
            }
        }

        return model;
    }
}
