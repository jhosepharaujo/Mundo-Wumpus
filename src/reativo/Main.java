package reativo;

public class Main {

	public static void main(String[] args) {
		// Dimensão da matriz do labirinto
		int dimensao = 5;

		// Quantidade de buracos: recomenda-se utilizar no máximo o valor de um a menos
		// o tamanho da metade da dimensão.
		int qtdBuracos = 2;

		Mapa mapa = new Mapa(dimensao, qtdBuracos);
		Jogador jogador = new Jogador();

		jogador.setMapa(mapa);
		jogador.inicializar(mapa.getX_jogador(), mapa.getY_jogador());
		jogador.acharCaminho();
	}
}
