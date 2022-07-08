package reativo;
import java.util.Random;

import comumUtil.MapaObjetos;

/**
 * Classe para a montagem e definições das configurações do mapa do jogo.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 * 
 */
public class Mapa {
	
	public static int nda = MapaObjetos.NADA.getValor();;
	public static int jogador = MapaObjetos.JOGADOR.getValor();
	public static int ouro = MapaObjetos.OURO.getValor();
	public static int wumpus = MapaObjetos.WUMPUS.getValor();
	public static int fedor = MapaObjetos.FEDOR.getValor();
	public static int buraco = MapaObjetos.BURACO.getValor();
	public static int brisa = MapaObjetos.BRISA.getValor();

	private int[][] mapa;
	private int dimensao;
	private int x_jogador;
	private int y_jogador;
	private ListaSalas salasOcupadas;

	public Mapa(int dimensao) {
		this.dimensao = dimensao;
		this.mapa = new int[dimensao][dimensao];
		this.salasOcupadas = new ListaSalas();
		this.x_jogador = dimensao - 1;
		this.y_jogador = 0;
		
		this.gerarMapa();
		this.gerarPosicaoJogador(x_jogador, y_jogador);
		this.gerarObjetoMapa(MapaObjetos.BURACO, 2);
		this.gerarObjetoMapa(MapaObjetos.WUMPUS, 1);
		this.gerarObjetoMapa(MapaObjetos.OURO, 1);
	}

	public int getX_jogador() {
		return x_jogador;
	}

	public int getY_jogador() {
		return y_jogador;
	}
	
	/**
	 * ordem da dimensão do mapa (linhas x colunas)
	 * 
	 * @return
	 */
	public int getDimensao() {
		return dimensao;
	}

	// ===== FUNCOES USADAS PELA GUI ======

	public int[][] getMapa() {
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
		this.salasOcupadas.adicionar(new Sala(linha, coluna));
	}

