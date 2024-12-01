public class list10 {
    public static void main(String[] args) throws Exception {
        System.out.print("Digite o primeiro termo (a): ");
        int a = Integer.parseInt(System.console().readLine());

        System.out.print("Digite a razão (r): ");
        double r = Double.parseDouble(System.console().readLine());

        System.out.print("Digite o número de termos (n): ");
        int n = Integer.parseInt(System.console().readLine());

        double soma;

        if (r == 1) {
            soma = a * n;
        } else {
            soma = a * (1 - Math.pow(r, n)) / (1 - r);
        }

        System.out.println("A soma dos " + n + " primeiros termos da progressão geométrica é: " + soma);
    }
}
