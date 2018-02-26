package com.sistemas.pictocam.Conexion;

import com.sistemas.pictocam.base.BasePojoResponse;

/**
 * Created by Luis Puc on 26/02/2018.
 */

public class PojoResponse extends BasePojoResponse{
    private  String respuesta;

    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
