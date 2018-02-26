package com.sistemas.pictocam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

/**
 * Created by Luis Puc on 26/02/2018.
 */

public class Constantes {
    public final  static String CadenaConexion              = "http://mastercoder.azurewebsites.net//UI/WsMCTuristic.asmx/";

    // Constantes de mensajes generales
    public static final String[] CODIGO_EXITO               = new String[]{"000000","PETICION EXITOSA"};

    // Errores generales en las capas
    public static final String[] CODIGO_ERROR_SERVICIO      = {"002","ERROR: EN LA CAPA SERVICIO"};
    public static final String[] CODIGO_ERROR_DAO           = {"003","ERROR: EN LA CAPA DAO"};
    public static final String[] CODIGO_ERROR_CONEXION      = {"404","ERROR: ERROR DE CONEXION"};

    //Métodos Utilidades
    public static void CrearRuta(Activity activity,Double Latitud, Double Longitud)
    {
        //Generar las rutas en el api de google maps
        String Url1 = "google.navigation:q="+Latitud +","+Longitud+"&mode=w";
        Uri gmmIntentUri = Uri.parse(Url1);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        activity.startActivity(mapIntent);
    }

    public static Bitmap decodificarImagen(String Foto)
    {
        //Creamos un byte[] Del dato Foto que proviene del la clase PictureAdapterRecyclerView
        byte[] decodeString = Base64.decode(Foto,Base64.DEFAULT);
        //Decodifica el byte[] y lo convierte a un objeto Bitmap.
        Bitmap decoded = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        return  decoded;
    }
}
