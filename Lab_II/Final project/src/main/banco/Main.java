import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Adicionar Agência");
            System.out.println("2. Selecionar Agência");
            System.out.println("3. Adicionar Saldo");
            System.out.println("4. Mostrar Saldo");
            System.out.println("5. Transferir Saldo");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    adicionarAgencia(banco, scanner);
                    break;
                case 2:
                    selecionarAgencia(banco, scanner);
                    break;
                case 3:
                    adicionarSaldo(banco, scanner);
                    break;
                case 4:
                    mostrarSaldo(banco, scanner);
                    break;
                case 5:
                    transferirSaldo(banco, scanner);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void adicionarAgencia(Banco banco, Scanner scanner) {
        System.out.print("Digite o código da nova agência: ");
        String codigoAgencia = scanner.nextLine();
        Agencia novaAgencia = new Agencia(codigoAgencia);
        banco.adicionarAgencia(novaAgencia);
        System.out.println("Agência adicionada com sucesso!");
    }

    private static void selecionarAgencia(Banco banco, Scanner scanner) {
        System.out.print("Digite o código da agência: ");
        String codigo = scanner.nextLine();
        try {
            Agencia agencia = banco.buscarAgencia(codigo);
            exibirMenuAgencia(agencia, scanner);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void exibirMenuAgencia(Agencia agencia, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Menu da Agência " + agencia.getCodigo() + " ===");
            System.out.println("1. Criar Conta");
            System.out.println("2. Exibir Contas da Agência");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    agencia.criarContaInterativa();
                    break;
                case 2:
                    agencia.exibirContasPorAgencia();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void adicionarSaldo(Banco banco, Scanner scanner) {
        System.out.print("Digite o código da agência: ");
        String codigoAgencia = scanner.nextLine();
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        try {
            Agencia agencia = banco.buscarAgencia(codigoAgencia);
            Conta conta = agencia.buscarConta(numeroConta);

            System.out.print("Digite o valor a ser depositado: ");
            double valor = scanner.nextDouble();

            conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void mostrarSaldo(Banco banco, Scanner scanner) {
        System.out.print("Digite o código da agência: ");
        String codigoAgencia = scanner.nextLine();
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        try {
            Agencia agencia = banco.buscarAgencia(codigoAgencia);
            Conta conta = agencia.buscarConta(numeroConta);

            System.out.println("Saldo atual: R$ " + conta.getSaldo());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void transferirSaldo(Banco banco, Scanner scanner) {
        System.out.print("Digite o código da agência de origem: ");
        String codigoOrigem = scanner.nextLine();
        System.out.print("Digite o número da conta de origem: ");
        String numeroOrigem = scanner.nextLine();
        System.out.print("Digite o código da agência de destino: ");
        String codigoDestino = scanner.nextLine();
        System.out.print("Digite o número da conta de destino: ");
        String numeroDestino = scanner.nextLine();

        try {
            Agencia agenciaOrigem = banco.buscarAgencia(codigoOrigem);
            Conta contaOrigem = agenciaOrigem.buscarConta(numeroOrigem);
            Agencia agenciaDestino = banco.buscarAgencia(codigoDestino);
            Conta contaDestino = agenciaDestino.buscarConta(numeroDestino);

            System.out.print("Digite o valor a ser transferido: ");
            double valor = scanner.nextDouble();

            contaOrigem.sacar(valor);
            contaDestino.depositar(valor);

            System.out.println("Transferência realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
