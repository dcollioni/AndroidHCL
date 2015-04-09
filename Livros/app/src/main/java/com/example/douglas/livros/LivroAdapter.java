package com.example.douglas.livros;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Douglas on 4/8/2015.
 */
public class LivroAdapter extends BaseAdapter {

    Context context;
    ArrayList<Livro> items;

    private ArrayList<Livro> selectedItems;

    public void setSelectedItems(ArrayList<Livro> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public LivroAdapter(Context context, ArrayList<Livro> items) {
        this.context = context;
        this.items = items;
        selectedItems = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(R.layout.livro_list_item, parent, false);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Livro l = (Livro)getItem(position);

        if (l != null) {
            viewHolder.tvLivroTitulo.setText(l.getTitulo());
            viewHolder.tvLivroAutor.setText(l.getAutor());
        }

        if (selectedItems.contains(l)) {
            convertView.setBackgroundResource(android.R.color.holo_blue_dark);
        } else {
            convertView.setBackgroundColor(android.R.attr.colorPrimary);
        }

        return convertView;
    }

    public static class ViewHolder {
        public final TextView tvLivroTitulo;
        public final TextView tvLivroAutor;

        public ViewHolder(View view) {
            this.tvLivroTitulo = (TextView) view.findViewById(R.id.tv_livro_titulo);
            this.tvLivroAutor = (TextView) view.findViewById(R.id.tv_livro_autor);
        }
    }
}
