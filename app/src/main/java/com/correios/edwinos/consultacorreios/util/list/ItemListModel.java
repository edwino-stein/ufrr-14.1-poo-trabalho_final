package com.correios.edwinos.consultacorreios.util.list;

/**
 * Created by EdwinoS on 08/11/14.
 */
public class ItemListModel {
    protected String code;
    protected String frendlyName;

    public ItemListModel(String code, String frendlyName) {
        this.code = code;
        this.frendlyName = frendlyName;
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
}
