package com.correios.edwinos.consultacorreios.util.card;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.correios.edwinos.consultacorreios.R;

/**
 * Created by edwinos on 20/11/14.
 */
public class CorreiosEventViewHolder extends RecyclerView.ViewHolder {

    ImageView vStatusThumb;
    TextView vStatus;
    TextView vLocal;
    TextView vDateTime;
    TextView vDetails;

    public CorreiosEventViewHolder(View itemView) {
        super(itemView);

        this.vStatusThumb = (ImageView) itemView.findViewById(R.id.statusThumb);
        this.vStatus = (TextView) itemView.findViewById(R.id.status);
        this.vLocal = (TextView) itemView.findViewById(R.id.local);
        this.vDateTime = (TextView) itemView.findViewById(R.id.dateTime);
        this.vDetails = (TextView) itemView.findViewById(R.id.details);

    }
}
