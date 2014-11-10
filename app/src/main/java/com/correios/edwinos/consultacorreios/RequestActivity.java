package com.correios.edwinos.consultacorreios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.correios.edwinos.consultacorreios.util.httprequest.HttpCallback;
import com.correios.edwinos.consultacorreios.util.httprequest.HttpResponse;


public class RequestActivity extends Activity implements HttpCallback{
    protected String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);


        this.code = getIntent().getStringExtra("code");

        if(code != null) {

            /** DEBUB **/
            this.beforeSendCallback();
            this.afterSendCallback();
            HttpResponse response = new HttpResponse();
            response.statusCode = 200;
            response.responseText = "{\"success\":true,\"data\":[{\"data\":{\"date\":\"2014-10-15 16:51:00\",\"timezone_type\":3,\"timezone\":\"America\\/Sao_Paulo\"},\"local\":\"CEE BOA VISTA - BOA VISTA\\/RR\",\"situacao\":\"Objeto entregue ao destinat\\u00e1rio\",\"estado\":1,\"id\":3},{\"data\":{\"date\":\"2014-10-15 09:22:00\",\"timezone_type\":3,\"timezone\":\"America\\/Sao_Paulo\"},\"local\":\"CEE BOA VISTA - BOA VISTA\\/RR\",\"situacao\":\"Objeto saiu para entrega ao destinat\\u00e1rio\",\"estado\":2,\"id\":2},{\"data\":{\"date\":\"2014-10-13 17:00:00\",\"timezone_type\":3,\"timezone\":\"America\\/Sao_Paulo\"},\"local\":\"CTE CAMPINAS\\/GCCAP - VALINHOS\\/SP\",\"situacao\":\"Objeto encaminhado\",\"detalhes\":\"Em tr\\u00e2nsito para CEE MANAUS - MANAUS\\/AM\",\"estado\":0,\"id\":1},{\"data\":{\"date\":\"2014-10-13 08:48:00\",\"timezone_type\":3,\"timezone\":\"America\\/Sao_Paulo\"},\"local\":\"CTE CAMPINAS\\/GCCAP - VALINHOS\\/SP\",\"situacao\":\"Objeto postado\",\"estado\":0,\"id\":0}],\"total\":4,\"engineInfo\":{\"projectName\":\"Traquer Track Engine\",\"version\":\"0.5\",\"stability\":\"ALPHA\",\"license\":\"Undefined\",\"credits\":[{\"name\":\"Edwino Stein\",\"email\":\"linkxtremer@gmail.com\"}]}}";
            this.successCallback(response);
            /***********/

            //HttpRequest request = new HttpRequest(this);
            //request.send("http://192.168.2.2/correios/query/json/"+this.code);
        }
        else {

            /** DEBUB **/
            this.beforeSendCallback();
            this.afterSendCallback();
            HttpResponse response = new HttpResponse();
            response.statusCode = 500;
            response.responseText = "{\"success\":false,\"message\":\"Nenhum c\\u00f3digo de rastreamento v\\u00e1lido foi informado\"}";
            this.errorCallback(response);
            /***********/

            //this.finish();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_request, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeSendCallback() {
        setFinishOnTouchOutside(false);
    }

    @Override
    public void afterSendCallback() {}

    @Override
    public void errorCallback(HttpResponse response) {
        setResult(RESULT_CANCELED);
        finish();

    }

    @Override
    public void successCallback(HttpResponse response) {
        Intent data = new Intent();
        data.putExtra("response", response.responseText);
        data.putExtra("code", this.code);

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public void onBackPressed() {}
}
