package com.example.javasqlitecrud.adapter;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.javasqlitecrud.R;
import com.example.javasqlitecrud.model.Docente;

import java.util.List;

public class DocenteAdapter extends BaseAdapter{
    private Context context;
    private List<Docente> listadoc;
    public DocenteAdapter(Context context, List<Docente> model){
        this.context = context;
        this.listadoc = model;
    }

    @Override
    public int getCount() {
        return listadoc.size();
    }

    @Override
    public Object getItem(int i) {
        return listadoc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Docente docente = listadoc.get(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.docentes, null);
        }
        TextView txtNom = (TextView) view.findViewById(R.id.docente_lista_nombre);
        txtNom.setText((i+1)+".- "+docente.getNombre()+"  - "+docente.getCorreo());

        return view;
    }
}
