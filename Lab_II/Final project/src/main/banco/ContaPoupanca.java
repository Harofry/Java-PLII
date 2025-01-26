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
