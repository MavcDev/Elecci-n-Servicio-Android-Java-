package com.app.eleccion.views.Usuario;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.app.eleccion.ListViewComponent.AdapterListView;
import com.app.eleccion.ListViewComponent.ContentListView;
import com.app.eleccion.R;
import com.app.eleccion.dataSource.DsUsuario;

import java.util.ArrayList;
import java.util.Locale;

public class ViewListUsuario extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_usuario);

        final EditText searchView = (EditText) findViewById(R.id.editText23);
        ListView listView = (ListView) findViewById(R.id.listView7);

        ArrayList<ContentListView> listaDatos = new DsUsuario().selectView();

        if(listaDatos == null)
            return;

        final AdapterListView adapterListView = new AdapterListView(this, listaDatos, ViewUpdateUsuario.class);
        listView.setAdapter(adapterListView);


        if(adapterListView == null)
            return;

        searchView.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = searchView.getText().toString().toLowerCase(Locale.getDefault());
                adapterListView.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

    }

}
