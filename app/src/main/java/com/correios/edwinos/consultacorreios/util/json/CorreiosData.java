package com.correios.edwinos.consultacorreios.util.json;

import com.correios.edwinos.consultacorreios.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by EdwinoS on 15/11/14.
 */
public class CorreiosData {

    protected int id;
    protected Date data;
    protected String local;
    protected String situacao;
    protected int estado;
    protected String  detalhes;

    public CorreiosData(boolean emptyValues){
        if(emptyValues){
            this.id = -1;
            this.estado = -1;
            this.situacao = "Aguardando postagem...";
            this.detalhes = "A postagem leva em média um dia útil para ser registrado no sistema dos correios.\nOBS.: Verifique se o código está correto.";
        }
    }

    public CorreiosData(JSONObject data){

        Iterator<String> keys = data.keys();

        while (keys.hasNext()){
            String currentKey = keys.next();
            try {
                this.set(currentKey, data.get(currentKey));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected void set(String key, Object value) {

        try {
            Method method = this.getClass().getDeclaredMethod(this.getMethodName("set", key), Object.class);
            method.invoke(this, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    protected String getMethodName(String prefix, String key){
        return prefix + Character.toUpperCase(key.charAt(0)) + key.substring(1);
    }

    public void setId(Object id) {
        Integer i = (Integer) id;
        this.id = i.intValue();
    }

    public void setData(Object data){

        JSONObject jsonObj = (JSONObject) data;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            this.data = format.parse(jsonObj.getString("date"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setLocal(Object local) {
        this.local = (String) local;
    }

    public void setSituacao(Object situacao) {
        this.situacao = (String) situacao;
    }

    public void setDetalhes(Object detalhes) {
        this.detalhes = (String) detalhes;
    }

    public void setEstado(Object estado) {
        Integer i = (Integer) estado;
        this.estado = i.intValue();
    }

    /**
     * Getters
     */

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public String getSituacao() {
        return situacao;
    }

    public int getEstado() {
        return estado;
    }

    public String getDetalhes() {
        return detalhes;
    }

    @Override
    public String toString(){
        return "id: "+this.id+"; data: "+this.data +"; local: "+this.local+"; situacao: "+this.situacao;
    }

    public int getThumbStatusRes(){
        int drawableId = R.drawable.icon_waiting_status;

        switch (this.estado){
            case 0: drawableId = R.drawable.icon_info_status; break;
            case 1: drawableId = R.drawable.icon_success_status; break;
            case 2: drawableId = R.drawable.icon_warning_status; break;
            case 3: drawableId = R.drawable.icon_error_status; break;
        }

        return drawableId;
    }
}
