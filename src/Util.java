import java.util.ArrayList;
import java.util.List;

public class Util {

	// selecionar n individuos da população.
	public static List<Individuo> selecionarIndividuos(List<Individuo> individuos, int qtdSelecionados) {

		List<Individuo> selecionados = new ArrayList<>();

		for (int i = 0; i < qtdSelecionados; i++) {
			selecionados.add(individuos.get(Util.numeroAleatorio(0, individuos.size())));
		}

		return selecionados;
	}

	// faz o cruzamento dos genes dos individuos selecionados
	public static List<Individuo> cruzarIndividuos(List<Individuo> individuosSelecionados, Mapa mapa) {

		List<Individuo> novosIndividuos = new ArrayList<>();

		for (int i = 0; i < individuosSelecionados.size(); i++) {
			//sorteia aleatoriamente 2 indivíduos para cruzar
			int random1 = Util.numeroAleatorio(0, individuosSelecionados.size());
			int random2 = Util.numeroAleatorio(0, individuosSelecionados.size());
			String genesSorteio1 = individuosSelecionados.get(random1).getGenes();
			String genesSorteio2 = individuosSelecionados.get(random2).getGenes();

			int sorteio1sizeGene = genesSorteio1.length();

			// divide os genes do individuo sorteado1 e pega a primeira parte
			String genes1p1 = genesSorteio1.substring(0, sorteio1sizeGene / 2);
			
			int sorteio2sizeGene = genesSorteio2.length();

			// divide os genes do individuo sorteado2 e pega a segunda parte
			String genes2p2 = genesSorteio2.substring(sorteio2sizeGene / 2, sorteio2sizeGene);
			
			//gera novo gene combinado com as duas partes que foram criadas
			String novoGene = genes1p1 + genes2p2;
			
			novosIndividuos.add(new Individuo(novoGene, mapa));
		}

		return novosIndividuos;
	}

	// na mutação eu acho erro e aletoreamente atriuo um resultado para ele sme me
	// preucupar se estra certo ou não
	public static Individuo mutarIndividuo(List<Individuo> novosIndividuos, int escolhido, Mapa mapa) {
		String opcoes = "NSLO";
		Individuo in = novosIndividuos.get(escolhido);
		int radom = Util.numeroAleatorio(0, opcoes.length());
		char mutacao = opcoes.charAt(radom);
		int radom1 = Util.numeroAleatorio(0, in.getGenes().length());
		StringBuffer sb = new StringBuffer(in.getGenes());
		sb.replace(radom1, radom1 + 1, mutacao + "");
		String mutado = sb.toString();
		in.setGenes(mutado);

		return new Individuo(in.getGenes(), mapa);
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

	/*
	 * public static String formataSaidaDaMatriz3(int movx, int movy) {
	 * 
	 * int[][] matriz = new int[xMax][iMax]; matriz[movx][movy] = ag;
	 * 
	 * String saida = ""; // Cria e inicializa uma String for (int linha =
	 * matriz.length - 1; linha >= 0; linha--) { // for para percorrer as linhas da
	 * matriz
	 * 
	 * for (int coluna = 0; coluna < matriz[0].length; coluna++) { // percorrer as
	 * colunas // guardando na String cada elemento separado por um espaço saida =
	 * saida + matriz[linha][coluna] + " "; } saida = saida + "\n"; // Guarda uma
	 * quebra de linha na String } return saida; // retorna a String }
	 */

	public static int numeroAleatorio(int min, int max) {

		int randomNum = min + (int) (Math.random() * (max - min));

		return randomNum;
	}

}