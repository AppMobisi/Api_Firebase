package com.example.apiffirebase.model;

public class EstabelecimentoFavorito {

    private String id;
    private Integer iUsuarioId;
    private String cNome;
    private Double nNota;
    private String cEndereco;
    private String cFoto;

    public EstabelecimentoFavorito(Integer iUsuarioId, String cNome, Double nNota, String cEndereco, String cFoto) {
        this.iUsuarioId = iUsuarioId;
        this.cNome = cNome;
        this.nNota = nNota;
        this.cEndereco = cEndereco;
        this.cFoto = cFoto;
    }

    public EstabelecimentoFavorito(){}

    public Integer getiUsuarioId() {
        return iUsuarioId;
    }

    public void setiUsuarioId(Integer iUsuarioId) {
        this.iUsuarioId = iUsuarioId;
    }

    public String getcNome() {
        return cNome;
    }

    public void setcNome(String cNome) {
        this.cNome = cNome;
    }

    public Double getnNota() {
        return nNota;
    }

    public void setnNota(Double nNota) {
        this.nNota = nNota;
    }

    public String getcEndereco() {
        return cEndereco;
    }

    public void setcEndereco(String cEnderco) {
        this.cEndereco = cEnderco;
    }

    public String getcFoto() {
        return cFoto;
    }

    public void setcFoto(String cFoto) {
        this.cFoto = cFoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
