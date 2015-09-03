package com.app.eleccion.views.Partido;

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
import com.app.eleccion.dataSource.DsPartido;
import com.app.eleccion.dataSource.DsPunto;

public class ViewInsertPartido extends ActionBarActivity {

    private EditText nombre;
    private  final Context context= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insert_partido);

        nombre = (EditText) findViewById(R.id.editText14);
    }

    public void guardar(View view) {

        if(nombre.getText().toString().trim().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("Campo Partido es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        DsPartido dsPartido = new DsPartido();
        String idPartido = dsPartido.insert("0", nombre.getText().toString());
        if (idPartido != null) {
            if (idPartido.equals("-1")) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Confirmacion");
                alertDialogBuilder.setMessage("Error al Guardar");
                alertDialogBuilder.show();
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Confirmacion");
                alertDialogBuilder.setMessage("Guardado con Exito");
                alertDialogBuilder.show();
            }
        }
    }

}
