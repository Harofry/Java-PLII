// Classe base Conta
import java.io.Serializable;

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

// Classe da contacorrente
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

// Classe da contapoupanca
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

// Classe de contasalario
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
            throw new IllegalArgumentException("Lamento, mas o imite de saques foi excedido.");
        }
        super.sacar(valor);
        saquesRealizados++;
    }

    @Override
    public void aplicarTaxasOuRendimentos() {
    }
}
/ Classe Agencia
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
        throw new IllegalArgumentException("a conta solictiada não foi encontrada.");
    }

    public void aplicarTaxasOuRendimentos() {
        for (Conta conta : contas) {
            conta.aplicarTaxasOuRendimentos();
        }
    }

    public List<Conta> getContas() {
        return contas;
    }

    // utiliza métodos de persistência
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
}