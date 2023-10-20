package com.example.apiffirebase.model;

public class EstabelecimentoFavorito {

    private Integer iUsuarioId;
    private Integer iEstabelecimentoId;
    private String name;
    private Double score;
    private String cep;
    private String neighborhood;
    private String city;
    private String state;
    private Integer iTipoEstabelecimento;
    private String cNomeTipoEstabelecimento;

    public EstabelecimentoFavorito(Integer iUsuarioId, Integer iEstabelecimentoId, String name, Double score, String cep, String neighborhood, String city, String state, Integer iTipoEstabelecimento, String cNomeTipoEstabelecimento) {
        this.iEstabelecimentoId = iEstabelecimentoId;
        this.iUsuarioId = iUsuarioId;
        this.name = name;
        this.score = score;
        this.cep = cep;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.iTipoEstabelecimento = iTipoEstabelecimento;
        this.cNomeTipoEstabelecimento = cNomeTipoEstabelecimento;
    }

    public EstabelecimentoFavorito() {
    }

    public Integer getiUsuarioId() {
        return iUsuarioId;
    }

    public void setiUsuarioId(Integer iUsuarioId) {
        this.iUsuarioId = iUsuarioId;
    }

    public Integer getiEstabelecimentoId() {
        return iEstabelecimentoId;
    }

    public void setiEstabelecimentoId(Integer iEstabelecimentoId) {
        this.iEstabelecimentoId = iEstabelecimentoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getiTipoEstabelecimento() {
        return iTipoEstabelecimento;
    }

    public void setiTipoEstabelecimento(Integer iTipoEstabelecimento) {
        this.iTipoEstabelecimento = iTipoEstabelecimento;
    }

    public String getcNomeTipoEstabelecimento() {
        return cNomeTipoEstabelecimento;
    }

    public void setcNomeTipoEstabelecimento(String cNomeTipoEstabelecimento) {
        this.cNomeTipoEstabelecimento = cNomeTipoEstabelecimento;
    }
}
