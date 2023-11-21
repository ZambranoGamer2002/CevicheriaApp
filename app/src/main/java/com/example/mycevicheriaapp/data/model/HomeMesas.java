package com.example.mycevicheriaapp.data.model;

import java.io.Serializable;

public class HomeMesas implements Serializable {
    private String mesaId;
    private String mesaRestaId;
    private String mesaUbicacion;
    private String mesaNumero;
    private String mesaCantidadAsientos;

    public HomeMesas(String mesaId, String mesaRestaId, String mesaUbicacion, String mesaNumero, String mesaCantidadAsientos) {
        this.mesaId = mesaId;
        this.mesaRestaId = mesaRestaId;
        this.mesaUbicacion = mesaUbicacion;
        this.mesaNumero = mesaNumero;
        this.mesaCantidadAsientos = mesaCantidadAsientos;
    }

    public String getMesaId() {
        return mesaId;
    }

    public String getMesaRestaId() {
        return mesaRestaId;
    }

    public String getMesaUbicacion() {
        return mesaUbicacion;
    }

    public String getMesaNumero() {
        return mesaNumero;
    }

    public String getMesaCantidadAsientos() {
        return mesaCantidadAsientos;
    }
}
