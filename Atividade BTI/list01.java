public class list01 {
    public static void main(String[] args) throws Exception {
        System.out.print("Digite um número: ");
        int numero = Integer.parseInt(System.console().readLine());

        if (numero % 2 == 0) {
            System.out.println("Seu número é par.");
        } else {
            System.out.println("Seu número é ímpar.");
        }
    }
}
