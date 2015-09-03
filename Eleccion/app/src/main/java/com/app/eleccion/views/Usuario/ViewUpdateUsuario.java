package com.app.eleccion.views.Usuario;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.eleccion.R;
import com.app.eleccion.dataSource.DsUsuario;

import org.json.JSONObject;

public class ViewUpdateUsuario extends ActionBarActivity {

    private EditText identificacion,nombre,apellido,direccion,telefono,correo,nick,pass;
    private Spinner tipouser;
    private Button aceptar;
    private String id;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update_usuario);

        identificacion = (EditText) findViewById(R.id.editText3);
        nombre= (EditText) findViewById(R.id.editText4);
        apellido = (EditText) findViewById(R.id.editText5);
        direccion = (EditText) findViewById(R.id.editText6);
        telefono = (EditText) findViewById(R.id.editText7);
        correo = (EditText) findViewById(R.id.editText8);
        nick = (EditText) findViewById(R.id.editText9);
        pass = (EditText) findViewById(R.id.editText10);
        aceptar = (Button) findViewById(R.id.button5);

        tipouser = (Spinner) findViewById(R.id.spinner);

        String tipos[] = new String[2];
        tipos[0] = "Jurado";
        tipos[1] = "Admin";
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipouser.setAdapter(dataAdapter);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        if(id != null){
            cargar();
        }
    }

    public  void cargar(){

        try {
            JSONObject jsonObject = new DsUsuario().find(id);
            identificacion.setText(jsonObject.getString("identificacion"));
            nombre.setText(jsonObject.getString("nombre"));
            apellido.setText(jsonObject.getString("apellido"));
            direccion.setText(jsonObject.getString("direccion"));
            telefono.setText(jsonObject.getString("telefono"));
            correo.setText(jsonObject.getString("correo"));
            nick.setText(jsonObject.getString("nick"));
            pass.setText(jsonObject.getString("pass"));
            tipouser.setSelection(jsonObject.getInt("tipouser"));

        }catch (Exception er){
            Toast.makeText(getApplicationContext(), "Error al carga el Usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void guardar(View view){

        if(identificacion.getText().toString().trim().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("El Campo Identificacion es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        if(nombre.getText().toString().trim().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("El Campo Nombre es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        if(nick.getText().toString().trim().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("El Campo Nick es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        if(pass.getText().toString().trim().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("El Campo Password es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        DsUsuario dsUsuario = new DsUsuario();
        boolean bol =  dsUsuario.update(id, identificacion.getText().toString(), nombre.getText().toString(), apellido.getText().toString(), direccion.getText().toString(), telefono.getText().toString(), correo.getText().toString(), String.valueOf(tipouser.getSelectedItemPosition()), nick.getText().toString(), pass.getText().toString());
        if(bol){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Confirmacion");
            alertDialogBuilder.setMessage("Modificado con Exito");
            alertDialogBuilder.show();
        }else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Confirmacion");
            alertDialogBuilder.setMessage("Error al Modificar");
            alertDialogBuilder.show();
        }
    }

}
