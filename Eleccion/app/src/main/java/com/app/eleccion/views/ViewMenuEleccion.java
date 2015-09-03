package com.app.eleccion.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.app.eleccion.R;
import com.app.eleccion.views.Mesa.ViewInsertMesa;
import com.app.eleccion.views.Mesa.ViewListMesa;
import com.app.eleccion.views.MesaPolitico.ViewInsertMesaPolitico;
import com.app.eleccion.views.MesaPolitico.ViewListMesaPlitico;
import com.app.eleccion.views.Partido.ViewInsertPartido;
import com.app.eleccion.views.Partido.ViewListPartido;
import com.app.eleccion.views.Politico.ViewInsertPolitico;
import com.app.eleccion.views.Politico.ViewListPolitico;
import com.app.eleccion.views.Punto.ViewInsertPunto;
import com.app.eleccion.views.Punto.ViewListPunto;
import com.app.eleccion.views.Tipo.ViewInsertTipo;
import com.app.eleccion.views.Tipo.ViewListTipo;
import com.app.eleccion.views.Tipo.ViewUpdateTipo;
import com.app.eleccion.views.Usuario.ViewInsertUsuario;
import com.app.eleccion.views.Usuario.ViewListUsuario;

public class ViewMenuEleccion extends ActionBarActivity {

    private  final  Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu_eleccion);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewInsertTipo.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewListTipo.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewInsertPunto.class);
                startActivity(intent);
            }
        });

        Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewListPunto.class);
                startActivity(intent);
            }
        });

        Button button21 = (Button) findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewInsertMesa.class);
                startActivity(intent);
            }
        });


        Button button22 = (Button) findViewById(R.id.button22);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewListMesa.class);
                startActivity(intent);
            }
        });


        Button button13 = (Button) findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewInsertPartido.class);
                startActivity(intent);
            }
        });

        Button button14 = (Button) findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewListPartido.class);
                startActivity(intent);
            }
        });

        Button button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewInsertPolitico.class);
                startActivity(intent);
            }
        });

        Button button16 = (Button) findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewListPolitico.class);
                startActivity(intent);
            }
        });

        Button button17 = (Button) findViewById(R.id.button17);
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewInsertMesaPolitico.class);
                startActivity(intent);
            }
        });

        Button button18 = (Button) findViewById(R.id.button18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewListMesaPlitico.class);
                startActivity(intent);
            }
        });

        Button button19 = (Button) findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewInsertUsuario.class);
                startActivity(intent);
            }
        });

        Button button20 = (Button) findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewListUsuario.class);
                startActivity(intent);
            }
        });


    }


}
