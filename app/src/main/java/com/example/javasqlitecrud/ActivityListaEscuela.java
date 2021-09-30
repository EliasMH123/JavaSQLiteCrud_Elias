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

import com.example.javasqlitecrud.adapter.EscuelaAdapter;
import com.example.javasqlitecrud.dao.EscuelaDao;
import com.example.javasqlitecrud.model.Escuela;
import com.example.javasqlitecrud.util.Mensajes;

import java.util.List;

public class ActivityListaEscuela extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{
    private ListView lista3;
    private List<Escuela> listaList;
    private EscuelaAdapter adapter;
    private EscuelaDao escuelaDAO;
    private int idposi;
    private AlertDialog alertDialog, alertconfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_escuela);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertconfirm = Mensajes.crearDialogConfirmacion(this);

        escuelaDAO = new EscuelaDao(this);
        listaList = escuelaDAO.listarEscuelas();
        adapter = new EscuelaAdapter(this,listaList);

        lista3 = (ListView) findViewById(R.id.lvEscuelas);
        lista3.setAdapter(adapter);

        lista3.setOnItemClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_escuela, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_listaesc) {
            startActivity(new Intent(this, ActivityRegistrarEsc.class));
        }
        if(id==R.id.action_menu_salirlistesc){
            startActivity(new Intent(this, ActivitySelection.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = listaList.get(idposi).get_idesc();
        switch (which){
            case 0:
                Intent intent = new Intent(this, ActivityRegistrarEsc.class);
                intent.putExtra("ESCUELA_ID",id);
                startActivity(intent);
                break;
            case 1:alertconfirm.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                listaList.remove(idposi);
                escuelaDAO.eliminarEscuela(id);
                lista3.invalidateViews();
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