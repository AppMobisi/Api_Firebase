package com.example.apiffirebase.model;

public class Anuncio {

    public String id;
    private String  cFoto;
    private String cTitulo;
    private String cDescricao;
    private Double nPreco;
    private int iTipoDeficienciaId;
    private int iAnuncianteId;

    public Anuncio( String cFoto, String cTitulo, String cDescricao, Double nPreco,  int iTipoDeficienciaId, int iAnuncianteId) {
        this.cFoto = cFoto;
        this.cTitulo = cTitulo;
        this.cDescricao = cDescricao;
        this.nPreco = nPreco;
        this.iTipoDeficienciaId = iTipoDeficienciaId;
        this.iAnuncianteId = iAnuncianteId;
    }

    public Anuncio(){}
    public String getcFoto() {
        return cFoto;
    }

    public void setcFoto(String cFoto) {
        this.cFoto = cFoto;
    }

    public String getcTitulo() {
        return cTitulo;
    }

    public void setcTitulo(String cTitulo) {
        this.cTitulo = cTitulo;
    }

    public String getcDescricao() {
        return cDescricao;
    }

    public void setcDescricao(String cDescricao) {
        this.cDescricao = cDescricao;
    }

    public Double getnPreco() {
        return nPreco;
    }

    public void setnPreco(Double cPreco) {
        this.nPreco = cPreco;
    }


    public int getiTipoDeficienciaId() {
        return iTipoDeficienciaId;
    }

    public void setiTipoDeficienciaId(int iTipoDeficienciaId) {
        this.iTipoDeficienciaId = iTipoDeficienciaId;
    }

    public int getiAnuncianteId() {
        return iAnuncianteId;
    }

    public void setiAnuncianteId(int iAnuncianteId) {
        this.iAnuncianteId = iAnuncianteId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
