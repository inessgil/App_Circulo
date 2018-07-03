package com.example.ines.circulo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.FileReader;
import java.util.List;

/**
 * Adapter for an element on Guion's spaces
 * Every space will have a list of the elements specified in this adapter
 * Every element contains an image or text
 * At the creation of the adapter there are delivered a list of paths to this images or texts
 * In every element one image/text will be loaded given it's path
 * It will have a function to add and delete an element from the list on the recycler view
 */

public class Element_Adapter extends RecyclerView.Adapter<Element_Adapter.ElementViewHolder> {

    List<String> paths;
    Context context;

    public Element_Adapter (List<String> paths, Context context) {
        this.paths = paths;
        this.context = context;
    }

    public static class ElementViewHolder extends RecyclerView.ViewHolder {

        LinearLayout element_container;
        RelativeLayout element;

        public ElementViewHolder(View view) {
            super(view);
            element_container = (LinearLayout) view.findViewById(R.id.element_layout);
            element = (RelativeLayout) view.findViewById(R.id.element);
        }
    }

    public void addElement (String path) {
        paths.add(path);
    }

    public void deleteElement ( int index ) {
        paths.remove(index);
    }

    public String getElement ( int index ){
        return paths.get(index);
    }

    @Override
    public ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_view, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ElementViewHolder holder, int position) {
        //TODO: Load text or image from path on paths(position) in Relative layout

        //TODO: Test this:
        ImageView view = new ImageView(context);
        view.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_gallery));
        holder.element.addView(view);
    }

    @Override
    public int getItemCount() {
        return paths.size();
    }

}
