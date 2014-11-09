package com.correios.edwinos.consultacorreios;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.correios.edwinos.consultacorreios.util.list.ItemListModel;
import com.correios.edwinos.consultacorreios.util.list.ListAdapter;


public class MainActivity extends ListActivity {
    protected ItemListModel preAdded;

    public static final int INSET_ACTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListAdapter adapter = new ListAdapter(this);

        /**
         * INICIALIZANDO COM DADOS FICTICIOS
         */
        for (int i = 0; i < 5; i++){
            adapter.add(new ItemListModel("CODE-"+i, "Nome Item "+i));
        }

        setListAdapter(adapter);
    }

    protected void onListItemClick(android.widget.ListView l, android.view.View v, int position, long id){
        ListAdapter adapter = (ListAdapter) l.getAdapter();
        Toast.makeText(this, "Selecionado: " + adapter.getItem(position).getFrendlyName(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_add){
            startActivityForResult(new Intent("com.correios.edwinos.consultacorreios.InsertActivity"), MainActivity.INSET_ACTION);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        switch (requestCode) {
            case INSET_ACTION:
                if(resultCode == RESULT_OK) {
                    this.preAdded = new ItemListModel(data.getStringExtra("code"), data.getStringExtra("name"));
                    this.verifyCode(this.preAdded.getCode());
                }
            break;
        }
    }

    protected void verifyCode(String code){

        Toast.makeText(this, "Codigo: "+ code, Toast.LENGTH_SHORT).show();

    }
}
