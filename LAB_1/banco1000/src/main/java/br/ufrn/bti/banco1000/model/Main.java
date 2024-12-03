import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BancoService bancoService = new BancoService();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Sistema Bancário ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Criar Conta");
            System.out.println("3. Transferir");
            System.out.println("4. Consultar Saldo");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
        // Opção switch foi o melhor metódo de integração para modularização
            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    if (bancoService.cadastrarCliente(nome, cpf, senha)) {
                        System.out.println("Cliente cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro: CPF já cadastrado.");
                    }
                }
                case 2 -> {
                    System.out.print("CPF do Titular: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Número da Conta: ");
                    String numero = scanner.nextLine();
                    System.out.print("Saldo Inicial: ");
                    double saldo = scanner.nextDouble();
                    if (bancoService.criarConta(cpf, numero, saldo)) {
                        System.out.println("Conta criada com sucesso!");
                    } else {
                        System.out.println("Erro: CPF inválido ou número de conta já existente.");
                    }
                }
                case 3 -> { //bug a ser corrigido por aqui no caso 3
                    System.out.print("Conta Origem: ");
                    String origem = scanner.nextLine();
                    System.out.print("Conta Destino: ");
                    String destino = scanner.nextLine();
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    if (bancoService.transferir(origem, destino, valor)) {
                        System.out.println("Transferência realizada com sucesso!");
                    } else {
                        System.out.println("Erro: Transferência não realizada.");
                    }
                }
                case 4 -> {
                    System.out.print("Número da Conta: ");
                    String numero = scanner.nextLine();
                    Conta conta = bancoService.buscarConta(numero);
                    if (conta != null) {
                        System.out.println("Saldo: " + conta.getSaldo());
                    } else {
                        System.out.println("Erro: Conta não encontrada.");
                    }
                }
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}
