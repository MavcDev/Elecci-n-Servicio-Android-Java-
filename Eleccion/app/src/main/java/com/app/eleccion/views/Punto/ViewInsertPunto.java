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

public class ViewInsertPunto extends ActionBarActivity {

    private EditText nombre,direccion;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insert_punto);

        nombre = (EditText) findViewById(R.id.editText12);
        direccion = (EditText) findViewById(R.id.editText13);

    }

    public void guardar(View view) {

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

        DsPunto dsPunto = new DsPunto();
        String idPunto =  dsPunto.insert("0",nombre.getText().toString(),direccion.getText().toString());
        if(idPunto != null){
            if(idPunto.equals("-1")){
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
