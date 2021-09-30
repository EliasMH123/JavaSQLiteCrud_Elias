package com.example.javasqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.javasqlitecrud.dao.EscuelaDao;
import com.example.javasqlitecrud.model.Escuela;
import com.example.javasqlitecrud.util.Mensajes;

public class ActivityRegistrarEsc extends AppCompatActivity {
    private EditText edtNombre, edtFacultad;
    private EscuelaDao escuelaDAO;
    private Escuela escuela;
    private int idesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_esc);
        escuelaDAO = new EscuelaDao(this);
        edtNombre = (EditText) findViewById(R.id.txtNombreesc);
        edtFacultad = (EditText) findViewById(R.id.txtFacultad);


        idesc = getIntent().getIntExtra("ESCUELA_ID",0);
        if(idesc > 0){
            Escuela model = escuelaDAO.buscarEscuelaPorID(idesc);
            edtNombre.setText(model.getNombre());
            edtFacultad.setText(model.getFacultad());
            setTitle("Actualizar Escuela");
        }
    }
    @Override
    protected void onDestroy() {
        escuelaDAO.cerrarDB();
        super.onDestroy();
    }
    private void registraresc(){
        boolean validar = true;
        String nombreesc = edtNombre.getText().toString();
        String facultad = edtFacultad.getText().toString();
        if(nombreesc == null || nombreesc.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.escuela_validanombre));
        }
        if(facultad == null || facultad.equals("")){
            validar = false;
            edtFacultad.setError(getString(R.string.escuela_validafacultad));
        }
        if(validar){
            escuela = new Escuela();
            escuela.setNombre(nombreesc);
            escuela.setFacultad(facultad);
            if(idesc > 0){
                escuela.set_idesc(idesc);
            }
            long resultado = escuelaDAO.modificarEscuela(escuela);
            if(resultado != -1){
                if(idesc > 0) {
                    Mensajes.Msg(this, getString(R.string.msg_escuela_modificado));
                    startActivity(new Intent(this, ActivityListaEscuela.class));
                }else{
                    Mensajes.Msg(this, getString(R.string.msg_escuela_guardado));
                    startActivity(new Intent(this, ActivityListaEscuela.class));
                }
                finish();
                //startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensajes.Msg(this, getString(R.string.msg_escuela_error));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar_escuela, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_menu_guardaresc:
                this.registraresc();
                break;
            case R.id.action_menu_salirregisesc:
                finish();
                startActivity(new Intent(this, ActivityListaEscuela.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}