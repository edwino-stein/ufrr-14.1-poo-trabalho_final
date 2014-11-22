package com.correios.edwinos.consultacorreios;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.correios.edwinos.consultacorreios.util.card.CorreiosEventAdapter;
import com.correios.edwinos.consultacorreios.util.database.CorreiosDataBase;
import com.correios.edwinos.consultacorreios.util.database.CorreiosEntity;
import com.correios.edwinos.consultacorreios.util.database.Entity;
import com.correios.edwinos.consultacorreios.util.json.JsonParser;


public class DataViewActivity extends Activity {

    protected String code;
    protected CorreiosEntity entity;
    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        this.code = getIntent().getStringExtra("code");

        CorreiosDataBase correiosObjectsData = new CorreiosDataBase(this, "com.correios.edwinos.consultacorreios.util.database.CorreiosEntity");
        this.entity = (CorreiosEntity) correiosObjectsData.select("code='"+this.code+"'")[0];
        JsonParser json = new JsonParser(this.entity.getJson_data());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle(this.code, this.entity.getName());

        CorreiosEventAdapter adapter = new CorreiosEventAdapter(json.getData());
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

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
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
}
