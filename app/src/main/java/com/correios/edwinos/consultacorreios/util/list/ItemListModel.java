package com.correios.edwinos.consultacorreios.util.list;

import com.correios.edwinos.consultacorreios.util.database.CorreiosEntity;

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


    public ItemListModel(String code, String frendlyName) {
        this.code = code;
        this.frendlyName = frendlyName.isEmpty() ? code : frendlyName;
    }

    public ItemListModel(CorreiosEntity entity) {
        this(entity.getCode(), entity.getName());

        this.id = entity.getId();
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
}
