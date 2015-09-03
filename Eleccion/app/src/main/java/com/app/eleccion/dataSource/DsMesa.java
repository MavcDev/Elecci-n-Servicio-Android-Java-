package com.app.eleccion.dataSource;

import com.app.eleccion.ListViewComponent.ContentListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Cananguchal Code on 02/08/2015.
 */
public class DsMesa {

    public String insert(String id,String numero,String nota){

        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("numero", numero);
            jo.put("nota",nota);

            Conexion conexion = new Conexion();
            return  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/InsertMesa",jo.toString());
        }catch (Exception er){}
        return  null;
    }

    public  boolean update(String id,String numero,String nota){
        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("numero", numero);
            jo.put("nota",nota);


            Conexion conexion = new Conexion();
            String response =  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/UpdateMesa", jo.toString());
            return  Boolean.parseBoolean(response);
        }catch (Exception er){}
        return  false;
    }

    public  JSONObject find(String id){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/FindMesa/" + id);
            return new JSONObject(response);
        }catch (Exception er){}
        return  null;
    }

    public String[] select(){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectMesas");
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

    public ArrayList<ContentListView> selectView(){

        ArrayList<ContentListView> listContenView = new ArrayList<ContentListView>();
        try {
            DsMesaPunto dsMesaPunto = new DsMesaPunto();
            DsPunto dsPunto = new DsPunto();

            Conexion conexion = new Conexion();
            String jo = conexion.requestGet("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/SelectMesas");
            JSONArray ja = new JSONArray(jo);
            for(int i=0;i<ja.length();i++){
                JSONObject object = ja.getJSONObject(i);

                JSONObject mesaPunto =  dsMesaPunto.find(object.getString("id"));
                JSONObject punto = dsPunto.find(mesaPunto.getString("punto"));

                ContentListView contentListView = new ContentListView();
                contentListView.setLabel1("ID : " + object.getString("id"));
                contentListView.setLabel2("Numero : " + object.getString("numero"));
                contentListView.setLabel3("Punto : " + punto.getString("nombre"));
                contentListView.setParameter(object.getString("id"));
                listContenView.add(contentListView);
            }
        }catch (Exception er){}
        return listContenView;

    }

}
