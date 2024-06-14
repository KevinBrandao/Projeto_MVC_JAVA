package edu.fatec.aulamvc.model;

import java.util.List;

public abstract class Imovel {
    private String matricula;
    private List<String> proprietarios;
    private String tipoImovel;
    private double valorVenal;
    private double areaM2;

    public Imovel(String matricula, List<String> proprietarios, String tipoImovel, double valorVenal, double areaM2) {
        this.matricula = matricula;
        this.proprietarios = proprietarios;
        this.tipoImovel = tipoImovel;
        this.valorVenal = valorVenal;
        this.areaM2 = areaM2;
    }

    public String getMatricula() {
        return matricula;
    }

    public List<String> getProprietarios() {
        return proprietarios;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public double getValorVenal() {
        return valorVenal;
    }

    public double getAreaM2() {
        return areaM2;
    }

    public abstract double calcularIPTU();
}
