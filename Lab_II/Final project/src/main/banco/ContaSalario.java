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
