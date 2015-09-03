package com.app.eleccion.views.Mesa;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.eleccion.R;
import com.app.eleccion.dataSource.DsMesa;
import com.app.eleccion.dataSource.DsMesaPunto;
import com.app.eleccion.dataSource.DsPunto;

import java.util.HashMap;

public class ViewInsertMesa extends ActionBarActivity {

    private EditText numero,nota;
    private Spinner punto;
    private HashMap<String,String> puntos;
    private  final Context context= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insert_mesa);

        numero = (EditText)  findViewById(R.id.editText16);
        nota = (EditText) findViewById(R.id.editText17);
        punto = (Spinner) findViewById(R.id.spinner4);

        cargarDatos();
    }

    public void cargarDatos(){

        String listaPunto[] = new DsPunto().select();
        ArrayAdapter<String> dataAdapterPunto = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaPunto);
        dataAdapterPunto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        punto.setAdapter(dataAdapterPunto);
        puntos = new DsPunto().MapId();
    }

    public void guardar(View view) {

        if(numero.getText().toString().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("Campo Numero es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        DsMesa dsMesa = new DsMesa();

        String idMesa = dsMesa.insert("0", numero.getText().toString(), nota.getText().toString());
        String idMesaPuinto = new DsMesaPunto().insert("0",idMesa,puntos.get(punto.getSelectedItem().toString()));
        if (idMesa != null && idMesaPuinto != null) {
            if (idMesa.equals("-1")) {
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
