public class Conta {
    private String numero;
    private double saldo;
    private Cliente titular;

    public Conta(String numero, double saldoInicial, Cliente titular) {
        this.numero = numero;
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean sacar(double valor) {
        if (valor > saldo) {
            return false;
        }
        saldo -= valor;
        return true;
    }
}
