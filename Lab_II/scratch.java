import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Conta implements Serializable {
    private String numero;
    private double saldo;

    public Conta(String numero, double saldoInicial) {
        this.numero = numero;
        this.saldo = saldoInicial;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Erro, valor do depósito deve ser positivo.");
        }
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Erro, o valor do saque deve ser positivo.");
        }
        if (valor > saldo) {
            throw new IllegalArgumentException("Alerta! Saldo insuficiente.");
        }
        saldo -= valor;
    }

    public abstract void aplicarTaxasOuRendimentos();
}

// classe da conta corrente
public class ContaCorrente extends Conta {
    private double taxaManutencao;

    public ContaCorrente(String numero, double saldoInicial, double taxaManutencao) {
        super(numero, saldoInicial);
        this.taxaManutencao = taxaManutencao;
    }

    @Override
    public void aplicarTaxasOuRendimentos() {
        sacar(taxaManutencao);
    }
}

// classe da conta poupanca
public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    public ContaPoupanca(String numero, double saldoInicial, double taxaRendimento) {
        super(numero, saldoInicial);
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void aplicarTaxasOuRendimentos() {
        double rendimento = getSaldo() * taxaRendimento;
        depositar(rendimento);
    }
}

// classe de conta salario
public class ContaSalario extends Conta {
    private int limiteSaques;
    private int saquesRealizados;

    public ContaSalario(String numero, double saldoInicial, int limiteSaques) {
        super(numero, saldoInicial);
        this.limiteSaques = limiteSaques;
        this.saquesRealizados = 0;
    }

    @Override
    public void sacar(double valor) {
        if (saquesRealizados >= limiteSaques) {
            throw new IllegalArgumentException("Lamento, mas o limite de saques foi excedido.");
        }
        super.sacar(valor);
        saquesRealizados++;
    }

    @Override
    public void aplicarTaxasOuRendimentos() {
    }
}

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

    public List<Conta> getContas() {
        return contas;
    }

    public void salvarDados(String arquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(contas);
        }
    }

    public void carregarDados(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            contas = (List<Conta>) ois.readObject();
        }
    }

    public void salvarDadosCSV(String arquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Conta conta : contas) {
                String tipo = conta.getClass().getSimpleName();
                writer.write(tipo + "," + conta.getNumero() + "," + conta.getSaldo() + "\n");
            }
        }
    }

    public void carregarDadosCSV(String arquivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            contas.clear();
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String tipo = partes[0];
                String numero = partes[1];
                double saldo = Double.parseDouble(partes[2]);

                switch (tipo) {
                    case "ContaCorrente":
                        contas.add(new ContaCorrente(numero, saldo, 10.0)); //  taxa
                        break;
                    case "ContaPoupanca":
                        contas.add(new ContaPoupanca(numero, saldo, 0.02)); // rendimento
                        break;
                    case "ContaSalario":
                        contas.add(new ContaSalario(numero, saldo, 3)); // saque
                        break;
                    default:
                        throw new IOException("Tipo de conta: " + tipo);
                }
            }
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
