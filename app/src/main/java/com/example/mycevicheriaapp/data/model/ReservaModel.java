package com.example.mycevicheriaapp.data.model;

public class ReservaModel {
    private String reseId;
    private String reseMesaId;
    private String reseClienId;
    private String reseNumPerso;
    private String reseFecha;
    private String reseHora;
    private String mesaNumeroCell;

    public ReservaModel(String reseId, String reseMesaId, String reseClienId, String reseNumPerso, String reseFecha, String reseHora, String mesaNumeroCell){

        this.reseId = reseId;
        this.reseMesaId = reseMesaId;
        this.reseClienId = reseClienId;
        this.reseNumPerso = reseNumPerso;
        this.reseFecha = reseFecha;
        this.reseHora = reseHora;
        this.mesaNumeroCell = mesaNumeroCell;

    }

    public String getReseId() {
        return reseId;
    }

    public void setReseId(String reseId) {
        this.reseId = reseId;
    }

    public String getReseMesaId() {
        return reseMesaId;
    }

    public void setReseMesaId(String reseMesaId) {
        this.reseMesaId = reseMesaId;
    }

    public String getReseClienId() {
        return reseClienId;
    }

    public void setReseClienId(String reseClienId) {
        this.reseClienId = reseClienId;
    }

    public String getReseNumPerso() {
        return reseNumPerso;
    }

    public void setReseNumPerso(String reseNumPerso) {
        this.reseNumPerso = reseNumPerso;
    }

    public String getReseFecha() {
        return reseFecha;
    }

    public void setReseFecha(String reseFecha) {
        this.reseFecha = reseFecha;
    }

    public String getReseHora() {
        return reseHora;
    }

    public void setReseHora(String reseHora) {
        this.reseHora = reseHora;
    }

    public String getMesaNumeroCell() {
        return mesaNumeroCell;
    }

    public void setMesaNumeroCell(String mesaNumeroCell) {
        this.mesaNumeroCell = mesaNumeroCell;
    }
}
