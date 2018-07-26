package com.example.ines.circulo.guion;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ines.circulo.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuionAdapter extends RecyclerView.Adapter<GuionAdapter.ViewHolder>{

    List<GuionPart> list;
    GuionPresenter presenter;
    Context context;

    public GuionAdapter (GuionPresenter presenter, Context context) {
        list = new ArrayList<> ();
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public GuionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guion_element, parent, false);
        return new GuionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuionAdapter.ViewHolder holder, int position) {
        GuionPart part = list.get(position);
        holder.editableText.setText(part.getEditable_text());
        holder.text.setText(part.getText());
        GalleryAdapter adapter = new GalleryAdapter(part.images, presenter);
        holder.galery.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.galery.setAdapter(adapter);
        DocsAdapter docsAdapter = new DocsAdapter(presenter, part.docs);
        holder.docs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.docs.setAdapter(docsAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<GuionPart> data) {
        this.list = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.b_editText)
        Button editSaveTextButton;
        @BindView(R.id.b_empty)
        Button emptyText;
        @BindView(R.id.b_addImage)
        Button addImageButton;
        @BindView(R.id.b_deleteImage)
        Button deleteImageButton;
        @BindView(R.id.b_addDoc)
        Button addDocButton;
        @BindView(R.id.b_deleteDoc)
        Button deleteDocButton;
        @BindView(R.id.editable_text)
        EditText editableText;
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.galery)
        RecyclerView galery;
        @BindView(R.id.docs)
        RecyclerView docs;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.b_editText)
        void edit () {
            if (editSaveTextButton.getText().toString().equals("Editar")) {
                editSaveTextButton.setText("Guardar");
                editableText.setEnabled(true);
                editableText.setFocusableInTouchMode(true);
            }
            else {
                editSaveTextButton.setText("Editar");
                editableText.setEnabled(true);
                editableText.setFocusable(false);
                String text = editableText.getText().toString();
                presenter.saveText(text, getAdapterPosition());
            }
        }

        //TODO: set add/delete images/docs buttons actions
    }
}
