package com.example.ines.circulo.resultado;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;

import com.example.ines.circulo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultViewHolder extends RecyclerView.ViewHolder {

    Result_item item;
    @BindView(R.id.tv_result)
    CheckedTextView result;
    OnItemSelectedListener itemSelectedListener;

    public ResultViewHolder(View itemView, OnItemSelectedListener listener) {
        super(itemView);
        itemSelectedListener = listener;
        ButterKnife.bind(itemView);
        item.setName(result.getText().toString());
    }

    @OnClick(R.id.tv_result)
    public void check (View view) {
        setChecked(true);
        itemSelectedListener.onItemSelected(item);
    }

    public void setChecked (boolean value) {
        if(value) {
            result.setBackgroundColor(Color.GRAY);
        }
        else {
            result.setBackground(null);
        }
        item.setSelected(value);
        result.setChecked(true);
    }
    public interface OnItemSelectedListener {
        void onItemSelected(Result_item item);
    }
}
