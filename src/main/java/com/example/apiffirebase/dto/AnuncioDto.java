package com.example.apiffirebase.dto;

import com.example.apiffirebase.model.Anunciante;
import com.example.apiffirebase.model.Anuncio;

public class AnuncioDto {

    public String id;
    public String cFoto;
    public String cTitulo;
    public String cDescricao;
    public Double nPreco;
    public int iTipoDeficiencia;
    public int iAnuncianteId;
    public String cNome;
    public String cTelefone;
    public String cEmail;
    public String cCep;
    public String cCpf;
    public double nNota;
    public boolean favorito;


    public AnuncioDto(Anuncio anuncio, Anunciante anunciante, boolean favorito) {
        this.id = anuncio.getId();
        this.cFoto = anuncio.getcFoto();
        this.cTitulo = anuncio.getcTitulo();
        this.cDescricao = anuncio.getcDescricao();
        this.nPreco = anuncio.getnPreco();
        this.iTipoDeficiencia = anuncio.getiTipoDeficiencia();
        this.iAnuncianteId = anuncio.getiAnuncianteId();
        this.cNome = anunciante.getcNome();
        this.cTelefone = anunciante.getcTelefone();
        this.cEmail = anunciante.getcEmail();
        this.cCep = anunciante.getcCep();
        this.cCpf = anunciante.getcCpf();
        this.nNota = anunciante.getnNota();
        this.favorito = favorito;
    }
}
