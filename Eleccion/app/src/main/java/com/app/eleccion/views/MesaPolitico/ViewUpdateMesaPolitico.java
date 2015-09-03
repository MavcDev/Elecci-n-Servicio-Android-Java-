package com.app.eleccion.views.MesaPolitico;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.eleccion.R;
import com.app.eleccion.dataSource.DsMesa;
import com.app.eleccion.dataSource.DsMesaPolitico;
import com.app.eleccion.dataSource.DsMesaPunto;
import com.app.eleccion.dataSource.DsPolitico;
import com.app.eleccion.dataSource.DsPunto;

import org.json.JSONObject;

import java.util.HashMap;

public class ViewUpdateMesaPolitico extends ActionBarActivity {

    private EditText votos;
    private TextView mesa,politico,punto;
    private  final Context context= this;
    private  String id,idMesa,idPolitico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update_mesa_politico);

        votos = (EditText) findViewById(R.id.editText18);
        punto = (TextView) findViewById(R.id.textView30);
        mesa = (TextView) findViewById(R.id.textView31);
        politico = (TextView) findViewById(R.id.textView32);


        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        if(id != null){
            cargar();
        }
    }

    public void cargar(){

        try{
            JSONObject jsonObject = new DsMesaPolitico().find(id);

            idMesa = jsonObject.getString("mesa");
            idPolitico = jsonObject.getString("politico");


            JSONObject jsonmesa =  new DsMesa().find(jsonObject.getString("mesa"));
            mesa.setText("Mesa : " + jsonmesa.getString("numero"));

            JSONObject jsonMesaPunto = new DsMesaPunto().find(jsonObject.getString("mesa"));
            JSONObject jsonPunto = new DsPunto().find(jsonMesaPunto.getString("punto"));
            punto.setText("Punto : " + jsonPunto.getString("nombre"));

            JSONObject jsonPolitico = new DsPolitico().find(jsonObject.getString("politico"));
            politico.setText("Politico : " + jsonPolitico.getString("nombre"));

            votos.setText(jsonObject.getString("votos"));

        }catch (Exception er){
            Log.e("Cargar Votos",er.getMessage());
        }

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
        boolean bol = dsMesaPolitico.update(id, idMesa,idPolitico, votos.getText().toString());
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
