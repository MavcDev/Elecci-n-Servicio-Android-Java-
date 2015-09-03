package com.app.eleccion.views.Partido;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.eleccion.R;
import com.app.eleccion.dataSource.DsPartido;

import org.json.JSONObject;

public class ViewUpdatePartido extends ActionBarActivity {

    private EditText nombre;
    private String id;
    private  final Context context= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update_partido);

        nombre = (EditText) findViewById(R.id.editText14);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        if(id != null){
            cargar();
        }
    }

    private void cargar() {

        try {
            JSONObject jsonObject = new DsPartido().find(id);
            nombre.setText(jsonObject.getString("nombre"));
        }catch(Exception er){
            Toast.makeText(getApplicationContext(), "Error al cargar", Toast.LENGTH_LONG).show();
        }
    }

    public void guardar(View view){

        if(nombre.getText().toString().trim().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("Campo Partido es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        DsPartido dsPartido  = new DsPartido();
        boolean bol =dsPartido.update(id, nombre.getText().toString());
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
