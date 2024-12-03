import java.util.*;

public class BancoService {
    private Map<String, Cliente> clientes = new HashMap<>();
    private Map<String, Conta> contas = new HashMap<>();

    public boolean cadastrarCliente(String nome, String cpf, String senha) {
        if (clientes.containsKey(cpf)) return false;
        Cliente cliente = new Cliente(nome, cpf, senha);
        clientes.put(cpf, cliente);
        return true;
    }

    public boolean criarConta(String cpf, String numero, double saldoInicial) {
        Cliente cliente = clientes.get(cpf);
        if (cliente == null || contas.containsKey(numero)) return false;
        Conta conta = new Conta(numero, saldoInicial, cliente);
        contas.put(numero, conta);
        return true;
    }

    public Conta buscarConta(String numero) {
        return contas.get(numero);
    }

    public Cliente autenticarUsuario(String cpf, String senha) {
        Cliente cliente = clientes.get(cpf);
        return (cliente != null && cliente.getSenha().equals(senha)) ? cliente : null;
    }

    public boolean transferir(String origem, String destino, double valor) {
        Conta contaOrigem = contas.get(origem);
        Conta contaDestino = contas.get(destino);
        if (contaOrigem == null || contaDestino == null || !contaOrigem.sacar(valor)) {
            return false;
        }
        contaDestino.depositar(valor);
        return true;
    }
}
