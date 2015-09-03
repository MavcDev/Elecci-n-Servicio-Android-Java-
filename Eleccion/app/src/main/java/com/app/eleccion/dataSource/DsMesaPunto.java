package com.app.eleccion.dataSource;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Cananguchal Code on 02/08/2015.
 */
public class DsMesaPunto {

    public String insert(String id,String mesa,String punto){

        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("mesa", mesa.replaceAll("\"","").trim());
            jo.put("punto", punto);

            Log.e("MesaPunto",jo.toString());

            Conexion conexion = new Conexion();
             return  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/InsertMesaPunto",jo.toString());
        }catch (Exception er){}
        return  null;
    }

    public  boolean update(String id,String mesa,String punto){
        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("mesa", mesa);
            jo.put("punto", punto);

            Conexion conexion = new Conexion();
            String response =  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/UpdateMesaPunto", jo.toString());
            return  Boolean.parseBoolean(response);
        }catch (Exception er){}
        return  false;
    }

    public  JSONObject find(String id){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/FindMesaPunto/" + id);
            return new JSONObject(response);
        }catch (Exception er){}
        return  null;
    }

    public String[] select(){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectMesaPuntos");
            JSONArray ja = new JSONArray(response);
            String datos[] = new String[ja.length()];
            for(int i=0;i<ja.length();i++){
                JSONObject joData = ja.getJSONObject(i);
                datos[i] = joData.getString("id");
            }
            return datos;
        }catch (Exception er){}
        return  null;
    }

    public String[] mesasPorPunto(String punto){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/MesasPunto/"+punto);
            JSONArray ja = new JSONArray(response);
            String datos[] = new String[ja.length()];
            for(int i=0;i<ja.length();i++){
                JSONObject joData = ja.getJSONObject(i);
                datos[i] = joData.getString("numero");
            }
            return datos;
        }catch (Exception er){}
        return  null;
    }

    public HashMap<String,String> MapId(String punto){

        HashMap<String,String> map = new HashMap<String, String>();

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/MesasPunto/"+punto);
            JSONArray ja = new JSONArray(response);
            for(int i=0;i<ja.length();i++){
                JSONObject joData = ja.getJSONObject(i);
                map.put(joData.getString("numero"),joData.getString("id"));
            }
        }catch (Exception er){}
        return  map;
    }

    public int posicionId(String punto, String mesa){
        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/MesasPunto/"+punto);
            JSONArray ja = new JSONArray(response);
            for(int i=0;i<ja.length();i++){
                JSONObject joData = ja.getJSONObject(i);
                if(joData.getString("id").equals(mesa)){
                    return  i;
                }
            }
        }catch (Exception er){}
        return  -1;
    }


}
