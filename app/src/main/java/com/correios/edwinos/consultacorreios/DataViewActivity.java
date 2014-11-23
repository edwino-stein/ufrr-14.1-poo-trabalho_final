package com.correios.edwinos.consultacorreios;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.correios.edwinos.consultacorreios.util.Dialog;
import com.correios.edwinos.consultacorreios.util.card.CorreiosEventAdapter;
import com.correios.edwinos.consultacorreios.util.database.CorreiosDataBase;
import com.correios.edwinos.consultacorreios.util.database.CorreiosEntity;
import com.correios.edwinos.consultacorreios.util.json.CorreiosData;
import com.correios.edwinos.consultacorreios.util.json.JsonParser;


public class DataViewActivity extends Activity implements Dialog.DialogResult {

    public static final int UPDATE_DATA = 1;
    public static final int DELETE_DATA = 2;


    protected String code;
    protected CorreiosEntity entity;
    protected RecyclerView recyclerView;
    protected CorreiosDataBase correiosObjectsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        this.code = getIntent().getStringExtra("code");
        this.correiosObjectsData = new CorreiosDataBase(this, "com.correios.edwinos.consultacorreios.util.database.CorreiosEntity");
        this.entity = (CorreiosEntity) correiosObjectsData.select("code='"+this.code+"'")[0];


        JsonParser json = new JsonParser(this.entity.getJson_data());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle(this.code, this.entity.getName());

        CorreiosEventAdapter adapter;

        if(json.getTotal() > 0) {
            adapter = new CorreiosEventAdapter(json.getData());
        }
        else{
            adapter = new CorreiosEventAdapter(this.getEmptyData());
        }

        this.getRecyclerView().setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_refresh){
            Intent requestIntent = new Intent("com.correios.edwinos.consultacorreios.RequestActivity");
            requestIntent.putExtra("code", this.code);

            startActivityForResult(requestIntent, DataViewActivity.UPDATE_DATA);
            return true;
        }

        if(id == android.R.id.home){
            this.finish();
            return true;
        }

        if(id == R.id.action_delete){
            Dialog.questionDialog(this, DELETE_DATA, "Excluir", "Deseja realmente excluir o objeto \""+this.code+"\"?");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case DataViewActivity.UPDATE_DATA:
                if(resultCode == RESULT_OK){
                    this.updateData(data.getStringExtra("response"));
                }
                else{
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            break;
        }
    }

    protected void updateData(String response){

        this.entity.setJson_data(response);

        this.correiosObjectsData.update(this.entity, "code=\""+this.code+"\"");

        JsonParser json = new JsonParser(response);
        CorreiosEventAdapter adapter;

        if(json.getTotal() > 0) {
            adapter = new CorreiosEventAdapter(json.getData());
        }
        else{
            adapter = new CorreiosEventAdapter(this.getEmptyData());
        }

        this.getRecyclerView().setAdapter(adapter);
    }

    protected void setTitle(String code, String frendlyName){
        ActionBar ab = getActionBar();

        if(frendlyName.isEmpty()){
            super.setTitle(code);
        }
        else{
            ab.setTitle(frendlyName);
            ab.setSubtitle(code);
        }

    }

    protected RecyclerView getRecyclerView(){

        if(this.recyclerView == null) {

            this.recyclerView = (RecyclerView) findViewById(R.id.cardList);
            this.recyclerView.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            this.recyclerView.setLayoutManager(llm);
        }

        return this.recyclerView;
    }

    protected CorreiosData[] getEmptyData(){

        CorreiosData[] emptyData = new CorreiosData[1];

        emptyData[0] = new CorreiosData(true);

        return emptyData;
    }


    protected void deleteObject(){
        if(!this.correiosObjectsData.delete("code=\""+this.code+"\"")){
            Dialog.alertDialog(this, "Erro ao Excluir", "Ocorreu um erro ao tentar excluir o objeto \""+this.code+"\".\n\nErro: "+this.correiosObjectsData.getErrorMessage());
        }
        else{
            this.finish();
        }
    }

    @Override
    public void onDialogResult(int index, boolean result) {
        switch (index){
            case DELETE_DATA:
                if(result) {
                    this.deleteObject();
                }
            break;
        }
    }
}
