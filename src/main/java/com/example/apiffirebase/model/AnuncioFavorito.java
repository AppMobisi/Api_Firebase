package com.example.apiffirebase.model;

public class AnuncioFavorito {
    private Integer iUsuarioId;
    private String cAnuncioId;

    public AnuncioFavorito(){}
    public AnuncioFavorito(Integer iUsuarioId, String cAnuncioId) {
        this.iUsuarioId = iUsuarioId;
        this.cAnuncioId = cAnuncioId;
    }

    public Integer getiUsuarioId() {
        return iUsuarioId;
    }

    public void setiUsuarioId(Integer iUsuarioId) {
        this.iUsuarioId = iUsuarioId;
    }

    public String getcAnuncioId() {
        return cAnuncioId;
    }

    public void setcAnuncioId(String cAnuncioId) {
        this.cAnuncioId = cAnuncioId;
    }
}
