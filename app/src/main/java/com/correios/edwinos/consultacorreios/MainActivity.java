package com.correios.edwinos.consultacorreios;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.correios.edwinos.consultacorreios.util.list.ItemListModel;
import com.correios.edwinos.consultacorreios.util.list.ListAdapter;


public class MainActivity extends ListActivity {

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


        return super.onOptionsItemSelected(item);
    }
}