	/**
	 * Gerar objeto (buraco, wumpus, ouro) no mapa com sua respectiva quantidade.
	 * 
	 * @param objeto
	 * @param quantidade
	 */
	private void gerarObjetoMapa(MapaObjetos objeto, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			Sala sala = this.getRandomSala(this.dimensao, objeto);
			this.mapa[sala.getLinha()][sala.getColuna()] = objeto.getValor();
			this.salasOcupadas.adicionar(sala);
		}
	}

	private Sala getRandomSala(int qtd, MapaObjetos objeto) {
		Random random = new Random();
		int linha = random.nextInt(qtd - 1);
		int coluna = random.nextInt(qtd - 1);
		Sala sala = new Sala(linha, coluna);

		if (this.salasOcupadas.containsSala(sala)) {
			return this.getRandomSala(qtd, objeto);
		}
		
		if (wumpus == objeto.getValor() || buraco == objeto.getValor()) {
			if (this.salasAdjacentesLivres(sala)) {
				this.mapa[sala.getLinha()][sala.getColuna()] = objeto.getValor();
				this.salasOcupadas.adicionar(sala);
				this.setarSensoresAdjacentes(objeto, sala);
			} else {
				return this.getRandomSala(qtd, objeto);
			}
		}

		return sala;
	}

	private boolean salasAdjacentesLivres(Sala sala) {
		// [l+1][c]
		if (sala.getLinha() + 1 < dimensao) {
			if (this.salasOcupadas.containsSala(new Sala(sala.getLinha() + 1, sala.getColuna()))) {
				return false;
			}
		}

		// [l-1][c]
		if (sala.getLinha() - 1 >= 0) {
			if (this.salasOcupadas.containsSala(new Sala(sala.getLinha() - 1, sala.getColuna()))) {
				return false;
			}
		}

		// [l][c+1]
		if (sala.getColuna() + 1 < dimensao) {
			if (this.salasOcupadas.containsSala(new Sala(sala.getLinha(), sala.getColuna() + 1))) {
				return false;
			}
		}

		// [l][c-1]
		if (sala.getColuna() - 1 >= 0) {
			if (this.salasOcupadas.containsSala(new Sala(sala.getLinha(), sala.getColuna() - 1))) {
				return false;
			}
		}

		return true;
	}

	private void setarSensoresAdjacentes(MapaObjetos objeto, Sala sala) {
		if (wumpus == objeto.getValor()) {
			Sala salaCima = new Sala(sala.getLinha() + 1, sala.getColuna());
			Sala salaBaixo = new Sala(sala.getLinha() - 1, sala.getColuna());
			Sala salaDireita = new Sala(sala.getLinha(), sala.getColuna() + 1);
			Sala salaEsquerda = new Sala(sala.getLinha(), sala.getColuna() - 1);
			
			// [l+1][c]
			if (salaCima.getLinha() < dimensao) {
				this.mapa[salaCima.getLinha()][salaCima.getColuna()] = fedor;
				this.salasOcupadas.adicionar(salaCima);
			}

			// [l-1][c]
			if (salaBaixo.getLinha() >= 0) {
				this.mapa[salaBaixo.getLinha()][salaBaixo.getColuna()] = fedor;
				this.salasOcupadas.adicionar(salaBaixo);
			}

			// [l][c+1]
			if (salaDireita.getColuna() < dimensao) {
				this.mapa[salaDireita.getLinha()][salaDireita.getColuna()] = fedor;
				this.salasOcupadas.adicionar(salaDireita);
			}

			// [l][c-1]
			if (salaEsquerda.getColuna() >= 0) {
				this.mapa[salaEsquerda.getLinha()][salaEsquerda.getColuna()] = fedor;
				this.salasOcupadas.adicionar(salaEsquerda);
			}
		}

		if (buraco == objeto.getValor()) {

			Sala salaCima = new Sala(sala.getLinha() + 1, sala.getColuna());
			Sala salaBaixo = new Sala(sala.getLinha() - 1, sala.getColuna());
			Sala salaDireita = new Sala(sala.getLinha(), sala.getColuna() + 1);
			Sala salaEsquerda = new Sala(sala.getLinha(), sala.getColuna() - 1);

			// [l+1][c]
			if (salaCima.getLinha() < dimensao) {
				this.mapa[salaCima.getLinha()][salaCima.getColuna()] = brisa;
				this.salasOcupadas.adicionar(salaCima);
			}

			// [l-1][c]
			if (salaBaixo.getLinha() >= 0) {
				this.mapa[salaBaixo.getLinha()][salaBaixo.getColuna()] = brisa;
				this.salasOcupadas.adicionar(salaBaixo);
			}

			// [l][c+1]
			if (salaDireita.getColuna() < dimensao) {
				this.mapa[salaDireita.getLinha()][salaDireita.getColuna()] = brisa;
				this.salasOcupadas.adicionar(salaDireita);
			}

			// [l][c-1]
			if (salaEsquerda.getColuna() >= 0) {
				this.mapa[salaEsquerda.getLinha()][salaEsquerda.getColuna()] = brisa;
				this.salasOcupadas.adicionar(salaEsquerda);
			}
		}
	}

	// ===== FUNÇÕES DO MAPA =======


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
	public int recuperaObjetoNaSala(int linha, int coluna) {
		if (mapa[linha][coluna] == MapaObjetos.OURO.getValor())// Ouro
			return 1;
		if (mapa[linha][coluna] == MapaObjetos.BURACO.getValor())// Buraco
			return 2;
		if (mapa[linha][coluna] == MapaObjetos.WUMPUS.getValor())// Wumpus
			return 3;

		return 0;
	}

	/**
	 * Retorna a percepção do jogador para aquela posição
	 * 
	 * @param movX
	 * @param movY
	 * @return
	 */
	public int getPercepcao(int movX, int movY) {
        try {
            return mapa[movX][movY];
        } catch (Exception e) {
            return 50; //indicar exceção
        }

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

}