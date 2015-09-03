package com.app.eleccion.dataSource;

import com.app.eleccion.ListViewComponent.ContentListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Cananguchal Code on 02/08/2015.
 */
public class DsUsuario {

    public String insert(String id,String identificacion,String nombre,String apellido,String direccion,String telefono,String correo, String tipouser,String nick,String pass){

        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("identificacion",identificacion);
            jo.put("nombre", nombre);
            jo.put("apellido", apellido);
            jo.put("direccion",direccion);
            jo.put("telefono",telefono);
            jo.put("correo",correo);
            jo.put("tipouser",tipouser);
            jo.put("nick",nick);
            jo.put("pass",pass);

            Conexion conexion = new Conexion();
                boolean existe = Boolean.parseBoolean(conexion.requestPost("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/ExisteUsuario",jo.toString()));
                if(existe)
                    return "-1";
                return conexion.requestPost("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/InsertUsuario", jo.toString());

        }catch (Exception er){}
        return  null;
    }

    public  boolean update(String id,String identificacion,String nombre,String apellido,String direccion,String telefono,String correo, String tipouser,String nick,String pass){
        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("identificacion",identificacion);
            jo.put("nombre", nombre);
            jo.put("apellido", apellido);
            jo.put("direccion",direccion);
            jo.put("telefono",telefono);
            jo.put("correo",correo);
            jo.put("tipouser",tipouser);
            jo.put("nick",nick);
            jo.put("pass",pass);

            Conexion conexion = new Conexion();
            String response =  conexion.requestPost("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/UpdateUsuario", jo.toString());
            return  Boolean.parseBoolean(response);
        }catch (Exception er){}
        return  false;
    }

    public  JSONObject find(String id){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/FindUsuario/"+id);
            return new JSONObject(response);
        }catch (Exception er){}
        return  null;
    }

    public  JSONObject validarLogin(String nick,String pass){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/ValidarLogin/"+nick+"/"+pass);
            return new JSONObject(response);
        }catch (Exception er){}
        return  null;
    }

    public String[] select(){

        try {
            Conexion conexion = new Conexion();
            String response = conexion.requestGet("http://"+Conexion.host+":"+Conexion.port+"/Eleccion/Servicio.svc/SelectUsuarios");
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
            String jo = conexion.requestGet("http://" + Conexion.host + ":" + Conexion.port + "/Eleccion/Servicio.svc/SelectUsuarios");
            JSONArray ja = new JSONArray(jo);
            for(int i=0;i<ja.length();i++){
                JSONObject object = ja.getJSONObject(i);

                ContentListView contentListView = new ContentListView();
                contentListView.setLabel1("ID : " +object.getString("identificacion"));
                contentListView.setLabel2("Nombre : " + object.getString("nombre"));
                contentListView.setLabel3("Nick : " + object.getString("nick"));
                contentListView.setParameter(object.getString("id"));
                listContenView.add(contentListView);
            }
        }catch (Exception er){}
        return listContenView;

    }

}
