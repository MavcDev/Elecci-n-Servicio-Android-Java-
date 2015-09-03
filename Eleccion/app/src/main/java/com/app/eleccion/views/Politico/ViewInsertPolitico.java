package com.app.eleccion.views.Politico;

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
import com.app.eleccion.dataSource.DsPartido;
import com.app.eleccion.dataSource.DsPolitico;
import com.app.eleccion.dataSource.DsTipo;

import java.util.HashMap;

public class ViewInsertPolitico extends ActionBarActivity {

    private EditText nombre;
    private Spinner partido,tipo;
    private HashMap<String,String> partidos,tipos;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insert_politico);

        nombre = (EditText) findViewById(R.id.editText15);
        partido = (Spinner) findViewById(R.id.spinner2);
        tipo = (Spinner) findViewById(R.id.spinner3);

        cargarDatos();
    }

    public  void cargarDatos(){

        String listaPartido[] =  new DsPartido().select();
        ArrayAdapter<String> dataAdapterPartido = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaPartido);
        dataAdapterPartido.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        partido.setAdapter(dataAdapterPartido);

        String listaTipo[] = new DsTipo().select();
        ArrayAdapter<String> dataAdapterTipo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaTipo);
        dataAdapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(dataAdapterTipo);

        tipos = new DsTipo().MapId();
        partidos = new DsPartido().MapId();
    }

    public void guardar(View view) {

        if(nombre.getText().toString().toString().isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("Campo Nombre es obligatorio");
            alertDialogBuilder.show();
            return;
        }

        DsPolitico dsPolitico = new DsPolitico();
        String idPolitico = dsPolitico.insert("0", nombre.getText().toString(),partidos.get(partido.getSelectedItem().toString()),tipos.get(tipo.getSelectedItem().toString()));
        if (idPolitico != null) {
            if (idPolitico.equals("-1")) {
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
