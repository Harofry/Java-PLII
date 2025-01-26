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
