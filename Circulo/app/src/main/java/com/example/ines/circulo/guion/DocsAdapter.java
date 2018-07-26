package com.example.ines.circulo.guion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ines.circulo.R;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class DocsAdapter extends RecyclerView.Adapter<DocsAdapter.ViewHolder> {

    private final GuionPresenter presenter;
    List<String> images;

    public DocsAdapter(GuionPresenter presenter, List<String> images) {
        this.presenter = presenter;
        this.images = images;
    }


    @Override
    public DocsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.docs_element, parent, false);
        return new DocsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DocsAdapter.ViewHolder holder, int position) {
        holder.text.setText(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textView)
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
