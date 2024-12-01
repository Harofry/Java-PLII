public class list09 {
    public static void main(String[] args) throws Exception {
        System.out.print("Digite um número: ");
        int n = Integer.parseInt(System.console().readLine());
        int soma = 0;
// adendo: talvez eu tenha entendido o enunciado errado mas fui atrás dessa math a seguir

        while (n % 2 == 0) {
            soma += 2;
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                soma += i;
                n /= i;
            }
        }

        if (n > 2) {
            soma += n;
        }

        System.out.println("A soma dos fatores primos é: " + soma);
    }
}
