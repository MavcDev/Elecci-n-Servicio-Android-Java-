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

import java.util.ArrayList;
import java.util.List;

public class ViewInsertUsuario extends ActionBarActivity {

    private EditText identificacion,nombre,apellido,direccion,telefono,correo,nick,pass;
    private Spinner tipouser;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insert_usuario);

        identificacion = (EditText) findViewById(R.id.editText3);
        nombre= (EditText) findViewById(R.id.editText4);
        apellido = (EditText) findViewById(R.id.editText5);
        direccion = (EditText) findViewById(R.id.editText6);
        telefono = (EditText) findViewById(R.id.editText7);
        correo = (EditText) findViewById(R.id.editText8);
        nick = (EditText) findViewById(R.id.editText9);
        pass = (EditText) findViewById(R.id.editText10);

        tipouser = (Spinner) findViewById(R.id.spinner);

        String tipos[] = new String[2];
        tipos[0] = "Jurado";
        tipos[1] = "Admin";
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipouser.setAdapter(dataAdapter);
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
        String idusuario =  dsUsuario.insert("0", identificacion.getText().toString(), nombre.getText().toString(), apellido.getText().toString(), direccion.getText().toString(), telefono.getText().toString(), correo.getText().toString(), String.valueOf(tipouser.getSelectedItemPosition()), nick.getText().toString(), pass.getText().toString());
        if(idusuario != null){
            if(idusuario.equals("-1")){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Confirmacion");
                alertDialogBuilder.setMessage("Error al Guardar");
                alertDialogBuilder.show();
            }else{
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Confirmacion");
                alertDialogBuilder.setMessage("Guardado con Exito");
                alertDialogBuilder.show();
            }
        }
    }


}
