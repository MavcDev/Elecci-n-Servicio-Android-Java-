package com.app.eleccion.views.Tipo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.eleccion.R;
import com.app.eleccion.dataSource.DsTipo;

public class ViewListTipo extends ActionBarActivity {

    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_tipo);

        ListView listView = (ListView) findViewById(R.id.listView);
        DsTipo dsTipo = new DsTipo();
        String listaData[] = dsTipo.select();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) adapter.getItem(position);
                Intent intent  = new Intent(context, ViewUpdateTipo.class);
                intent.putExtra("id",item.split("-")[0].trim());
                startActivity(intent);
            }
        });

    }


}
