package util;

import static util.AG.iMax;
import static util.AG.xMax;
import static util.Ambiente.jogador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

	// selecionar dois individos da populaçãlo.
	public static List<Individuo> selecionarIndividuos(List<Individuo> individuos) {

		List<Individuo> selecionados = new ArrayList();

		// sorteia dois numeros
		int radom1 = Util.numeroAleatorio(0, individuos.size());
		int radom2 = Util.numeroAleatorio(0, individuos.size());
		int radom3 = Util.numeroAleatorio(0, individuos.size());
		int radom4 = Util.numeroAleatorio(0, individuos.size());

		selecionados.add(individuos.get(radom1));
		selecionados.add(individuos.get(radom2));
		selecionados.add(individuos.get(radom3));
		selecionados.add(individuos.get(radom4));

		return selecionados;
	}

	// faz o lance do sorteio
	public static List<Individuo> cruzarIndividuos(List<Individuo> individuosSelecionados, int iMax, int xMax) {

		Random r = new Random();

		// tamanho do gene do individuo1
		int sizeGene1 = individuosSelecionados.get(0).getGenes().length();

		// soterio dos valores com os tamanhos dos genes do individuo1
		int radom1 = Util.numeroAleatorio(1, sizeGene1);
		int radom2 = Util.numeroAleatorio(1, sizeGene1);

		// tres partes do indivio1
		String p1 = "";
		String p2 = "";
		String p3 = "";

		//
		for (int i = 0; i < sizeGene1; i++) {

			char gene = individuosSelecionados.get(0).getGenes().charAt(i);

			if (i <= radom1) {
				p1 = p1 + gene;
			} else if (i >= radom1 && i <= radom2) {
				p2 = p2 + gene;
			} else if (i >= radom2) {
				p3 = p3 + gene;
			}

		}

		// -----------------------------------------------------------------------------------------
		// tamanho do gene do individuo1
		int sizeGene2 = individuosSelecionados.get(1).getGenes().length();

		// soterio dos valores com os tamanhos dos genes do individuo1
		int radom3 = Util.numeroAleatorio(1, sizeGene2);
		int radom4 = Util.numeroAleatorio(1, sizeGene2);

		// tres partes do indivio1
		String p4 = "";
		String p5 = "";
		String p6 = "";

		//
		for (int i = 0; i < sizeGene2; i++) {

			char gene = individuosSelecionados.get(1).getGenes().charAt(i);

			if (i <= radom3) {
				p4 = p4 + gene;
			} else if (i >= radom3 && i <= radom4) {
				p5 = p5 + gene;
			} else if (i >= radom4) {
				p6 = p6 + gene;
			}

		}

		// -----------------------------------------------------------------------------------------
		// tamanho do gene do individuo1
		int sizeGene3 = individuosSelecionados.get(2).getGenes().length();

		// soterio dos valores com os tamanhos dos genes do individuo1
		int radom5 = Util.numeroAleatorio(1, sizeGene3);
		int radom6 = Util.numeroAleatorio(1, sizeGene3);

		// tres partes do indivio1
		String p7 = "";
		String p8 = "";
		String p9 = "";

		//
		for (int i = 0; i < sizeGene3; i++) {

			char gene = individuosSelecionados.get(2).getGenes().charAt(i);

			if (i <= radom5) {
				p7 = p7 + gene;
			} else if (i >= radom5 && i <= radom6) {
				p8 = p8 + gene;
			} else if (i >= radom6) {
				p9 = p9 + gene;
			}

		}

		// -----------------------------------------------------------------------------------------
		// tamanho do gene do individuo1
		int sizeGene4 = individuosSelecionados.get(3).getGenes().length();

		// soterio dos valores com os tamanhos dos genes do individuo1
		int radom7 = Util.numeroAleatorio(1, sizeGene4);
		int radom8 = Util.numeroAleatorio(1, sizeGene4);

		// tres partes do indivio1
		String p10 = "";
		String p11 = "";
		String p12 = "";

		//
		for (int i = 0; i < sizeGene4; i++) {

			char gene = individuosSelecionados.get(3).getGenes().charAt(i);

			if (i <= radom7) {
				p10 = p10 + gene;
			} else if (i >= radom7 && i <= radom8) {
				p11 = p11 + gene;
			} else if (i >= radom8) {
				p12 = p12 + gene;
			}

		}

		String i1 = p1 + p5 + p3;
		String i2 = p4 + p2 + p6;
		String i3 = p7 + p11 + p9;
		String i4 = p10 + p8 + p12;

		List<Individuo> novosIndividuos = new ArrayList();
		novosIndividuos.add(new Individuo(i1, iMax, xMax));
		novosIndividuos.add(new Individuo(i2, iMax, xMax));
		novosIndividuos.add(new Individuo(i3, iMax, xMax));
		novosIndividuos.add(new Individuo(i4, iMax, xMax));

		return novosIndividuos;
	}

	// na mutação eu acho erro e aletoreamente atriuo um resultado para ele sme me
	// preucupar se estra certo ou não
	public static Individuo mutarIndividuo(List<Individuo> novosIndividuos, int iMax, int xMax, int escolhido) {

		String opcoes = "NSLO";

		Random r = new Random();

		List<Individuo> individuosMutados = new ArrayList();

		Individuo in = novosIndividuos.get(escolhido);

		int radom = Util.numeroAleatorio(0, opcoes.length());

		char mutacao = opcoes.charAt(radom);

		int radom1 = Util.numeroAleatorio(0, in.getGenes().length());

		StringBuffer sb = new StringBuffer(in.getGenes());

		sb.replace(radom1, radom1 + 1, mutacao + "");

		String mutado = sb.toString();

		in.setGenes(mutado);

		return new Individuo(in.getGenes(), iMax, xMax);
	}

	public static String formataSaidaDaMatriz(int[][] matriz) {

		String saida = ""; // Cria e inicializa uma String
		for (int linha = 0; linha < matriz.length; linha++) { // for para percorrer as linhas da matriz
			for (int coluna = 0; coluna < matriz[0].length; coluna++) { // percorrer as colunas
				// guardando na String cada elemento separado por um espaço
				saida = saida + matriz[linha][coluna] + " ";
			}
			saida = saida + "\n"; // Guarda uma quebra de linha na String
		}
		return saida; // retorna a String
	}

	public static String formataSaidaDaMatriz2(int[][] matriz) {

		String saida = ""; // Cria e inicializa uma String
		for (int linha = matriz.length - 1; linha >= 0; linha--) { // for para percorrer as linhas da matriz

			for (int coluna = 0; coluna < matriz[0].length; coluna++) { // percorrer as colunas
				// guardando na String cada elemento separado por um espaço
				saida = saida + matriz[linha][coluna] + " ";
			}
			saida = saida + "\n"; // Guarda uma quebra de linha na String
		}
		return saida; // retorna a String
	}

	public static String formataSaidaDaMatriz3(int movx, int movy) {

		int[][] matriz = new int[xMax][iMax];
		matriz[movx][movy] = jogador;

		String saida = ""; // Cria e inicializa uma String
		for (int linha = matriz.length - 1; linha >= 0; linha--) { // for para percorrer as linhas da matriz

			for (int coluna = 0; coluna < matriz[0].length; coluna++) { // percorrer as colunas
				// guardando na String cada elemento separado por um espaço
				saida = saida + matriz[linha][coluna] + " ";
			}
			saida = saida + "\n"; // Guarda uma quebra de linha na String
		}
		return saida; // retorna a String
	}
	
	public static void imprimirLegenda() {
		System.out.println("\nLEGENDA: 0:Sala Vazia | 1:Wumpus | 2:Fedor | 3:Buraco | 4:Brisa | 6:Ouro | 7:Jogador \n");
		System.out.println("==========================================================================================");
	}

	public static int numeroAleatorio(int min, int max) {

		int randomNum = min + (int) (Math.random() * (max - min));

		return randomNum;
	}

}
