package com.correios.edwinos.consultacorreios.util.list;

import com.correios.edwinos.consultacorreios.util.database.CorreiosEntity;
import com.correios.edwinos.consultacorreios.util.json.CorreiosData;
import com.correios.edwinos.consultacorreios.util.json.JsonParser;

/**
 * Created by EdwinoS on 08/11/14.
 */

/**
 * TODO: Adicionar ultima situação, hora, data e estado
 */

public class ItemListModel {

    protected int id;

    protected String code;
    protected String frendlyName;
    protected CorreiosData lastData;


    public ItemListModel(String code, String frendlyName) {
        this.code = code;
        this.frendlyName = frendlyName;
    }

    public ItemListModel(CorreiosEntity entity) {
        this(entity.getCode(), entity.getName());

        this.id = entity.getId();

        JsonParser json = new JsonParser(entity.getJson_data());

        if(json.getTotal() > 0) {
            this.lastData = json.getData(0);
        }
        else {
            this.lastData = new CorreiosData(true);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFrendlyName() {
        return frendlyName;
    }

    public void setFrendlyName(String frendlyName) {
        this.frendlyName = frendlyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CorreiosData getLastData(){
        return this.lastData;
    }
}
