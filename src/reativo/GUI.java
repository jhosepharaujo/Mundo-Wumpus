package reativo;

/**
 * Classe de iteração com o usuário para mostrar as saídas das ações e eventos
 * que acontecem durante a execução do jogo.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class GUI {

	/**
	 * Indica o local do mapa em que o jogador morreu e qual foi a causa.
	 * 
	 * @param linha
	 * @param coluna
	 * @param causa
	 */
	public void jogadorMorreu(int linha, int coluna, String causa) {
		System.out.println("\n O Jogador morreu na casa [" + linha + ":" + coluna + "] por um " + causa);
	}

	/**
	 * Indica a casa em que o jogador achou o ouro.
	 * 
	 * @param linha
	 * @param coluna
	 */
	public void jogadorPegouOuro(int linha, int coluna) {
		System.out.println("\n O Jogador pegou o ouro na casa [" + linha + ":" + coluna + "]");
	}

	/**
	 * Indica que o jogador conseguiu sair do labirinto.
	 */
	public void jogadorSaiuDoLabirinto() {
		System.out.println("\n O Jogador saiu do labirinto ");
	}

	/**
	 * Indica qual foi o caminho que o jogador percorreu.
	 * 
	 * @param caminho
	 */
	public void caminho(String caminho) {
		System.out.println("\n Caminho percorrido: " + caminho);
	}

	/**
	 * Imprimir o mapa.
	 * 
	 * @param mapa
	 */
	public void imprimirMapa(Mapa mapa) {
		int[][] map = mapa.getMapa();
		System.out.println("\n================== MAPA =====================\n");
		for (int i = 0; i < mapa.getDimensao(); i++) {
			for (int j = 0; j < mapa.getDimensao(); j++)
				System.out.print(map[i][j] + " ");
			System.out.println("");
		}
		System.out.println("\nLEGENDA: 0:Sala Vazia | 1:Wumpus | 2:Fedor | 3:Buraco | 4:Brisa | 6:Ouro | 7:Jogador \n");
		System.out.println("==========================================================================================");
	}
}