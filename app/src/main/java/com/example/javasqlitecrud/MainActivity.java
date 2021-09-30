package com.example.javasqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javasqlitecrud.dao.UsuarioDao;
import com.example.javasqlitecrud.util.Mensajes;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsuario, edtClave;
    private UsuarioDao usuarioDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario = (EditText) findViewById(R.id.edt_Usuario);
        edtClave = (EditText) findViewById(R.id.edt_Clave);
        usuarioDAO = new UsuarioDao(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    public void logueo(View view){
        String usuario = edtUsuario.getText().toString();
        String clave = edtClave.getText().toString();
        Toast.makeText(getApplicationContext(),usuario,Toast.LENGTH_LONG).show();
        boolean valida = true;
        if(usuario == null || usuario.equals("")){
            valida = false;
            edtUsuario.setError(getString(R.string.Login_validaUsuario));
        }
        if(clave == null || clave.equals("")){
            valida = false;
            edtClave.setError(getString(R.string.Login_validaClave));
        }
        if(valida){
            if(usuarioDAO.logueoUser(usuario, clave)){
                startActivity(new Intent(this, ActivitySelection.class));
                finish();
            }else{
                Mensajes.Msg(this,getString(R.string.msg_login_incorrecto));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}