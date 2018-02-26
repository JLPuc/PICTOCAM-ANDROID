package com.sistemas.pictocam.Conexion;

import com.sistemas.pictocam.Constantes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Luis Puc on 26/02/2018.
 */

public class ConexionDAO {
    private static ConexionDAO mInstance = null;

    public static ConexionDAO getInstance(){
        if(mInstance == null) {
            mInstance = new ConexionDAO();
        }
        return mInstance;
    }

    public PojoResponse Conectar(String cadenaConexion)
    {
        PojoResponse pojoResponse = new PojoResponse();
        HttpURLConnection conexion = null;
        BufferedReader reader = null;
        try{
            URL url = new URL(cadenaConexion);
            conexion = (HttpURLConnection)url.openConnection();
            conexion.connect();
            InputStream stream = conexion.getInputStream();
            reader = new BufferedReader((new InputStreamReader(stream)));
            StringBuffer buffer = new StringBuffer();
            String Line = "";
            while ((Line = reader.readLine()) != null){
                buffer.append(Line);
            }
            pojoResponse.setCodigoRespuesta(Constantes.CODIGO_EXITO[0]);
            pojoResponse.setMensajeRespuesta(Constantes.CODIGO_EXITO[1]);
            pojoResponse.setRespuesta(buffer.toString());
            return pojoResponse;
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            //Error de conexion
            e.printStackTrace();
            pojoResponse.setCodigoRespuesta(Constantes.CODIGO_ERROR_CONEXION[0]);
            pojoResponse.setMensajeRespuesta(Constantes.CODIGO_ERROR_CONEXION[1]);
        }
        finally
        {
            if(conexion != null){
                conexion.disconnect();
            }
            try{
                if(reader!= null){
                    reader.close();
                }
            }
            catch (IOException e){
                pojoResponse.setCodigoRespuesta(Constantes.CODIGO_ERROR_DAO[0]);
                pojoResponse.setMensajeRespuesta(Constantes.CODIGO_ERROR_DAO[1]);
                e.printStackTrace();
            }
        }
        return pojoResponse;
    }
}
