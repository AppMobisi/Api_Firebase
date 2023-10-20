package com.example.apiffirebase.dto;

import com.example.apiffirebase.model.Anunciante;
import com.example.apiffirebase.model.Anuncio;

public class AnuncioDto {
    public int iId;
    public byte[] cFoto;
    public String cTitulo;
    public String cDescricao;
    public Double nPreco;
    public int iTipoDeficiencia;
    public int iAnuncianteId;
    public String cTelefone;
    public String cEmail;
    public String cCep;
    public String cCpf;
    public double nNota;


    public AnuncioDto(Anuncio anuncio, Anunciante anunciante) {
        this.iId = anuncio.getiId();
        this.cFoto = anuncio.getcFoto();
        this.cTitulo = anuncio.getcTitulo();
        this.cDescricao = anuncio.getcDescricao();
        this.nPreco = anuncio.getnPreco();
        this.iTipoDeficiencia = anuncio.getiTipoDeficiencia();
        this.iAnuncianteId = anuncio.getiAnuncianteId();
        this.cTelefone = anunciante.getcTelefone();
        this.cEmail = anunciante.getcEmail();
        this.cCep = anunciante.getcCep();
        this.cCpf = anunciante.getcCpf();
        this.nNota = anunciante.getnNota();
    }
}
