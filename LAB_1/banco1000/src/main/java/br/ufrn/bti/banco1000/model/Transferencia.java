public class Transferencia extends Movimentacao {
    private Conta contaDestino;

    public Transferencia(double valor, Conta contaOrigem, Conta contaDestino) {
        super("Transferência", valor, contaOrigem);
        this.contaDestino = contaDestino;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    @Override
    public String toString() {
        return super.toString() + " | Conta Destino: " + contaDestino.getNumero();
    }
}
