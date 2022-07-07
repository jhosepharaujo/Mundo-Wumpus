package genetico.util;
/**
 * Enumeração para representar os objetos que iteragem no mapa do jogo
 * 
 *  1- Jogador (agente que iterage no ambiente com o objetivo de achar o ouro e voltar ao ponto inicial sem morrer)
 *  2- Ouro (objetivo para completar a tarefa)
 *  3- Buraco (obstáculo/inimigo)
 *  4- Wumpus (obstáculo/inimigo)
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public enum MapaObjetos {

	NADA(0), WUMPUS(1), FEDOR(2), BURACO(3), BRISA(4), OURO(6), JOGADOR(7);

	private final int valor;

	MapaObjetos(int valorOpcao){
		valor = valorOpcao;
	}

	public int getValor() {
		return valor;
	}

}
