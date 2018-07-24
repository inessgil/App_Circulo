package com.example.ines.circulo.guion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ines.circulo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//TODO: Make zoom
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    List<String> images;
    GuionPresenter presenter;

    public GalleryAdapter(List<String> images, GuionPresenter presenter) {
        this.images = images;
        this.presenter = presenter;
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_element, parent, false);
        return new GalleryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, int position) {
        String image = images.get(position);
        holder.imageGallery.setImageBitmap(presenter.loadImage(image));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageGallery)
        ImageView imageGallery;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
