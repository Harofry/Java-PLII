public class list03 {
    public static void main(String[] args) throws Exception {
        System.out.print("Digite sua idade: ");
        int idade = Integer.parseInt(System.console().readLine());

        if (idade <= 13) {
            System.out.println("Você é uma criança.");
        } else if (idade >= 14 && idade <= 19) {
            System.out.println("Você é um adolescente.");
        } else if (idade >= 20 && idade <= 59) {
            System.out.println("Você é um adulto.");
        } else if (idade >= 60) {
            System.out.println("Você é um idoso.");
        } else {
            System.out.println("Ai você está de onda meu fi!");
        }
    }
}
