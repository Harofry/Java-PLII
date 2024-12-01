public class list08 {
    public static void main(String[] args) throws Exception {
        int numero;

        do {
            System.out.print("Digite um número (acerte a chave parada!): ");
            numero = Integer.parseInt(System.console().readLine());
        } while (numero != -1);

        System.out.println("Protocolo de encerramento ativado com sucesso!.");
    }
}
