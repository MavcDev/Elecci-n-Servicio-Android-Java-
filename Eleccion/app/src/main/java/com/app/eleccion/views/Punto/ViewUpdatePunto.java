package com.app.eleccion.views.Punto;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.eleccion.R;
import com.app.eleccion.dataSource.DsPunto;

import org.json.JSONObject;

public class ViewUpdatePunto extends ActionBarActivity {

    private EditText nombre,direccion;
    private String id;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update_punto);

        nombre = (EditText) findViewById(R.id.editText12);
        direccion = (EditText) findViewById(R.id.editText13);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        if(id != null){
            cargar();
        }

    }

    public  void cargar(){

        try {
            JSONObject jsonObject = new  DsPunto().find(id);
            nombre.setText(jsonObject.getString("nombre"));
            direccion.setText(jsonObject.getString("direccion"));
        }catch (Exception er){
            Toast.makeText(getApplicationContext(), "Error al cargar", Toast.LENGTH_LONG).show();
        }
    }

    public void guardar(View view){

        if(nombre.getText().toString().trim().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("Campo Nombre es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        if(direccion.getText().toString().trim().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("Campo Direccion es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        DsPunto dsPunto  = new DsPunto();
        boolean bol =  dsPunto.update(id, nombre.getText().toString(),direccion.getText().toString());
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
