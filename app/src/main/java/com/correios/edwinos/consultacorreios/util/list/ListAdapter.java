package com.correios.edwinos.consultacorreios.util.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.correios.edwinos.consultacorreios.R;
import com.correios.edwinos.consultacorreios.util.DateParser;

import java.util.ArrayList;

/**
 * Created by EdwinoS on 08/11/14.
 */
public class ListAdapter extends ArrayAdapter<ItemListModel> {

    protected Context context;
    private ArrayList<ItemListModel> items = new ArrayList<ItemListModel>();

    public ListAdapter(Context context, ItemListModel[] items){

        this(context);

        for (int i = 0; i < items.length; i++){
            Log.d("Events", "Adicionado ("+i+"): "+items[i].getCode());
            this.items.add(items[i]);
        }
    }

    public ListAdapter(Context context){
        super(context, R.layout.list_item);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemListView = inflater.inflate(R.layout.list_item, parent, false);

        ItemListModel itemData = this.items.get(position);
        Log.d("Events", "Renderizando ("+position+"): "+itemData.getCode());

        if(itemData.getFrendlyName().isEmpty()){
            ((TextView) itemListView.findViewById(R.id.itemListCode)).setVisibility(View.GONE);
            ((TextView) itemListView.findViewById(R.id.itemListFrendlyName)).setText(itemData.getCode());
        } else{
            ((TextView) itemListView.findViewById(R.id.itemListCode)).setText(itemData.getCode());
            ((TextView) itemListView.findViewById(R.id.itemListFrendlyName)).setText(itemData.getFrendlyName());
        }

        ((ImageView) itemListView.findViewById(R.id.listItemIcon)).setImageResource(itemData.getLastData().getThumbStatusRes());
        ((TextView) itemListView.findViewById(R.id.last_status)).setText(itemData.getLastData().getSituacao());

        if(itemData.getLastData().getData() != null) {
            ((TextView) itemListView.findViewById(R.id.last_date)).setText(DateParser.dataToString(itemData.getLastData().getData()));
        }
        else{
            ((TextView) itemListView.findViewById(R.id.last_date)).setVisibility(View.GONE);
        }

        return itemListView;
    }

    public void add(ItemListModel item){
        super.add(item);
        this.items.add(item);
    }

}
