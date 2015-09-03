package com.app.eleccion.dataSource;

import com.app.eleccion.ListViewComponent.ContentListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Cananguchal Code on 02/08/2015.
 */
public class DsMesaPolitico {

    public String insert(String id,String mesa,String politico,String votos){

        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("mesa", mesa);
            jo.put("politico", politico);
            jo.put("votos", votos);

            Conexion conexion = new Conexion();
            boolean existe = Boolean.parseBoolean(conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/ExisteMesaPolitico",jo.toString()));
            if(existe)
                return "-1";
            return  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/InsertMesaPolitico",jo.toString());
        }catch (Exception er){}
        return  null;
    }

    public  boolean update(String id,String mesa,String politico,String votos){
        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("mesa", mesa);
            jo.put("politico", politico);
            jo.put("votos", votos);

            Conexion conexion = new Conexion();
            String response =  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/UpdateMesaPolitico", jo.toString());
            return  Boolean.parseBoolean(response);
        }catch (Exception er){}
        return  false;
    }

    public  JSONObject find(String id){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/FindMesaPoliticos/" + id);
            return new JSONObject(response);
        }catch (Exception er){}
        return  null;
    }

    public String[] select(){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectMesaPoliticos");
            JSONArray ja = new JSONArray(response);
            String datos[] = new String[ja.length()];
            for(int i=0;i<ja.length();i++){
                JSONObject joData = ja.getJSONObject(i);
                datos[i] = joData.getString("id") + " - " + joData.getString("mesa") + " - " + joData.getString("politico");
            }
            return datos;
        }catch (Exception er){}
        return  null;
    }

    public ArrayList<ContentListView> selectView(){

        ArrayList<ContentListView> listContenView = new ArrayList<ContentListView>();
        try {
            DsPolitico dsPolitico = new DsPolitico();
            DsMesa dsMesa = new DsMesa();
            DsMesaPunto dsMesaPunto = new DsMesaPunto();
            DsPunto dsPunto = new  DsPunto();

            Conexion conexion = new Conexion();
            String jo = conexion.requestGet("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/SelectMesaPoliticos");
            JSONArray ja = new JSONArray(jo);
            for(int i=0;i<ja.length();i++){
                JSONObject object = ja.getJSONObject(i);

                JSONObject politico =  dsPolitico.find(object.getString("politico"));
                JSONObject mesa = dsMesa.find(object.getString("mesa"));
                JSONObject mesaPunto = dsMesaPunto.find(object.getString("mesa"));
                JSONObject punto = dsPunto.find(mesaPunto.getString("punto"));

                ContentListView contentListView = new ContentListView();
                contentListView.setLabel1("Politico : " + politico.getString("nombre"));
                contentListView.setLabel2("Votos : " + object.getString("votos") );
                contentListView.setLabel3("Mesa : " + mesa.getString("numero") + " Punto :" +punto.getString("nombre"));
                contentListView.setParameter(object.getString("id"));
                listContenView.add(contentListView);
            }
        }catch (Exception er){}
        return listContenView;

    }

}
