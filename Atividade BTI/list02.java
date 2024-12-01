public class list02 {
    public static void main(String[] args) throws Exception {
        System.out.print("Digite o primeiro número: ");
        int numero1 = Integer.parseInt(System.console().readLine());

        System.out.print("Digite o segundo número: ");
        int numero2 = Integer.parseInt(System.console().readLine());

        System.out.print("Digite qual operação tu quer entre: (+, -, *, /): ");
        char operacao = System.console().readLine().charAt(0);

        if (operacao == '+') {
            System.out.println("Resultado: " + (numero1 + numero2));
        } else if (operacao == '-') {
            System.out.println("Resultado: " + (numero1 - numero2));
        } else if (operacao == '*') {
            System.out.println("Resultado: " + (numero1 * numero2));
        } else if (operacao == '/') {
            System.out.println("Resultado: " + (numero1 / numero2));
        } else {
            System.out.println("Operação inválidada!");
        }
    }
}
