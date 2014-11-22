package com.correios.edwinos.consultacorreios.util.card;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.correios.edwinos.consultacorreios.R;
import com.correios.edwinos.consultacorreios.util.DateParser;
import com.correios.edwinos.consultacorreios.util.json.CorreiosData;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by edwinos on 20/11/14.
 */

/**
 * TODO: Arrumar as cores dos estados e a data
 */
public class CorreiosEventAdapter extends RecyclerView.Adapter<CorreiosEventViewHolder>{

    private CorreiosData[] dataList;

    public CorreiosEventAdapter(CorreiosData[] dataList) {
        this.dataList = dataList;
    }

    @Override
    public CorreiosEventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.data_card, viewGroup, false);

        return new CorreiosEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CorreiosEventViewHolder correiosEventViewHolder, int i) {
        CorreiosData data = this.dataList[i];

        correiosEventViewHolder.vStatus.setText(data.getSituacao());



        correiosEventViewHolder.vDateTime.setText(
                DateParser.getWeekDayName(data.getData())+", "+
                DateParser.fullDataToString(data.getData())+" às "+
                DateParser.getHour(data.getData())
        );

        correiosEventViewHolder.vLocal.setText(data.getLocal());

        if(data.getDetalhes() != null) {
            correiosEventViewHolder.vDetails.setText(data.getDetalhes());
        }
        else{
            correiosEventViewHolder.vDetails.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.length;
    }
}
