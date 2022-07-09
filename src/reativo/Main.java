package reativo;

public class Main {

	public static void main(String[] args) {
		Mapa mapa = new Mapa(5,2);
		Jogador jogador = new Jogador();

		jogador.setMapa(mapa);
		jogador.inicializar(mapa.getX_jogador(), mapa.getY_jogador());
		jogador.acharCaminho();
	}
}
