package edu.fatec.aulamvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fatec.aulamvc.model.Imovel;

public class ImovelController {
    private Map<String, Imovel> imoveis;

    public ImovelController() {
        this.imoveis = new HashMap<>();
    }

    public void cadastrarImovel(Imovel imovel) {
        imoveis.put(imovel.getMatricula(), imovel);
    }

    public List<Imovel> listarImoveis() {
        return new ArrayList<>(imoveis.values());
    }

    public Imovel consultarPorMatricula(String matricula) {
        return imoveis.get(matricula);
    }

    public void removerPorMatricula(String matricula) {
        imoveis.remove(matricula);
    }

    public double calcularIPTU(String matricula) {
        Imovel imovel = consultarPorMatricula(matricula);
        return (imovel != null) ? imovel.calcularIPTU() : 0;
    }

    public List<Double> calcularIPTUProximosAnos(String matricula) {
        Imovel imovel = consultarPorMatricula(matricula);
        List<Double> valoresIPTU = new ArrayList<>();
        if (imovel != null) {
            double iptuAtual = imovel.calcularIPTU();
            for (int i = 0; i < 6; i++) {
                valoresIPTU.add(iptuAtual * Math.pow(1.07, i));
            }
        }
        return valoresIPTU;
    }

    public Map<String, Double> valorVenalTotalPorTipo() {
        double totalCasa = 0, totalApartamento = 0, totalTerreno = 0;
        for (Imovel imovel : imoveis.values()) {
            switch (imovel.getTipoImovel()) {
                case "Casa":
                    totalCasa += imovel.getValorVenal();
                    break;
                case "Apartamento":
                    totalApartamento += imovel.getValorVenal();
                    break;
                case "Terreno":
                    totalTerreno += imovel.getValorVenal();
                    break;
            }
        }
        Map<String, Double> totalPorTipo = new HashMap<>();
        totalPorTipo.put("Casa", totalCasa);
        totalPorTipo.put("Apartamento", totalApartamento);
        totalPorTipo.put("Terreno", totalTerreno);
        return totalPorTipo;
    }
}
