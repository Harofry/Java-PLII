import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {
    private List<Agencia> agencias;

    public Banco() {
        agencias = new ArrayList<>();
    }

    public Agencia buscarAgencia(String codigo) {
        for (Agencia agencia : agencias) {
            if (agencia.getCodigo().equals(codigo)) {
                return agencia;
            }
        }
        throw new IllegalArgumentException("Agência não encontrada.");
    }

    public void adicionarAgencia(Agencia agencia) {
        agencias.add(agencia);
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
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
                    System.out.print("Digite o código da nova agência: ");
                    String codigoAgencia = scanner.nextLine();
                    Agencia novaAgencia = new Agencia(codigoAgencia);
                    adicionarAgencia(novaAgencia);
                    System.out.println("Agência adicionada com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o código da agência: ");
                    String codigo = scanner.nextLine();
                    try {
                        Agencia agencia = buscarAgencia(codigo);
                        exibirMenuAgencia(agencia);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirMenuAgencia(Agencia agencia) {
        Scanner scanner = new Scanner(System.in);
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

    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.exibirMenu();
    }
}
