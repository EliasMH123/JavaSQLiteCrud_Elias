package com.example.javasqlitecrud.adapter;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.javasqlitecrud.R;
import com.example.javasqlitecrud.model.Escuela;

import java.util.List;

public class EscuelaAdapter extends BaseAdapter {
    private Context context;
    private List<Escuela> listaesc;
    public EscuelaAdapter(Context context, List<Escuela> model){
        this.context = context;
        this.listaesc = model;
    }
    @Override
    public int getCount() {
        return listaesc.size();
    }

    @Override
    public Object getItem(int i) {
        return listaesc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Escuela escuela = listaesc.get(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.escuelas, null);
        }
        TextView txtNom = (TextView) view.findViewById(R.id.escuela_lista_nombre);
        txtNom.setText((i+1)+".- "+escuela.getNombre()+"  - "+escuela.getFacultad());

        return view;
    }
}
