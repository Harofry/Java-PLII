public class list07 {
    public static void main(String[] args) throws Exception {
        System.out.print("Digite um ano: ");
        int ano = Integer.parseInt(System.console().readLine());

        if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
            System.out.println(ano + " é um ano bissexto.");
        } else {
            System.out.println(ano + " não é um ano bissexto.");
        }
    }
}
