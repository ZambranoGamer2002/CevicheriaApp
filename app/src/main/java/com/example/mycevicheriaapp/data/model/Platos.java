package com.example.mycevicheriaapp.data.model;

public class Platos {
    private String platoId;
    private String platiTipoPlatoId;
    private String platoNombre;
    private String pedidoDescrip;
    private String platoPrecio;
    private String platoFoto;
    private String getPlatiTipoPlatoId;
    private String tipoPlatoNombre;

    public Platos(String platoId, String platiTipoPlatoId, String platoNombre, String pedidoDescrip, String platoPrecio, String platoFoto, String getPlatiTipoPlatoId, String tipoPlatoNombre) {
        this.platoId = platoId;
        this.platiTipoPlatoId = platiTipoPlatoId;
        this.platoNombre = platoNombre;
        this.pedidoDescrip = pedidoDescrip;
        this.platoPrecio = platoPrecio;
        this.platoFoto = platoFoto;
        this.getPlatiTipoPlatoId = getPlatiTipoPlatoId;
        this.tipoPlatoNombre = tipoPlatoNombre;
    }

    public String getPlatoId() {
        return platoId;
    }

    public String getPlatiTipoPlatoId() {
        return platiTipoPlatoId;
    }

    public String getPlatoNombre() {
        return platoNombre;
    }

    public String getPedidoDescrip() {
        return pedidoDescrip;
    }

    public String getPlatoPrecio() {
        return platoPrecio;
    }

    public String getPlatoFoto() {
        return platoFoto;
    }

    public String getGetPlatiTipoPlatoId() {
        return getPlatiTipoPlatoId;
    }

    public String getTipoPlatoNombre() {
        return tipoPlatoNombre;
    }
}
