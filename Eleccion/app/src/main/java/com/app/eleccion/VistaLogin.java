package com.app.eleccion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.eleccion.dataSource.Conexion;
import com.app.eleccion.dataSource.DsUsuario;
import com.app.eleccion.views.ViewMenuEleccion;
import com.app.eleccion.views.ViewMenuUsuario;

import org.json.JSONObject;


public class VistaLogin extends ActionBarActivity {

    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_login);

        Conexion.host = "192.168.1.20";
        Conexion.port = "2058";

        Button button = (Button) findViewById(R.id.button);
        final EditText nick = (EditText) findViewById(R.id.editText);
        final  EditText pass = (EditText) findViewById(R.id.editText2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject =  new DsUsuario().validarLogin(nick.getText().toString(),pass.getText().toString());
                try {

                    if(jsonObject.getString("tipouser").equals("1")){
                        Intent intent = new Intent(context, ViewMenuEleccion.class);
                        startActivity(intent);
                    }else if(jsonObject.getString("tipouser").equals("0")){
                        Intent intent = new Intent(context, ViewMenuUsuario.class);
                        startActivity(intent);
                    }
                }catch (Exception er){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Confirmacion");
                    alertDialogBuilder.setMessage("Error al intetar Logear");
                    alertDialogBuilder.show();
                }
            }
        });



    }

}
