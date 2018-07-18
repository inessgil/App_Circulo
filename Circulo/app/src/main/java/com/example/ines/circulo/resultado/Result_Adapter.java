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

    @Inject
    Context context;
    ResultViewHolder.OnItemSelectedListener listener;

    List<Result_item> list;

    public Result_Adapter(ResultViewHolder.OnItemSelectedListener listener, List<Result_item> list) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_element, parent, false);
        return new ResultViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ResultViewHolder holder = (ResultViewHolder) viewHolder;
        Result_item item = list.get(position);
        String name = item.getName();
        holder.result.setText(name);
        TypedValue value = new TypedValue();
        holder.result.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
        int checkMarkDrawableResId = value.resourceId;
        holder.result.setCheckMarkDrawable(checkMarkDrawableResId);
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
                result_item.setSelected(true);
            }
        }
        notifyDataSetChanged();
        listener.onItemSelected(item);
    }
}
