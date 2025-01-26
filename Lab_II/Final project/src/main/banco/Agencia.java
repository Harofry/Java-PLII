import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
                        contas.add(new ContaCorrente(numero, saldo, 10.0)); // taxa
                        break;
                    case "ContaPoupanca":
                        contas.add(new ContaPoupanca(numero, saldo, 0.02)); // rendimento
                        break;
                    case "ContaSalario":
                        contas.add(new ContaSalario(numero, saldo, 3)); // limite de saques
                        break;
                    default:
                        throw new IOException("Tipo de conta: " + tipo);
                }
            }
        }
    }

    public void criarContaInterativa() {
    }

    public void exibirContasPorAgencia() {
    }
}
