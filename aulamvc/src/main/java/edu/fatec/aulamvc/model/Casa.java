package edu.fatec.aulamvc.model;

import java.util.List;

public class Casa extends Imovel {
    public Casa(String matricula, List<String> proprietarios, double valorVenal, double areaM2) {
        super(matricula, proprietarios, "Casa", valorVenal, areaM2);
    }

    @Override
    public double calcularIPTU() {
        if (getAreaM2() <= 200) {
            return getAreaM2() * 5 + 200 + 0.05 * getValorVenal();
        } else {
            return getAreaM2() * 6 + 350 + 0.08 * getValorVenal();
        }
    }
}
