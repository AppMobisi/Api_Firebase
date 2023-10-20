package com.example.apiffirebase.model;

public class Anunciante {
    private int iUsuarioId;
    private String cTelefone;
    private String cEmail;
    private String cCep;
    private String cCpf;
    private double nNota;

    public Anunciante(int iUsuarioId, String cTelefone, String cEmail, String cCep, String cCpf, double nNota) {
        this.iUsuarioId = iUsuarioId;
        this.cTelefone = cTelefone;
        this.cEmail = cEmail;
        this.cCep = cCep;
        this.cCpf = cCpf;
        this.nNota = nNota;
    }

    public int getiUsuarioId() {
        return iUsuarioId;
    }

    public void setiUsuarioId(int iUsuarioId) {
        this.iUsuarioId = iUsuarioId;
    }

    public String getcTelefone() {
        return cTelefone;
    }

    public void setcTelefone(String cTelefone) {
        this.cTelefone = cTelefone;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcCep() {
        return cCep;
    }

    public void setcCep(String cCep) {
        this.cCep = cCep;
    }

    public String getcCpf() {
        return cCpf;
    }

    public void setcCpf(String cCpf) {
        this.cCpf = cCpf;
    }

    public double getnNota() {
        return nNota;
    }

    public void setnNota(double nNota) {
        this.nNota = nNota;
    }
}
