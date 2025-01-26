import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agencia {
    private String codigo;
    private List<Conta> contas;

    public Agencia(String codigo) {
        this.codigo = codigo;
        this.contas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public Conta buscarConta(String numero) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta;
            }
        }
        throw new IllegalArgumentException("A conta solicitada não foi encontrada.");
    }

    public void aplicarTaxasOuRendimentos() {
        for (Conta conta : contas) {
            conta.aplicarTaxasOuRendimentos();
        }
    }

    public void criarContaInterativa() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número da conta: ");
        String numero = scanner.nextLine();

        System.out.print("Digite o saldo inicial: ");
        double saldoInicial = scanner.nextDouble();

        System.out.println("Selecione o tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.println("3. Conta Salário");

        int tipo = scanner.nextInt();
        Conta novaConta = null;

        switch (tipo) {
            case 1:
                System.out.print("Digite a taxa de manutenção: ");
                double taxaManutencao = scanner.nextDouble();
                novaConta = new ContaCorrente(numero, saldoInicial, taxaManutencao);
                break;
            case 2:
                System.out.print("Digite a taxa de rendimento: ");
                double taxaRendimento = scanner.nextDouble();
                novaConta = new ContaPoupanca(numero, saldoInicial, taxaRendimento);
                break;
            case 3:
                System.out.print("Digite o limite de saques: ");
                int limiteSaques = scanner.nextInt();
                novaConta = new ContaSalario(numero, saldoInicial, limiteSaques);
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        adicionarConta(novaConta);
        System.out.println("Conta criada com sucesso!");
    }

    public void exibirContasPorAgencia() {
        System.out.println("Contas na agência " + codigo + ":");
        for (Conta conta : contas) {
            System.out.println("Tipo: " + conta.getClass().getSimpleName() + ", Número: " + conta.getNumero() + ", Saldo: " + conta.getSaldo());
        }
    }
}
