package genetico;

import comumUtil.MapaObjetos;

public class Util {

	public static String caracteres = "NSLO";
	public static int nada = MapaObjetos.NADA.getValor();;
	public static int jogador = MapaObjetos.JOGADOR.getValor();
	public static int ouro = MapaObjetos.OURO.getValor();
	public static int wumpus = MapaObjetos.WUMPUS.getValor();
	public static int fedor = MapaObjetos.FEDOR.getValor();
	public static int buraco = MapaObjetos.BURACO.getValor();
	public static int brisa = MapaObjetos.BRISA.getValor();


	public static String imprimirMapaConfiguracaoInicial(int[][] matriz) {

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

	public static String imprimirMapaPosicaoJogador(int movx, int movy, int dimensao) {
		int[][] matriz = new int[dimensao][dimensao];
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
		System.out
				.println("==========================================================================================");
	}

	public static int numeroAleatorio(int min, int max) {

		int randomNum = min + (int) (Math.random() * (max - min));

		return randomNum;
	}

}
