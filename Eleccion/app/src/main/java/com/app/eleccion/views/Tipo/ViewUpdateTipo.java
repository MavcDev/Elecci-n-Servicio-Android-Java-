package com.app.eleccion.views.Tipo;

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
import com.app.eleccion.dataSource.DsTipo;

import org.json.JSONObject;

public class ViewUpdateTipo extends ActionBarActivity {

    private EditText nombre;
    private String id;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update_tipo);

        nombre = (EditText) findViewById(R.id.editText11);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        if(id != null){
            cargar();
        }
    }

    public  void cargar(){

        try {
            JSONObject jsonObject = new DsTipo().find(id);
            nombre.setText(jsonObject.getString("nombre"));

        }catch (Exception er){
            Toast.makeText(getApplicationContext(), "Error al carga el Usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void guardar(View view){

        if(nombre.getText().toString().isEmpty()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("Campo Nombre es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        DsTipo dsTipo = new DsTipo();
        boolean bol =  dsTipo.update(id, nombre.getText().toString());
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
