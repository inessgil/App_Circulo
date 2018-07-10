package com.example.ines.circulo.resultado;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ines.circulo.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Result_Adapter extends RecyclerView.Adapter<Result_Adapter.ResultViewHolder> {

    @Inject
    Context context;

    List<Result_item> list;
    private int checked;

    public Result_Adapter(List<Result_item> list) {
        this.list = list;
        checked = -1;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.result_element, parent, false));
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.item = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        Result_item item;
        @BindView(R.id.tv_result)
        TextView result;

        public ResultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            item.setName(result.getText().toString());
        }

        @OnClick(R.id.tv_result)
        public void check (View view) {
            view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            setChecked(item);
        }
    }

    public void setChecked (Result_item item) {
        for ( Result_item i : list) {
            if (item == i) {
                i.setSelected(true);
            }
            else {
                i.setSelected(false);
            }
        }
        notifyDataSetChanged();
    }
}
