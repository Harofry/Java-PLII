import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Agencia> agencias;

    public Banco() {
        this.agencias = new ArrayList<>();
    }

    public void adicionarAgencia(Agencia agencia) {
        this.agencias.add(agencia);
    }

    public Agencia buscarAgencia(String codigo) {
        return agencias.stream()
                .filter(agencia -> agencia.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Agência não encontrada: " + codigo));
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }
}
