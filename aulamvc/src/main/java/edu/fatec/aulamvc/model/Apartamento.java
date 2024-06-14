package edu.fatec.aulamvc.model;

import java.util.List;

public class Apartamento extends Imovel {
    public Apartamento(String matricula, List<String> proprietarios, double valorVenal, double areaM2) {
        super(matricula, proprietarios, "Apartamento", valorVenal, areaM2);
    }

    @Override
    public double calcularIPTU() {
        if (getAreaM2() <= 150) {
            return getAreaM2() * 2 + 200 + 0.03 * getValorVenal();
        } else {
            return getAreaM2() * 4 + 350 + 0.10 * getValorVenal();
        }
    }
}
