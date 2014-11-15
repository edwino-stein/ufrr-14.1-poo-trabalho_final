package com.correios.edwinos.consultacorreios.util.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by EdwinoS on 15/11/14.
 */
public class JsonParser {
    protected boolean errorFlag = false;
    protected String errorMessage = "";

    protected boolean success = false;
    protected int total = -1;
    protected CorreiosData[] data;
    protected String responseMessage = "";


    public JsonParser(JSONObject jsonObj){
        this.setSuccess(jsonObj)
            .setTotal(jsonObj)
            .setData(jsonObj);
    }

    public JsonParser(String json){

        JSONObject jsonObj = null;

        try {
            jsonObj = new JSONObject(json);

        } catch (JSONException e) {
            e.printStackTrace();
            this.errorFlag = true;
            this.errorMessage = "Parser error";
        }

        this.setSuccess(jsonObj)
            .setTotal(jsonObj)
            .setData(jsonObj);
    }

    protected JsonParser setSuccess(JSONObject jsonObj){

        try {
            this.success = jsonObj.getBoolean("success");

            if(!this.success){
                this.responseMessage = jsonObj.getString("message");
            }

        } catch (JSONException e) {
            e.printStackTrace();
            this.errorFlag = true;
            this.errorMessage = "Parser error";
            this.success = false;
        }

        return this;
    }

    public JsonParser setTotal(JSONObject jsonObj){

        try {
            this.total = jsonObj.getInt("total");
        } catch (JSONException e) {
            e.printStackTrace();
            this.errorFlag = true;
            this.errorMessage = "Parser error";
            this.total = -1;
        }

        return this;
    }

    public JsonParser setData(JSONObject jsonObj){

        JSONArray dataArray = null;

        try {
            dataArray = jsonObj.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
            this.errorFlag = true;
            this.errorMessage = "Parser error";
        }

        if(!this.errorFlag) {
            this.data = new CorreiosData[this.total];

            for (int i = 0; i < this.data.length; i++) {
                try {
                    this.data[i] = new CorreiosData((JSONObject) dataArray.get(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.errorFlag = true;
                    this.errorMessage = "Parser error";
                }
            }
        }

        return this;
    }


    public boolean isSuccess() {
        return success;
    }

    public int getTotal() {
        return total;
    }

    public CorreiosData[] getData() {
        return data;
    }

    public CorreiosData getData(int index) {

        CorreiosData data = null;

        try {
            data = this.data[index];
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public String getMessage() {
        return responseMessage;
    }
}
