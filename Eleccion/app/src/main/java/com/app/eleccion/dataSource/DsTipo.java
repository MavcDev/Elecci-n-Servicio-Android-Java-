package com.app.eleccion.dataSource;

import android.content.Context;
import android.util.Log;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Cananguchal Code on 02/08/2015.
 */
public class DsTipo {

    public String insert(String id,String nombre){

        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("nombre", nombre);

            Conexion conexion = new Conexion();
            Log.e("Ruta","http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/InsertTipo");
            Log.e("data",jo.toString());

            return  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/InsertTipo",jo.toString());
        }catch (Exception er){}
        return  null;
    }

    public  boolean update(String id,String nombre){
        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("nombre", nombre);

            Conexion conexion = new Conexion();
            String response =  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/UpdateTipo", jo.toString());
            return  Boolean.parseBoolean(response);
        }catch (Exception er){}
        return  false;
    }

    public  JSONObject find(String id){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/FindTipo/" + id);
            return new JSONObject(response);
        }catch (Exception er){}
        return  null;
    }

    public String[] select(){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectTipos");
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

    public HashMap<String,String> MapId(){

        HashMap<String,String> map = new HashMap<String, String>();

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectTipos");
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
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectTipos");
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
