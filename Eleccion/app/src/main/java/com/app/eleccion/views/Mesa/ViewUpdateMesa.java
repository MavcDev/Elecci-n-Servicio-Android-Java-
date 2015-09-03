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

import org.json.JSONObject;

import java.util.HashMap;

public class ViewUpdateMesa extends ActionBarActivity {

    private EditText numero,nota;
    private Spinner punto;
    private HashMap<String,String> puntos;
    private String id;
    private  final Context context= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update_mesa);

        numero = (EditText)  findViewById(R.id.editText16);
        nota = (EditText) findViewById(R.id.editText17);
        punto = (Spinner) findViewById(R.id.spinner4);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        cargarDatos();
        if(id != null){
            cargar();
        }
    }

    public void cargar(){

        try{
            JSONObject jsonObject = new DsMesa().find(id);
            numero.setText(jsonObject.getString("numero"));
            nota.setText(jsonObject.getString("nota"));

            JSONObject mensaPunto =   new DsMesaPunto().find(id);

            int posicionPunto =  new DsPunto().posicionId(mensaPunto.getString("punto"));
            punto.setSelection(posicionPunto);

        }catch (Exception er){}
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
        boolean bol = dsMesa.update(id, numero.getText().toString(), nota.getText().toString());
        try{
            JSONObject mesaPunto =  new DsMesaPunto().find(id);
            new DsMesaPunto().update(mesaPunto.getString("id"), id, puntos.get(punto.getSelectedItem().toString()));
        }catch (Exception er){}


        if (bol) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Confirmacion");
            alertDialogBuilder.setMessage("Modificado con Exito");
            alertDialogBuilder.show();
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Confirmacion");
            alertDialogBuilder.setMessage("Error al Modificar");
            alertDialogBuilder.show();
        }
    }

}
