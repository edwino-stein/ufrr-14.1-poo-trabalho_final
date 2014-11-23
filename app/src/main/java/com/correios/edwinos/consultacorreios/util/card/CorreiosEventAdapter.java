package com.correios.edwinos.consultacorreios.util.card;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.correios.edwinos.consultacorreios.R;
import com.correios.edwinos.consultacorreios.util.DateParser;
import com.correios.edwinos.consultacorreios.util.json.CorreiosData;

/**
 * Created by edwinos on 20/11/14.
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
        correiosEventViewHolder.vStatusThumb.setImageResource(data.getThumbStatusRes());

        if(data.getDetalhes() != null) {
            correiosEventViewHolder.vDetails.setText(data.getDetalhes());
        }
        else{
            correiosEventViewHolder.vDetails.setVisibility(View.GONE);
        }


        if(data.getEstado() >= 0){

            correiosEventViewHolder.vDateTime.setText(
                    DateParser.getWeekDayName(data.getData()) + ", " +
                            DateParser.fullDataToString(data.getData()) + " às " +
                            DateParser.getHour(data.getData())
            );

            correiosEventViewHolder.vLocal.setText(data.getLocal());

        } else{
            this.renderSimpleCard(correiosEventViewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.length;
    }


    protected void renderSimpleCard(CorreiosEventViewHolder correiosEventViewHolder){

        correiosEventViewHolder.vLocal.setVisibility(View.GONE);
        correiosEventViewHolder.vDateTime.setVisibility(View.GONE);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.removeRule(RelativeLayout.ALIGN_LEFT);
            params.removeRule(RelativeLayout.ALIGN_TOP);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.statusThumb);
            params.setMarginStart(20);
            correiosEventViewHolder.vStatus.setLayoutParams(params);
        }


    }
}
