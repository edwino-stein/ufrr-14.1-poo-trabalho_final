package com.correios.edwinos.consultacorreios.util.httprequest;

import org.apache.http.HttpStatus;

/**
 * Created by EdwinoS on 09/11/14.
 */
public class HttpResponse {

    public int statusCode = 0;
    public String responseText;
    public org.apache.http.HttpResponse originalResponse;

    public boolean requestResultOk(){
        return this.statusCode == HttpStatus.SC_OK;
    }

}
