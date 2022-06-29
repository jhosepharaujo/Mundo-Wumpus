import java.util.Random;

/**
 * Classe para a montagem e definições das configurações do mapa do jogo.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class Mapa {

	private int[][] mapa;
	private int dimensao;
	private int x_jogador;
	private int y_player;
	private Lista salasOcupadas;

	Mapa(int dimensao) {
		this.dimensao = dimensao;
		this.mapa = new int[dimensao][dimensao];
		this.x_jogador = dimensao - 1;
		this.y_player = 0;
		this.salasOcupadas = new Lista();
		this.gerarMapa();
		this.gerarPosicaoJogador(this.getX_jogador(), this.getY_jogador());
		this.gerarObjetoMapa(MapaObjetos.BURACO, dimensao - 1);
		this.gerarObjetoMapa(MapaObjetos.WUMPUS, 1);
		this.gerarObjetoMapa(MapaObjetos.OURO, 1);
	}

	public int getX_jogador() {
		return x_jogador;
	}

	public int getY_jogador() {
		return y_player;
	}

	// ===== FUNCOES USADAS PELA GUI ======

	public int[][] getMap() {
		return mapa;
	}

	// ======== GERAR MAPA INICIAL =======
	/**
	 * Função para gerar o mapa preenchendo todo com zeros - estado inicial para
	 * preencher posteriormente com objetos.
	 */
	private void gerarMapa() {
		for (int i = 0; i < this.getDimensao(); i++) {
			for (int j = 0; j < this.getDimensao(); j++) {
				this.mapa[i][j] = 0;
			}
		}
	}

	/**
	 * Gerar posição do jogador.
	 * 
	 * @param linha
	 * @param coluna
	 */
	private void gerarPosicaoJogador(int linha, int coluna) {
		mapa[linha][coluna] = MapaObjetos.JOGADOR.getValor();
		this.salasOcupadas.add(new Sala(this.getX_jogador(), this.getY_jogador()));
	}

	/**
	 * Gerar objeto (buraco, wumpus, ouro) no mapa com sua respectiva quantidade.
	 * 
	 * @param objeto
	 * @param quantidade
	 */
	private void gerarObjetoMapa(MapaObjetos objeto, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			Sala sala = this.getRandomSala(this.getDimensao());
			mapa[sala.getLinha()][sala.getColuna()] = objeto.getValor();
			this.salasOcupadas.add(sala);
		}
	}

	/**
	 * randomizar a posição no mapa (sala) onde será inserido algum objeto.
	 * 
	 * @param qtd
	 * @return
	 */
	private Sala getRandomSala(int qtd) {
		Random random = new Random();
		int linha = random.nextInt(qtd - 1);
		int coluna = random.nextInt(qtd - 1);

		if (this.salasOcupadas.containsSala(new Sala(linha, coluna))) {
			return this.getRandomSala(qtd);
		}
		return new Sala(linha, coluna);
	}

	// ===== FUNÇÕES DO MAPA =======

	public String getInitialPosition() {
		for (int i = 0; i < this.getDimensao(); i++)
			for (int j = 0; j < this.getDimensao(); j++)
				if (mapa[i][j] == 1)
					return i + " " + j;

		return this.getDimensao() + " 0";
	}

	/**
	 * Informa que o jogador esta ou passou por aqui.
	 * 
	 * @param linha
	 * @param coluna
	 */
	public void estouAqui(int linha, int coluna) {
		mapa[linha][coluna] = 9;
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public void imOuThis(int linha, int coluna) {
		// informo que o player passou por la mas voltou
		mapa[linha][coluna] = 8;
	}

	// recupera o que tem na sala / 0 - nada / 1 - ouro / 2 - buraco / 3 - wumpus
	/**
	 * Recupera o objeto que está contido na sala:
	 * 
	 * 0 - nada / 1 - ouro / 2 - buraco / 3 - wumpus
	 * 
	 * @param linha
	 * @param coluna
	 * @return
	 */
	public int whatIsInThisPlace(int linha, int coluna) {
		if (mapa[linha][coluna] == MapaObjetos.OURO.getValor())// Ouro
			return 1;
		if (mapa[linha][coluna] == MapaObjetos.BURACO.getValor())// Buraco
			return 2;
		if (mapa[linha][coluna] == MapaObjetos.WUMPUS.getValor())// Wumpus
			return 3;

		return 0;
	}

	// ======= RECUPERA FRONTEIRAS ========

	/**
	 * recupera fronteira de cima
	 * 
	 * @param cur
	 * @return
	 */
	public Sala getFronteiraCima(Sala cur) {
		Sala proxima = null;

		// se nao estiver no mapa retorna nulo
		if (!dentroMapa(cur.getLinha() - 1, cur.getColuna()))
			return null;

		// retorno a sala fronteira
		proxima = new Sala(cur.getLinha() - 1, cur.getColuna());

		return proxima;
	}

	/**
	 * recupera fronteira de baixo
	 * 
	 * @param cur
	 * @return
	 */
	public Sala getFronteiraBaixo(Sala cur) {
		Sala proxima = null;

		// se nao esiver no mapa retorna nulo
		if (!dentroMapa(cur.getLinha() + 1, cur.getColuna()))
			return null;

		// retorno a sala fronteira
		proxima = new Sala(cur.getLinha() + 1, cur.getColuna());

		return proxima;
	}

	/**
	 * recupera fronteira da direita
	 * 
	 * @param cur
	 * @return
	 */
	public Sala getFronteiraDireita(Sala cur) {
		Sala proxima = null;

		// se nao esiver no mapa retorna nulo
		if (!dentroMapa(cur.getLinha(), cur.getColuna() + 1))
			return null;

		// retorno a sala fronteira
		proxima = new Sala(cur.getLinha(), cur.getColuna() + 1);

		return proxima;
	}

	/**
	 * recupera fronteira da esquerda
	 * 
	 * @param cur
	 * @return
	 */
	public Sala getFronteiraEsquerda(Sala cur) {
		Sala proxima = null;

		// se nao esiver no mapa retorna nulo
		if (!dentroMapa(cur.getLinha(), cur.getColuna() - 1))
			return null;

		// retorno a sala fronteira
		proxima = new Sala(cur.getLinha(), cur.getColuna() - 1);

		return proxima;
	}

	// ===== VERIFICA SENSORES =====

	// 0 Nada / 1 Vento / 2 cheiro
	public int verifySensors(int linha, int coluna) {
		if (mapa[linha][coluna] == 3)
			return 1;
		else if (mapa[linha][coluna] == 4)
			return 2;

		return 0;
	}

	/**
	 * verifica se a coordenada está no mapa
	 * 
	 * @param linha
	 * @param coluna
	 * @return
	 */
	private boolean dentroMapa(int linha, int coluna) {
		if (linha >= this.getDimensao() || linha < 0)
			return false;
		if (coluna >= this.getDimensao() || coluna < 0)
			return false;

		return true;
	}

	/**
	 * ordem da dimensão do mapa (linhas x colunas)
	 * 
	 * @return
	 */
	public int getDimensao() {
		return dimensao;
	}
}
