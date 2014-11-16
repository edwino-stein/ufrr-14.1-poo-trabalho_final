package com.correios.edwinos.consultacorreios.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by EdwinoS on 15/11/14.
 */
public class CorreiosDataBase{

    protected SQLiteDatabase dataBase;
    protected String dataBaseName = "correios";
    protected String entity;

    protected String errorMessage;

    public CorreiosDataBase(){}

    public CorreiosDataBase(Context context, String entity){
        this.dataBase = context.openOrCreateDatabase(this.dataBaseName, SQLiteDatabase.CREATE_IF_NECESSARY, null);
        this.entity = entity;
        this.initTable();
    }

    protected boolean tableExistis(String tableName){
        Cursor c = this.dataBase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='"+tableName+"'", null);

        if(c == null){
            return false;
        }

        if(c.getCount() <= 0){
            return  false;
        }

        return true;
    }

    protected void initTable(){
        Entity entity = this.getEntityInstance();

        if(!this.tableExistis(entity.getTableName())){
            this.dataBase.execSQL("CREATE TABLE "+entity.getTableName()+" (" +entity.getEntityModel()+ ")");
        }
    }

    protected Entity getEntityInstance() {

        try {
            Entity e = (Entity) Class.forName(this.entity).newInstance();
            return e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Entity[] fetchAll(){
        Entity entity = this.getEntityInstance();

        if(entity == null){
            return null;
        }

        Cursor queryResult = this.dataBase.rawQuery("SELECT * FROM " + entity.getTableName(), null);

        if(queryResult == null){
            return null;
        }

        Entity[] data = new Entity[queryResult.getCount()];

        if(!queryResult.moveToFirst()){
            return data;
        }

        int i = 0;

        do{
            data[i] = this.getEntityInstance();
            data[i].setValues(queryResult);
            i++;
        }while (queryResult.moveToNext());


        return data;
    }

    public boolean insert(Entity entity){

        try {
            this.dataBase.insertOrThrow(entity.getTableName(), null, entity.getValues());

        }catch (SQLiteConstraintException e){
            e.printStackTrace();
            this.errorMessage = e.getMessage();
            return false;
        }

        return true;
    }

    public Entity[] select(String where){
        Cursor queryResult = this.dataBase.rawQuery("SELECT * FROM " + this.getEntityInstance().getTableName() + " WHERE " + where, null);

        if(queryResult == null){
            return null;
        }

        Entity[] data = new Entity[queryResult.getCount()];

        if(!queryResult.moveToFirst()){
            return data;
        }

        int i = 0;

        do{
            data[i] = this.getEntityInstance();
            data[i].setValues(queryResult);
            i++;
        }while (queryResult.moveToNext());


        return data;
    }

    public boolean delete(String where){

        if(this.dataBase.delete(this.getEntityInstance().getTableName(), where, null) >= 1){
            return true;
        }

        return false;
    }

    public boolean update(Entity entity, String where){

        if(this.dataBase.update(this.getEntityInstance().getTableName(), entity.getValues(), where, null) >= 1){
            return true;
        }

        return false;
    }

    public void clearAll(){
        this.dataBase.execSQL("DROP TABLE "+this.getEntityInstance().getTableName());
        this.initTable();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
