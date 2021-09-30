package com.example.javasqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.javasqlitecrud.dao.DocenteDao;
import com.example.javasqlitecrud.model.Docente;
import com.example.javasqlitecrud.util.Mensajes;

public class ActivityRegistrarDoc extends AppCompatActivity {
    private EditText edtCodigo, edtNombre, edtDNI, edtTelefono, edtCorreo;
    private DocenteDao docenteDAO;
    private Docente docente;
    private int iddoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_doc);
        docenteDAO = new DocenteDao(this);
        edtCodigo = (EditText) findViewById(R.id.txtCodigo);
        edtNombre = (EditText) findViewById(R.id.txtNombredoc);
        edtDNI = (EditText) findViewById(R.id.txtDNI);
        edtTelefono = (EditText) findViewById(R.id.txtTelefono);
        edtCorreo = (EditText) findViewById(R.id.txtCorreo);

        iddoc = getIntent().getIntExtra("DOCENTE_ID",0);
        if(iddoc > 0){
            Docente model = docenteDAO.buscarDocentePorID(iddoc);
            edtCodigo.setText(model.getCodigo());
            edtNombre.setText(model.getNombre());
            edtDNI.setText(model.getDni());
            edtTelefono.setText(model.getTelefono());
            edtCorreo.setText(model.getCorreo());
            setTitle("Actualizar Docente");
        }
    }
    @Override
    protected void onDestroy() {
        docenteDAO.cerrarDB();
        super.onDestroy();
    }
    private void registrardoc(){
        boolean validar = true;
        String codigo = edtCodigo.getText().toString();
        String nombre = edtNombre.getText().toString();
        String dni = edtDNI.getText().toString();
        String telefono = edtTelefono.getText().toString();
        String correo = edtCorreo.getText().toString();
        if(codigo == null || codigo.equals("")){
            validar = false;
            edtCodigo.setError(getString(R.string.docente_validacodigo));
        }
        if(nombre == null || nombre.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.docente_validanombre));
        }
        if(dni == null || dni.equals("")){
            validar = false;
            edtDNI.setError(getString(R.string.docente_validadni));
        }
        if(telefono == null || telefono.equals("")){
            validar = false;
            edtTelefono.setError(getString(R.string.docente_validatelefono));
        }
        if(correo == null || correo.equals("")){
            validar = false;
            edtCorreo.setError(getString(R.string.docente_validacorreo));
        }
        if(validar){
            docente = new Docente();
            docente.setCodigo(codigo);
            docente.setNombre(nombre);
            docente.setDni(dni);
            docente.setTelefono(telefono);
            docente.setCorreo(correo);
            if(iddoc > 0){
                docente.set_iddoc(iddoc);
            }
            long resultado = docenteDAO.modificarDocente(docente);
            if(resultado != -1){
                if(iddoc > 0) {
                    Mensajes.Msg(this, getString(R.string.msg_docente_modificado));
                    startActivity(new Intent(this, ActivityListaDocente.class));
                }else{
                    Mensajes.Msg(this, getString(R.string.msg_docente_guardado));
                    startActivity(new Intent(this, ActivityListaDocente.class));
                }
                finish();
                //startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensajes.Msg(this, getString(R.string.msg_docente_error));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar_docente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_menu_guardardoc:
                this.registrardoc();
                break;
            case R.id.action_menu_salirregisdoc:
                finish();
                startActivity(new Intent(this, ActivityListaDocente.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}