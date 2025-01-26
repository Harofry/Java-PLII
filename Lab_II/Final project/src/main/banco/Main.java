import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);

        // Menu principal
        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Adicionar Agência");
            System.out.println("2. Selecionar Agência");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    // Adicionar uma nova agência
                    System.out.print("Digite o código da nova agência: ");
                    String codigoAgencia = scanner.nextLine();
                    Agencia novaAgencia = new Agencia(codigoAgencia);
                    banco.adicionarAgencia(novaAgencia);
                    System.out.println("Agência adicionada com sucesso!");
                    break;
                case 2:
                    // Selecionar uma agência
                    System.out.print("Digite o código da agência: ");
                    String codigo = scanner.nextLine();
                    try {
                        Agencia agencia = banco.buscarAgencia(codigo);
                        exibirMenuAgencia(agencia);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuAgencia(Agencia agencia) {
        Scanner scanner = new Scanner(System.in);

        // Menu da agência
        while (true) {
            System.out.println("=== Menu da Agência " + agencia.getCodigo() + " ===");
            System.out.println("1. Criar Conta");
            System.out.println("2. Exibir Contas da Agência");
            System.out.println("3. Buscar Conta por Número");
            System.out.println("4. Voltar ao Menu Principal");
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
                    System.out.print("Digite o número da conta: ");
                    String numero = scanner.nextLine();
                    try {
                        Conta conta = agencia.buscarConta(numero);
                        System.out.println("Conta encontrada: Tipo: " + conta.getClass().getSimpleName() + ", Número: " + conta.getNumero() + ", Saldo: " + conta.getSaldo());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
