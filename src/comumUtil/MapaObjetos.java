package comumUtil;

/**
 * Enumeração para representar os objetos que iteragem no mapa do jogo
 * 
 *  0- Sala vazia
 *  1- Wumpus (obstáculo/inimigo)
 *  2- Fedor (sensor para indicar que o Wumpus está em sala próxima)
 *  3- Buraco (obstáculo/inimigo)
 *  4- Brisa (sensor para indicar que tem buraco em sala próxima)
 *  6- Ouro (objetivo para completar a tarefa)
 *  7- Jogador (agente que iterage no ambiente com o objetivo de achar o ouro e voltar ao ponto inicial sem morrer)
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

	/**
	 * retorna o valor referente ao objeto do mapa.
	 * 
	 * @return
	 */
	public int getValor() {
		return valor;
	}

}
