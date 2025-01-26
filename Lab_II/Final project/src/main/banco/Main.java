import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirTabela();
            System.out.print("\nEscolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: // Adicionar nova agência
                    System.out.print("Digite o código da nova agência: ");
                    String codigoAgencia = scanner.nextLine();
                    Agencia novaAgencia = new Agencia(codigoAgencia);
                    banco.adicionarAgencia(novaAgencia);
                    System.out.println("Agência " + codigoAgencia + " adicionada com sucesso!");
                    break;

                case 2: // Selecionar uma agência
                    System.out.print("Digite o código da agência: ");
                    String codigo = scanner.nextLine();
                    try {
                        Agencia agencia = banco.buscarAgencia(codigo);
                        menuAgencia(agencia, scanner);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3: // Salvamento de dados em CSV
                    System.out.print("Digite o nome do arquivo para salvar os dados: ");
                    String arquivoSalvar = scanner.nextLine();
                    try {
                        for (Agencia agencia : banco.getAgencias()) {
                            agencia.salvarDadosCSV(arquivoSalvar);
                        }
                        System.out.println("Dados salvos com sucesso em " + arquivoSalvar);
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar os dados: " + e.getMessage());
                    }
                    break;

                case 4: // Carregamento de dados de CSV
                    System.out.print("Digite o nome do arquivo para carregar os dados: ");
                    String arquivoCarregar = scanner.nextLine();
                    try {
                        for (Agencia agencia : banco.getAgencias()) {
                            agencia.carregarDadosCSV(arquivoCarregar);
                        }
                        System.out.println("Dados carregados com sucesso de " + arquivoCarregar);
                    } catch (IOException e) {
                        System.out.println("Erro ao carregar os dados: " + e.getMessage());
                    }
                    break;

                case 5: // Sair do programa
                    System.out.println("Encerrando o programa. Até logo!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirTabela() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Adicionar nova agência");
        System.out.println("2. Selecionar uma agência");
        System.out.println("3. Salvar dados das agências e contas em CSV");
        System.out.println("4. Carregar dados das agências e contas de CSV");
        System.out.println("5. Sair");
    }

    private static void menuAgencia(Agencia agencia, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Menu da Agência " + agencia.getCodigo() + " ===");
            System.out.println("1. Criar uma nova conta");
            System.out.println("2. Exibir contas da agência");
            System.out.println("3. Buscar conta por número");
            System.out.println("4. Aplicar taxas ou rendimentos em todas as contas");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: // Criação de uma nova conta
                    agencia.criarContaInterativa();
                    break;

                case 2: // Exibição de contas da agência
                    agencia.exibirContasPorAgencia();
                    break;

                case 3: // Busca por conta por número
                    System.out.print("Digite o número da conta: ");
                    String numero = scanner.nextLine();
                    try {
                        Conta conta = agencia.buscarConta(numero);
                        System.out.println("Conta encontrada: Tipo: " + conta.getClass().getSimpleName() +
                                ", Número: " + conta.getNumero() +
                                ", Saldo: " + conta.getSaldo());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4: // Aplicação de taxas ou rendimentos
                    agencia.aplicarTaxasOuRendimentos();
                    System.out.println("Taxas/rendimentos aplicados com sucesso.");
                    break;

                case 5: // Retorno ao menu principal
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
