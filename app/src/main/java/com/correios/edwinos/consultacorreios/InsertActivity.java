package com.correios.edwinos.consultacorreios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class InsertActivity extends Activity {
    protected static int debugCouter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void onOkBtnClicked(View v){

        String frendlyName = ((EditText) findViewById(R.id.frendlyName)).getText().toString();
        String codeObj = ((EditText) findViewById(R.id.codeObj)).getText().toString().toUpperCase();

        if(!this.codeObjIsValid(codeObj)){
            Toast.makeText(this, "O código informado é inválido", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent data = new Intent();

            data.putExtra("name", frendlyName);
            data.putExtra("code", codeObj);
            setResult(RESULT_OK, data);

            finish();
        }
    }

    public void onCancelBtnClicked(View v){
        setResult(RESULT_CANCELED);
        finish();
    }

    protected boolean codeObjIsValid(String code){

        if(code.length() != 13){
            return false;
        }


        return true;
    }
}
