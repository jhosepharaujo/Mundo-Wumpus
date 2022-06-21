import java.util.Random;

public class Mapa {

	private int[][] mapa;
	private int dimensao;
	private int x_player;
	private int y_player;
	private Lista salasOcupadas;

	Mapa(int dimensao) {
		this.dimensao = dimensao;
		this.mapa = new int[dimensao][dimensao];
		this.x_player = dimensao - 1;
		this.y_player = 0;
		this.salasOcupadas = new Lista();
		this.generateMapa();
		this.generatePositionPlayer(this.getX_player(), this.getY_player());
		this.generateObjetoMapa(MapaObjetos.BURACO, dimensao - 1);
		this.generateObjetoMapa(MapaObjetos.WUMPUS, 1);
		this.generateObjetoMapa(MapaObjetos.OURO, 1);
	}

	public int getX_player() {
		return x_player;
	}

	public int getY_player() {
		return y_player;
	}

	// ===== FUNCOES USADAS PELA GUI ======

	public int[][] getMap() {
		return mapa;
	}

	// ======== GERAR MAPA INICIAL =======
	private void generateMapa() {
		for (int i = 0; i < this.getDimensao(); i++) {
			for (int j = 0; j < this.getDimensao(); j++) {
				this.mapa[i][j] = 0;
			}
		}
	}

	private void generatePositionPlayer(int linha, int coluna) {
		mapa[linha][coluna] = MapaObjetos.JOGADOR.getValor();
		this.salasOcupadas.add(new Sala(this.getX_player(), this.getY_player()));
	}

	
	private void generateObjetoMapa(MapaObjetos objeto, int quantidade)
	{
		for (int i = 0; i < quantidade; i++) {
			Sala sala = this.getRandomSala(this.getDimensao());
			mapa[sala.getLinha()][sala.getColuna()] = objeto.getValor();
			this.salasOcupadas.add(sala);
		}
	}

	private Sala getRandomSala(int qtd) {
		Random random = new Random();
		int linha = random.nextInt(qtd - 1);
		int coluna = random.nextInt(qtd - 1);
		Sala sala = new Sala(linha, coluna);
		if (this.salasOcupadas.containsSala(new Sala(linha, coluna))) {
			return this.getRandomSala(qtd);
		}
		return new Sala(linha, coluna);
	}

	// ===== FUNCOES DO MAPA =======

	public String getInitialPosition() {
		for (int i = 0; i < this.getDimensao(); i++)
			for (int j = 0; j < this.getDimensao(); j++)
				if (mapa[i][j] == 1)
					return i + " " + j;

		return this.getDimensao() + " 0";
	}

	public void imInHere(int linha, int coluna) {
		// informo que o player esta ou passou por aqui
		mapa[linha][coluna] = 9;
	}

	public void imOuThis(int linha, int coluna) {
		// informo que o player passou por la mas voltou
		mapa[linha][coluna] = 8;
	}

	// recupera o que tem na sala / 0 - nada / 1 - ouro / 2 - buraco / 3 - wumpus
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

	// recupera fronteira de cima
	public Sala getFrontierTop(Sala cur) {
		Sala next = null;

		// se nao esiver no mapa retorna nulo
		if (!inMap(cur.getLinha() - 1, cur.getColuna()))
			return null;

		// retorno a sala fronteira
		next = new Sala(cur.getLinha() - 1, cur.getColuna());

		return next;
	}

	// recupera fronteira de baixo
	public Sala getFrontierBot(Sala cur) {
		Sala next = null;

		// se nao esiver no mapa retorna nulo
		if (!inMap(cur.getLinha() + 1, cur.getColuna()))
			return null;

		// retorno a sala fronteira
		next = new Sala(cur.getLinha() + 1, cur.getColuna());

		return next;
	}

	// recupera fronteira da direita
	public Sala getFrontierRight(Sala cur) {
		Sala next = null;

		// se nao esiver no mapa retorna nulo
		if (!inMap(cur.getLinha(), cur.getColuna() + 1))
			return null;

		// retorno a sala fronteira
		next = new Sala(cur.getLinha(), cur.getColuna() + 1);

		return next;
	}

	// recupera fronteira da esquerda
	public Sala getFrontierLeft(Sala cur) {
		Sala next = null;

		// se nao esiver no mapa retorna nulo
		if (!inMap(cur.getLinha(), cur.getColuna() - 1))
			return null;

		// retorno a sala fronteira
		next = new Sala(cur.getLinha(), cur.getColuna() - 1);

		return next;
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

	// verifica se a coordenada esta no mapa
	private boolean inMap(int linha, int coluna) {
		if (linha >= this.getDimensao() || linha < 0)
			return false;
		if (coluna >= this.getDimensao() || coluna < 0)
			return false;

		return true;
	}

	public int getDimensao() {
		return dimensao;
	}
}
