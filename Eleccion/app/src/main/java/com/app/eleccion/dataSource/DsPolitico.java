package com.app.eleccion.dataSource;

import com.app.eleccion.ListViewComponent.ContentListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cananguchal Code on 02/08/2015.
 */
public class DsPolitico {

    public String insert(String id,String nombre,String partido, String tipo){

        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("nombre", nombre);
            jo.put("partido",partido);
            jo.put("tipo",tipo);

            Conexion conexion = new Conexion();
            return  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/InsertPolitico",jo.toString());
        }catch (Exception er){}
        return  null;
    }

    public  boolean update(String id,String nombre,String partido, String tipo){
        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("nombre", nombre);
            jo.put("partido",partido);
            jo.put("tipo",tipo);

            Conexion conexion = new Conexion();
            String response =  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/UpdatePolitico", jo.toString());
            return  Boolean.parseBoolean(response);
        }catch (Exception er){}
        return  false;
    }

    public  JSONObject find(String id){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/FindPolitico/" + id);
            return new JSONObject(response);
        }catch (Exception er){}
        return  null;
    }

    public String[] select(){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectPoliticos");
            JSONArray ja = new JSONArray(response);
            String datos[] = new String[ja.length()];
            for(int i=0;i<ja.length();i++){
                JSONObject joData = ja.getJSONObject(i);
                datos[i] = joData.getString("id") + " - " + joData.getString("nombre");
            }
            return datos;
        }catch (Exception er){}
        return  null;
    }

    public ArrayList<ContentListView> selectView(){

        ArrayList<ContentListView> listContenView = new ArrayList<ContentListView>();
        try {
            DsPartido dsPartido = new DsPartido();
            DsTipo dsTipo = new DsTipo();

            Conexion conexion = new Conexion();
            String jo = conexion.requestGet("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/SelectPoliticos");
            JSONArray ja = new JSONArray(jo);
            for(int i=0;i<ja.length();i++){
                JSONObject object = ja.getJSONObject(i);

                JSONObject partido = dsPartido.find(object.getString("partido"));
                JSONObject tipo = dsTipo.find(object.getString("tipo"));

                ContentListView contentListView = new ContentListView();
                contentListView.setLabel1("Nombre : " + object.getString("nombre"));
                contentListView.setLabel2("Partido : " + partido.getString("nombre"));
                contentListView.setLabel3("Tipo : " + tipo.getString("nombre"));
                contentListView.setParameter(object.getString("id"));
                listContenView.add(contentListView);
            }
        }catch (Exception er){}
        return listContenView;

    }

    public HashMap<String,String> MapId(){

        HashMap<String,String> map = new HashMap<String, String>();

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectPoliticos");
            JSONArray ja = new JSONArray(response);
            String datos[] = new String[ja.length()];
            for(int i=0;i<ja.length();i++){
                JSONObject joData = ja.getJSONObject(i);
                datos[i] = joData.getString("id") + " - " + joData.getString("nombre");
                map.put(datos[i],joData.getString("id"));
            }
        }catch (Exception er){}
        return  map;
    }

    public  int posicionId(String id){

        int posicion = 0;
        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectPoliticos");
            JSONArray ja = new JSONArray(response);
            for(int i=0;i<ja.length();i++){
                JSONObject joData = ja.getJSONObject(i);
                if(joData.getString("id").equals(id)){
                    return  i;
                }
            }
        }catch (Exception er){}
        return  -1;
    }

}
