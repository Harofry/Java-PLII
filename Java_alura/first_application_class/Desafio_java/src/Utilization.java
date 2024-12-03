import java.util.Scanner;

public class Utilization {
    public static void main(String[] args) {
        String nome = "Mia Lupi";
        String tipoConta = "Corrente";
        double saldo = 1000;
        int opcao = 0;

        System.out.println("***********************");
        System.out.println("\nNome do cliente: " + nome);
        System.out.println("Tipo conta: " + tipoConta);
        System.out.println("Saldo atual: " + saldo);
        System.out.println("\n***********************");

        String menu = """
                ** Digite sua opção **
                1 - Consultar saldo
                2 - Transferir valor
                3 - Receber valor 
                4 - Sair
                """;

        Scanner leitura = new Scanner(System.in);

        while (opcao != 4) {
            System.out.println(menu);
            opcao = leitura.nextInt();

            switch (opcao) {
                case 1 -> System.out.println("Seu saldo atual é: " + saldo);
                case 2 -> {
                    System.out.print("Informe o valor para transferir: ");
                    double valorTransferir = leitura.nextDouble();
                    if (valorTransferir > saldo) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        saldo -= valorTransferir;
                        System.out.println("Transferência realizada. Saldo atual: " + saldo);
                    }
                }
                case 3 -> {
                    System.out.print("Informe o valor recebido: ");
                    double valorReceber = leitura.nextDouble();
                    saldo += valorReceber;
                    System.out.println("Valor recebido. Saldo atual: " + saldo);
                }
                case 4 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        leitura.close();
    }
}
