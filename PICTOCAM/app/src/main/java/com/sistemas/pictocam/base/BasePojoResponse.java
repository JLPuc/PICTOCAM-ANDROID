package com.sistemas.pictocam.base;

import java.io.Serializable;

/**
 * Created by Luis Puc on 26/02/2018.
 */

public class BasePojoResponse implements Serializable {

    private String codigoRespuesta;
    private String mensajeRespuesta;

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }
}
