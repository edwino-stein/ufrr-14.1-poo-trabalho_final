package com.correios.edwinos.consultacorreios;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.correios.edwinos.consultacorreios.util.json.JsonParser;
import com.correios.edwinos.consultacorreios.util.list.ItemListModel;
import com.correios.edwinos.consultacorreios.util.list.ListAdapter;


public class MainActivity extends ListActivity {
    protected ItemListModel preAdded;

    public static final int INSERT_ACTION = 1;
    public static final int VERIFY_ACTION = 2;

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
            startActivityForResult(new Intent("com.correios.edwinos.consultacorreios.InsertActivity"), MainActivity.INSERT_ACTION);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        switch (requestCode) {
            case INSERT_ACTION:
                if(resultCode == RESULT_OK) {
                    this.preAdded = new ItemListModel(data.getStringExtra("code"), data.getStringExtra("name"));
                    this.verifyCode(this.preAdded.getCode());
                }
            break;
            case VERIFY_ACTION:

                /**
                 * TODO: Fazer uma caixa de dialogo
                 */

                if(resultCode == RESULT_OK) {
                    JsonParser jsonResponse = new JsonParser(data.getStringExtra("response"));

                    if(!jsonResponse.isSuccess()){
                        Toast.makeText(this, jsonResponse.getMessage(), Toast.LENGTH_LONG).show();
                        this.preAdded = null;
                        break;
                    }

                    if (jsonResponse.getTotal() <= 0){
                        Toast.makeText(this, "Vazio", Toast.LENGTH_SHORT).show();
                        this.preAdded = null;
                        break;
                    }

                    ((ListAdapter) this.getListAdapter()).add(this.preAdded);
                }
                else{

                    Toast.makeText(this, "A comunicação com o servidor falhou.\nVerifique se você está conectado a internet.", Toast.LENGTH_LONG).show();
                }

                this.preAdded = null;
            break;
        }
    }

    protected void verifyCode(String code){

        Intent requestIntent = new Intent("com.correios.edwinos.consultacorreios.RequestActivity");
        requestIntent.putExtra("code", code);

        startActivityForResult(requestIntent, MainActivity.VERIFY_ACTION);


    }
}
