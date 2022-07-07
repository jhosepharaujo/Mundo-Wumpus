import java.util.ArrayList;

/**
 * Classe de iteração com o usuário para mostrar as saídas das ações e eventos
 * que acontecem durante a execução do jogo
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class GUI {

	public void jogadorMorreu(int linha, int coluna, String causa) {
		System.out.println("\n O Jogador morreu na casa " + linha + " " + coluna + " por um " + causa);
	}

	public void jogadorRecebePenalidade(int linha, int coluna, String causa, int penalidade) {
		System.out.println("\n O Jogador recebeu penalidade na casa " + linha + " " + coluna + " DE " + penalidade
				+ " pontos por um " + causa);
	}

	public void jogadorPegouOuro(int linha, int coluna) {
		System.out.println("\n O Jogador pegou o ouro na casa " + linha + " " + coluna);
	}

	public void jogadorSaiuDoLabirinto() {
		System.out.println("\n O Jogador saiu do labirinto ");
	}

	public void playerOutLabirintWithPontuation(int pontuation) {
		System.out.println("\n O Jogador saiu do labirinto com " + (pontuation * (-1)) + " Pontos");
	}

	public void showBestPontuation(int best) {
		System.out.println("\n A pontucao para o melhor caminho é " + (best * (-1)) + " Pontos");
	}

	public void caminho(String caminho) {
		System.out.println("\n Caminho percorrido: " + caminho);
	}

	public void melhorCaminho(ArrayList<String> caminho) {
		// recebe o caminho de volta
		int tam = caminho.size();
		System.out.println("\n Melhor caminho: ");
		// imprimo Ida
		for (int i = (tam - 1); i > 0; i--)
			System.out.print(caminho.get(i));
		// imprimo retorno
		for (String cur : caminho)
			System.out.print(cur);
		System.out.println("");
	}

	public void debug(String msg) {
		System.out.println("\n DEBUG: " + msg);
	}

	public void iAmPlayerWithPontuation() {
		System.out.println("\n ==== JOGADOR COM PONTUACAO ==== \n");
	}

	public void imprimirMapa(Mapa mapa) {
		// recupero dados
		int[][] map = mapa.getMapa();
		System.out.println("\n================== MAPA =====================\n");
		for (int i = 0; i < mapa.getDimensao(); i++) {
			for (int j = 0; j < mapa.getDimensao(); j++)
				System.out.print(map[i][j] + " ");
			System.out.println("");
		}
		System.out.println("\nLEGENDA: 1: Jogador | 2:Ouro | 3:Buraco | 4:Wumpus\n");
		System.out.println("=============================================");
	}
}