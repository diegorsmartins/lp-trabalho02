import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class resolvido {
	public static void main(String args[]) {

		String arquivo = "j://testeDiego.txt";

		try {
			System.setIn(new FileInputStream(new File(arquivo)));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
		}

		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.ENGLISH);
		Locale.setDefault(new Locale("en", "US"));
		DecimalFormat df = new DecimalFormat(".00");

		String vetorNome[], vetorTel[];
		int vetorTipo[], vetorMin[];
		double valorDaConta[];
		int N, i, j;

		System.out.println("Quantos clientes?");
		N = sc.nextInt();

		vetorNome = new String[N];
		vetorTel = new String[N];
		vetorTipo = new int[N];
		vetorMin = new int[N];
		valorDaConta = new double[N];

		// matriz de preços
		double matrizPrec[][];

		matrizPrec = new double[3][2];

		System.out.println("Digite os valores da conta:");
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 2; j++) {
				System.out.print("[" + i + "]" + "[" + j + "] = ");
				matrizPrec[i][j] = sc.nextDouble();
			}
		}

		// pega os dados do vetor
		for (i = 0; i < N; i++) {
			System.out.print("Digite o " + (i + 1) + "º nome: ");
			vetorNome[i] = sc.next();
			System.out.print("Qual o número de telefone deste cliente: ");
			vetorTel[i] = sc.next();
			System.out.print("Qual o tipo de conta deste cliente: ");
			vetorTipo[i] = sc.nextInt();
			System.out.print("Quantos minutos foram consumidos pelo cliente: ");
			vetorMin[i] = sc.nextInt();

			// calculo da conta

			if (vetorTipo[i] == 0) {

				if (vetorMin[i] > 90) {
					valorDaConta[i] = ((vetorMin[i] - 90) * (matrizPrec[0][1])) + (matrizPrec[0][0]);
				} else {
					valorDaConta[i] = matrizPrec[0][0];
				}
			}

			if (vetorTipo[i] == 1) {
				if (vetorMin[i] > 90) {
					valorDaConta[i] = ((vetorMin[i] - 90) * (matrizPrec[1][1])) + (matrizPrec[1][0]);
				} else {
					valorDaConta[i] = matrizPrec[1][0];
				}
			}

			if (vetorTipo[i] == 2) {
				if (vetorMin[i] > 90) {
					valorDaConta[i] = ((vetorMin[i] - 90) * (matrizPrec[2][1])) + (matrizPrec[2][0]);
				} else {
					valorDaConta[i] = matrizPrec[2][0];
				}
			}
		}

		// imprime tabela

		System.out.print("\n\nNome\t\tTelefone\tTipo\tMinutos\tValor da Conta");
		System.out.print("\n--------------- --------------- ------- ------- --------------- ");
		for (i = 0; i < N; i++) {
			System.out.printf("\n" + vetorNome[i] + "\t\t" + vetorTel[i] + "\t" + vetorTipo[i] + "\t" + vetorMin[i]
					+ "\tR$ " + df.format(valorDaConta[i]));
		}

		// perguntas

		double receitaTotal = 0;
		int contaBarata = 0, media1 = 0, mediac = 0, cont = 0, contm = 0, contp = 0;
		double porcentagem2 = 0;

		for (i = 0; i < N; i++) {

			// soma das receitas
			receitaTotal = receitaTotal + valorDaConta[i];

			// nome e telefone do cliente com a conta mais barata
			if (valorDaConta[i] < valorDaConta[contaBarata]) {
				contaBarata = i;
			}

			// media de minutos da conta do tipo 1
			if (vetorTipo[i] == 1) {
				mediac = mediac + vetorMin[i];
				cont++;
			}
			media1 = mediac / cont;

			// clientes que consumiram acima de 120 minutos
			if (vetorMin[i] > 120) {
				contm++;
			}

			// porcentagem de contas tipo 2
			if (vetorTipo[i] == 2) {
				contp = contp + 1;
				porcentagem2 = (double) contp / N * 100;
			}
		}

		System.out.printf("\n\nReceita total da empresa telefônica (soma de todas as contas): R$ %.2f", receitaTotal);
		System.out.println("\nNome e telefone do cliente do qual a conta foi mais barata: " + vetorNome[contaBarata]
				+ " - " + vetorTel[contaBarata]);
		System.out.println("Média de minutos consumidos por clientes de conta tipo 1: " + media1);
		System.out.println("Nomes e telefones dos clientes que não consumiram minutos excedentes: ");

		for (i = 0; i < N; i++) {
			// nome e telefone dos clientes que não consumiram minutos
			// excedentes
			if (vetorMin[i] < 90) {
				System.out.println(vetorNome[i] + " - " + vetorTel[i]);
			}
		}

		System.out.println("Quantidade de clientes que consumiu acima de 120 minutos: " + contm);
		System.out.printf("Porcentagem de clientes que possuem conta tipo 2, em relação ao total de clientes: %.1f",
				porcentagem2);
		System.out.print("%");

		sc.close();
	}
}