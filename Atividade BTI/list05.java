public class list05 {
    public static void main(String[] args) throws Exception {
        System.out.print("Digite um número: ");
        int n = Integer.parseInt(System.console().readLine());
        int soma = 0;

        for (int i = 1; i <= n; i++) {
            soma += i;
        }

        System.out.println("A soma de 1 a " + n + " é: " + soma);
    }
}
