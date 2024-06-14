package edu.fatec.aulamvc.model;

import java.util.List;

public class Terreno extends Imovel {
    public Terreno(String matricula, List<String> proprietarios, double valorVenal, double areaM2) {
        super(matricula, proprietarios, "Terreno", valorVenal, areaM2);
    }

    @Override
    public double calcularIPTU() {
        if (getAreaM2() <= 400) {
            return getAreaM2() * 3 + 500;
        } else {
            return getAreaM2() * 10 + 500;
        }
    }
}
