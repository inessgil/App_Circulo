package com.example.ines.circulo.resultado;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;

import com.example.ines.circulo.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultViewHolder extends RecyclerView.ViewHolder {

    Result_item item;
    @BindView(R.id.tv_result)
    CheckedTextView result;
    CardView card;

    Context context;

    OnItemSelectedListener itemSelectedListener;

    public ResultViewHolder(View itemView, OnItemSelectedListener listener, Context context) {
        super(itemView);
        itemSelectedListener = listener;
        this.context = context;
        ButterKnife.bind(itemView);
        card = itemView.findViewById(R.id.card);
        result = itemView.findViewById(R.id.tv_result);
        result.setCheckMarkDrawable(null);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemSelectedListener.onItemSelected(item);
            }
        });
    }

    public void setChecked (boolean value) {
        if(value) {
            card.setCardBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }
        else {
            card.setCardBackgroundColor(context.getResources().getColor(R.color.transparent));
        }
        item.setSelected(value);
        result.setChecked(value);
    }
    public interface OnItemSelectedListener {
        void onItemSelected(Result_item item);
    }
}
