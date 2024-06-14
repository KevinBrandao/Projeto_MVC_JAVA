package edu.fatec.aulamvc;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.fatec.aulamvc.controller.ImovelController;
import edu.fatec.aulamvc.model.Apartamento;
import edu.fatec.aulamvc.model.Casa;
import edu.fatec.aulamvc.model.Imovel;
import edu.fatec.aulamvc.model.Terreno;

public class Main {
    public static void main(String[] args) {
        ImovelController controller = new ImovelController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Cadastrar imóvel");
            System.out.println("2. Listar imóveis");
            System.out.println("3. Consultar imóvel por matrícula");
            System.out.println("4. Remover imóvel por matrícula");
            System.out.println("5. Calcular IPTU por matrícula");
            System.out.println("6. Calcular IPTU próximos 5 anos por matrícula");
            System.out.println("7. Listar valor venal total por tipo de imóvel");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (escolha) {
                case 1:
                    System.out.print("Matrícula: ");
                    String matricula = scanner.nextLine();
                    System.out.print("Proprietários (separados por vírgula): ");
                    String proprietariosStr = scanner.nextLine();
                    List<String> proprietarios = List.of(proprietariosStr.split(","));
                    System.out.print("Tipo de imóvel (casa, apartamento, terreno): ");
                    String tipoImovel = scanner.nextLine().toLowerCase();
                    System.out.print("Valor venal: ");
                    double valorVenal = scanner.nextDouble();
                    System.out.print("Área em metros quadrados: ");
                    double areaM2 = scanner.nextDouble();

                    Imovel imovel = null;
                    switch (tipoImovel) {
                        case "casa":
                            imovel = new Casa(matricula, proprietarios, valorVenal, areaM2);
                            break;
                        case "apartamento":
                            imovel = new Apartamento(matricula, proprietarios, valorVenal, areaM2);
                            break;
                        case "terreno":
                            imovel = new Terreno(matricula, proprietarios, valorVenal, areaM2);
                            break;
                        default:
                            System.out.println("Tipo de imóvel inválido.");
                            continue;
                    }

                    controller.cadastrarImovel(imovel);
                    System.out.println("Imóvel cadastrado com sucesso.");
                    break;

                case 2:
                    List<Imovel> imoveis = controller.listarImoveis();
                    for (Imovel im : imoveis) {
                        System.out.println(im.getMatricula() + " - " + im.getTipoImovel() + " - " +
                                im.getValorVenal() + " - " + im.getAreaM2() + "m²");
                    }
                    break;

                case 3:
                    System.out.print("Matrícula: ");
                    matricula = scanner.nextLine();
                    Imovel im = controller.consultarPorMatricula(matricula);
                    if (im != null) {
                        System.out.println(im.getMatricula() + " - " + im.getTipoImovel() + " - " +
                                im.getValorVenal() + " - " + im.getAreaM2() + "m²");
                    } else {
                        System.out.println("Imóvel não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Matrícula: ");
                    matricula = scanner.nextLine();
                    controller.removerPorMatricula(matricula);
                    System.out.println("Imóvel removido com sucesso.");
                    break;

                case 5:
                    System.out.print("Matrícula: ");
                    matricula = scanner.nextLine();
                    double iptu = controller.calcularIPTU(matricula);
                    if (iptu > 0) {
                        System.out.printf("O valor do IPTU é: %.2f%n", iptu);
                    } else {
                        System.out.println("Imóvel não encontrado.");
                    }
                    break;

                case 6:
                    System.out.print("Matrícula: ");
                    matricula = scanner.nextLine();
                    List<Double> valoresIPTU = controller.calcularIPTUProximosAnos(matricula);
                    if (!valoresIPTU.isEmpty()) {
                        for (int i = 0; i < valoresIPTU.size(); i++) {
                            System.out.printf("IPTU ano %d: %.2f%n", i, valoresIPTU.get(i));
                        }
                    } else {
                        System.out.println("Imóvel não encontrado.");
                    }
                    break;

                case 7:
                    Map<String, Double> valoresTotais = controller.valorVenalTotalPorTipo();
                    for (Map.Entry<String, Double> entry : valoresTotais.entrySet()) {
                        System.out.printf("Valor venal total para %s: %.2f%n", entry.getKey(), entry.getValue());
                    }
                    break;

                case 8:
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
