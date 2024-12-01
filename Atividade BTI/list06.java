public class list06 {
        public static void main(String[] args) throws Exception {
            System.out.print("Digite um número: ");
            int n = Integer.parseInt(System.console().readLine());
            int soma = 0;

            // Assim como no codigo anterior mas agora usando N²
            for (int i = 1; i <= n * n; i++) {
                soma += i;
            }

            System.out.println("A soma de 1 a " + (n * n) + " é: " + soma);
        }
    }
