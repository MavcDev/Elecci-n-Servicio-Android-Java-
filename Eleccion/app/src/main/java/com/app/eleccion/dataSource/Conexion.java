package com.app.eleccion.dataSource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by Cananguchal Code on 02/08/2015.
 */
public class Conexion {
    public static String dataResponse;
    public  static  String host;
    public  static  String port;

    public String requestGet(final String uri)  {
        dataResponse = "";
        Thread threadGet =  new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet method =  new HttpGet( new URI(uri) );
                    HttpResponse response = httpClient.execute(method);
                    if ( response != null )
                    {
                        dataResponse = getResponse(response.getEntity());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        threadGet.start();
        while (threadGet.isAlive()){}
        return dataResponse;
    }

    public String requestPost(final String uri, final String data) {
        dataResponse = "";
        Thread threadPost =  new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost method =  new HttpPost( new URI(uri));
                    StringEntity stringEntity = new StringEntity(data,"UTF-8");
                    method.setEntity(stringEntity);;
                    method.setHeader("content-type","application/json");
                    HttpResponse response = httpClient.execute(method);
                    if ( response != null )
                    {
                        dataResponse = getResponse(response.getEntity());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        threadPost.start();
        while (threadPost.isAlive()){}
        return dataResponse;
    }

    private String getResponse( HttpEntity entity )
    {
        String response = "";

        try
        {
            int length = ( int ) entity.getContentLength();
            StringBuffer sb = new StringBuffer( length );
            InputStreamReader isr = new InputStreamReader( entity.getContent(), "UTF-8" );
            char buff[] = new char[length];
            int cnt;
            while ( ( cnt = isr.read( buff, 0, length - 1 ) ) > 0 )
            {
                sb.append( buff, 0, cnt );
            }
            response = sb.toString();
            isr.close();
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        }
        return response;
    }

}
