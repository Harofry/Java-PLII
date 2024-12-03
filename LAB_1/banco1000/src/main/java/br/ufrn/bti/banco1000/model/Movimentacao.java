public class Movimentacao {
    private String tipo; // Ex.: "Depósito", "Saque"
    private double valor;
    private Conta conta;

    public Movimentacao(String tipo, double valor, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.conta = conta;
    }

    @Override
    public String toString() {
        return "Movimentação: " + tipo + " | Valor: " + valor + " | Conta: " + conta.getNumero();
    }
}
