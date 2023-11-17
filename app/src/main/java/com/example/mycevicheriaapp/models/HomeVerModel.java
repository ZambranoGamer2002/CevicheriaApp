package com.example.mycevicheriaapp.models;

import java.io.Serializable;

public class HomeVerModel implements Serializable {

    String platoId;
    String namePlato;
    String descripcionPlato;
    String precioPlato;
    String tipoPlatoId;
    int imagePlato;


    public HomeVerModel(String platoId, String namePlato, String descripcionPlato, String precioPlato, String tipoPlatoId, int imagePlato) {

        this.platoId = platoId;
        this.namePlato = namePlato;
        this.descripcionPlato = descripcionPlato;
        this.precioPlato = precioPlato;
        this.tipoPlatoId = tipoPlatoId;
        this.imagePlato = imagePlato;

    }

    public String getPlatoId() {
        return platoId;
    }

    public void setPlatoId(String platoId) {
        this.platoId = platoId;
    }

    public String getNamePlato() {return namePlato;}
    public void setNamePlato(String namePlato) {this.namePlato = namePlato;}

    public String getDescripcionPlato() {return descripcionPlato;}

    public void setDescripcionPlato(String descripcionPlato) {this.descripcionPlato = descripcionPlato;}

    public String getPrecioPlato() {return precioPlato;}

    public void setPrecioPlato(String precioPlato) {this.precioPlato = precioPlato;}

    public String getTipoPlatoId() {return tipoPlatoId;}

    public void setTipoPlatoId(String tipoPlatoId) {this.tipoPlatoId = tipoPlatoId;}

    public int getImagePlato() {return imagePlato;}
    public void setImagePlato(int imagePlato) {this.imagePlato = imagePlato;}


}
