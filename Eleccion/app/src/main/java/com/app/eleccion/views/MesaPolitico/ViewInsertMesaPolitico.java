package com.app.eleccion.views.MesaPolitico;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.eleccion.R;
import com.app.eleccion.dataSource.DsMesaPolitico;
import com.app.eleccion.dataSource.DsMesaPunto;
import com.app.eleccion.dataSource.DsPolitico;
import com.app.eleccion.dataSource.DsPunto;

import java.util.HashMap;

public class ViewInsertMesaPolitico extends ActionBarActivity {

    private Spinner punto,mesa,politico;
    private EditText votos;
    private HashMap<String,String> puntos;
    private HashMap<String,String> mesas;
    private HashMap<String,String> politicos;
    private  final Context context= this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insert_mesa_politico);

        votos = (EditText) findViewById(R.id.editText18);
        punto = (Spinner) findViewById(R.id.spinner5);
        mesa = (Spinner) findViewById(R.id.spinner6);
        politico = (Spinner) findViewById(R.id.spinner7);

        cargarDatos();

        final Context context = this;

        punto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String idPunto = puntos.get(punto.getSelectedItem().toString());
                String dataMesa[] =  new DsMesaPunto().mesasPorPunto(idPunto);
                ArrayAdapter<String> dataAdapterMesa = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, dataMesa);
                dataAdapterMesa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mesa.setAdapter(dataAdapterMesa);
                mesas = new DsMesaPunto().MapId(idPunto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void cargarDatos(){

        String listaPunto[] = new DsPunto().select();
        ArrayAdapter<String> dataAdapterPunto = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaPunto);
        dataAdapterPunto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        punto.setAdapter(dataAdapterPunto);
        puntos = new DsPunto().MapId();


        String listaPoliticos[] = new DsPolitico().select();
        ArrayAdapter<String> dataAdapterPolitico = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaPoliticos);
        dataAdapterPolitico.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        politico.setAdapter(dataAdapterPolitico);
        politicos = new DsPolitico().MapId();

    }

    public void guardar(View view) {

        if(votos.getText().toString().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("Campo Votos es obligatorio");
            alertDialogBuilder.show();
            return;
        }


        DsMesaPolitico dsMesaPolitico = new DsMesaPolitico();
        String idMesaPolitico = dsMesaPolitico.insert("0", mesas.get(mesa.getSelectedItem().toString()), politicos.get(politico.getSelectedItem().toString()), votos.getText().toString());
        if (idMesaPolitico != null) {
            if (idMesaPolitico.equals("-1")) {
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
