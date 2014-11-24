package com.correios.edwinos.consultacorreios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.correios.edwinos.consultacorreios.util.httprequest.HttpCallback;
import com.correios.edwinos.consultacorreios.util.httprequest.HttpRequest;
import com.correios.edwinos.consultacorreios.util.httprequest.HttpResponse;


public class RequestActivity extends Activity implements HttpCallback{
    protected String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);


        this.code = getIntent().getStringExtra("code");

        if(code != null && !code.isEmpty()) {
            HttpRequest request = new HttpRequest(this);
            request.send("http://edwinos.ddns.net:8080/correios/query/json/" + this.code);
        }
        else{
            this.errorCallback(new HttpResponse());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_request, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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
