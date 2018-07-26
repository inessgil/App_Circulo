package com.example.ines.circulo.resultado;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ines.circulo.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Result_Adapter extends RecyclerView.Adapter implements ResultViewHolder.OnItemSelectedListener {

    Context context;
    ResultViewHolder.OnItemSelectedListener listener;

    List<Result_item> list;

    public Result_Adapter(ResultViewHolder.OnItemSelectedListener listener, List<Result_item> list, Context context) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_element, parent, false);
        return new ResultViewHolder(view, this, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ResultViewHolder holder = (ResultViewHolder) viewHolder;
        Result_item item = list.get(position);
        holder.item = item;
        String name = item.getName();
        //25072018_estudiantes
        String aux = name.substring(9, name.length()) + " "
                + name.substring(0,2) + "/"
                + name.substring(2, 4) + "/"
                + name.substring(4,8);
        holder.result.setText(aux);
        holder.result.setChecked(false);
        TypedValue value = new TypedValue();
        holder.result.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
        holder.item = item;
        holder.setChecked(holder.item.isSelected());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<Result_item> getSelectedItems () {
        List<Result_item> items = new ArrayList<>();
        for (Result_item item : list) {
            if ( item.isSelected()) items.add(item);
        }
        return items;
    }


    @Override
    public void onItemSelected(Result_item item) {
        for (Result_item result_item : list) {
            if (!result_item.equals(item) && result_item.isSelected()) {
                result_item.setSelected(false);
            } else if (result_item.equals(item) && item.isSelected()) {
                result_item.setSelected(false);
            } else if (result_item.equals(item) && !item.isSelected()) {
                result_item.setSelected(true);
            }
        }
        notifyDataSetChanged();
        listener.onItemSelected(item);
    }
}
