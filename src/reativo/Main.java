package reativo;

public class Main {

	public static void main(String[] args) {
		Mapa mapa = new Mapa(5);
		Jogador jogador = new Jogador();

		// passo mapa para o player [Ele nao ter� acesso ao mapa todo, somente para
		// verificar sensores]
		jogador.setMapa(mapa);
		jogador.inicializar(mapa.getX_jogador(), mapa.getY_jogador());

		// encontro caminho
		jogador.acharCaminho();
	}
}
