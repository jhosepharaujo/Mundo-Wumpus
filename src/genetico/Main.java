package genetico;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	// Dimensão da matriz do labirinto
	static int dimensao = 5;

	// Quantidade de buracos: recomenda-se utilizar no máximo o valor de um a menos
	// o tamanho da metade da dimensão.
	static int qtdBuracos = 2;

	// Quantidade máxima de genes de um indivíduo
	static int rangeNumGenes = 100;

	// Tamanho da população
	static int tamanhoPopulacao = 3;

	// Quantidade de gerações
	static int tamanhoGeracao = 50000;

	public static Mapa mapa = new Mapa(dimensao, qtdBuracos);

	public static void main(String[] args) {
		try {
			mapa.runAag(new Genetico().run(tamanhoPopulacao, tamanhoGeracao, rangeNumGenes));
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
