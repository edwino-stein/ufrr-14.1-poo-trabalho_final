package com.correios.edwinos.consultacorreios.util.database;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by EdwinoS on 15/11/14.
 */
public class CorreiosEntity extends Entity {

    public CorreiosEntity(){}

    protected String tableName = "objects";
    protected String[] entityModel = {
            "'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE",
            "'code' TEXT NOT NULL UNIQUE",
            "'name' TEXT",
            "'json_data' TEXT"
    };

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    protected String[] getEntityModelArray() {
        return this.entityModel;
    }

    @Override
    public void setValues(Cursor c){
        this.id = c.getInt(c.getColumnIndex("id"));
        this.code = c.getString(c.getColumnIndex("code"));
        this.json_data = c.getString(c.getColumnIndex("json_data"));
        this.name = c.getString(c.getColumnIndex("name"));
    }

    @Override
    public ContentValues getValues(){
        ContentValues values = new ContentValues();

        if(this.id >= 0){
            values.put("id", this.id);
        }

        values.put("code", this.code);
        values.put("json_data", this.json_data);
        values.put("name", this.name);

        return values;
    }

    protected int id = -1;
    protected String code;
    protected String json_data;
    protected String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJson_data() {
        return json_data;
    }

    public void setJson_data(String json_data) {
        this.json_data = json_data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
