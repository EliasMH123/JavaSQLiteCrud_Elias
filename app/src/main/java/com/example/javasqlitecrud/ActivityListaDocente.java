package com.example.javasqlitecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.javasqlitecrud.adapter.DocenteAdapter;
import com.example.javasqlitecrud.dao.DocenteDao;
import com.example.javasqlitecrud.model.Docente;
import com.example.javasqlitecrud.util.Mensajes;

import java.util.List;

public class ActivityListaDocente extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{
    private ListView lista2;
    private List<Docente> listaList;
    private DocenteAdapter adapter;
    private DocenteDao docenteDAO;
    private int idposi;
    private AlertDialog alertDialog, alertconfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_docente);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertconfirm = Mensajes.crearDialogConfirmacion(this);

        docenteDAO = new DocenteDao(this);
        listaList = docenteDAO.listarDocentes();
        adapter = new DocenteAdapter(this,listaList);

        lista2 = (ListView) findViewById(R.id.lvDocentes);
        lista2.setAdapter(adapter);

        lista2.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_docente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_listadoc) {
            startActivity(new Intent(this, ActivityRegistrarDoc.class));
        }
        if(id==R.id.action_menu_salirlistdoc){
            startActivity(new Intent(this, ActivitySelection.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = listaList.get(idposi).get_iddoc();
        switch (which){
            case 0:
                Intent intent = new Intent(this, ActivityRegistrarDoc.class);
                intent.putExtra("DOCENTE_ID",id);
                startActivity(intent);
                break;
            case 1:alertconfirm.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                listaList.remove(idposi);
                docenteDAO.eliminarDocente(id);
                lista2.invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertconfirm.dismiss();break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idposi = position;
        alertDialog.show();
    }
}