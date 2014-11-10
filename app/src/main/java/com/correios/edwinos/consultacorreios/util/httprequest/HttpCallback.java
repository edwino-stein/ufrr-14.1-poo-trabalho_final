package com.correios.edwinos.consultacorreios.util.httprequest;

/**
 * Created by EdwinoS on 09/11/14.
 */
public interface HttpCallback {

    void beforeSendCallback();

    void afterSendCallback();

    void errorCallback(HttpResponse response);

    void successCallback(HttpResponse response);

}
