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
import com.app.eleccion.views.MesaPolitico.ViewInsertMesaPolitico;

public class ViewMenuUsuario extends ActionBarActivity {

    private  final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu_usuario);

        Button button = (Button) findViewById(R.id.button23);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewInsertMesaPolitico.class);
                startActivity(intent);
            }
        });

    }

}
