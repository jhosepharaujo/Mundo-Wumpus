package genetico;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import comumUtil.ListaSalas;
import comumUtil.MapaObjetos;
import comumUtil.Sala;

/**
 * Classe que representa o mapa do ambiente para o algoritmo genético.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class Mapa {

	public int mapa[][];
	int mapaMov[][];
	public static int percepcao = 0;

	int aptidao = 0;
	int dimensao;
	private ListaSalas salasOcupadas;
	private int x_jogador;
	private int y_jogador;

	public Mapa(int dimensao, int qtd_buraco) {

		this.mapa = new int[dimensao][dimensao];
		this.mapaMov = new int[dimensao][dimensao];
		this.dimensao = dimensao;
		this.salasOcupadas = new ListaSalas();
		this.x_jogador = 0;
		this.y_jogador = 0;

		this.gerarPosicaoJogador(this.x_jogador, this.y_jogador);
		this.gerarObjetoMapa(MapaObjetos.BURACO, qtd_buraco);
		this.gerarObjetoMapa(MapaObjetos.WUMPUS, 1);
		this.gerarObjetoMapa(MapaObjetos.OURO, 1);
	}

	public int[][] getmapa() {
		return mapa;
	}

	private void gerarPosicaoJogador(int linha, int coluna) {
		mapa[linha][coluna] = MapaObjetos.JOGADOR.getValor();
		this.salasOcupadas.adicionar(new Sala(linha, coluna));
	}

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
		
		if (Util.wumpus == objeto.getValor() || Util.buraco == objeto.getValor()) {
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
		if (Util.wumpus == objeto.getValor()) {
			Sala salaCima = new Sala(sala.getLinha() + 1, sala.getColuna());
			Sala salaBaixo = new Sala(sala.getLinha() - 1, sala.getColuna());
			Sala salaDireita = new Sala(sala.getLinha(), sala.getColuna() + 1);
			Sala salaEsquerda = new Sala(sala.getLinha(), sala.getColuna() - 1);
			
			// [l+1][c]
			if (salaCima.getLinha() < dimensao) {
				this.mapa[salaCima.getLinha()][salaCima.getColuna()] = Util.fedor;
				this.salasOcupadas.adicionar(salaCima);
			}

			// [l-1][c]
			if (salaBaixo.getLinha() >= 0) {
				this.mapa[salaBaixo.getLinha()][salaBaixo.getColuna()] = Util.fedor;
				this.salasOcupadas.adicionar(salaBaixo);
			}

			// [l][c+1]
			if (salaDireita.getColuna() < dimensao) {
				this.mapa[salaDireita.getLinha()][salaDireita.getColuna()] = Util.fedor;
				this.salasOcupadas.adicionar(salaDireita);
			}

			// [l][c-1]
			if (salaEsquerda.getColuna() >= 0) {
				this.mapa[salaEsquerda.getLinha()][salaEsquerda.getColuna()] = Util.fedor;
				this.salasOcupadas.adicionar(salaEsquerda);
			}
		}

		if (Util.buraco == objeto.getValor()) {

			Sala salaCima = new Sala(sala.getLinha() + 1, sala.getColuna());
			Sala salaBaixo = new Sala(sala.getLinha() - 1, sala.getColuna());
			Sala salaDireita = new Sala(sala.getLinha(), sala.getColuna() + 1);
			Sala salaEsquerda = new Sala(sala.getLinha(), sala.getColuna() - 1);

			// [l+1][c]
			if (salaCima.getLinha() < dimensao) {
				this.mapa[salaCima.getLinha()][salaCima.getColuna()] = Util.brisa;
				this.salasOcupadas.adicionar(salaCima);
			}

			// [l-1][c]
			if (salaBaixo.getLinha() >= 0) {
				this.mapa[salaBaixo.getLinha()][salaBaixo.getColuna()] = Util.brisa;
				this.salasOcupadas.adicionar(salaBaixo);
			}

			// [l][c+1]
			if (salaDireita.getColuna() < dimensao) {
				this.mapa[salaDireita.getLinha()][salaDireita.getColuna()] = Util.brisa;
				this.salasOcupadas.adicionar(salaDireita);
			}

			// [l][c-1]
			if (salaEsquerda.getColuna() >= 0) {
				this.mapa[salaEsquerda.getLinha()][salaEsquerda.getColuna()] = Util.brisa;
				this.salasOcupadas.adicionar(salaEsquerda);
			}
		}
	}

	public int getPercepcao(int movX, int movY) {

		try {
			return mapa[movX][movY];
		} catch (Exception e) {
			return 50;// numero não entra na logica
		}

	}

	public boolean runSolucao(String genes) {
		boolean result = false;
		boolean japegouOuro = false;
		boolean movimentoFora = false;

		int sizeGene = genes.length();

		int movimentoX = 0;
		int moviemntoY = 0;

		for (int i = 0; i < sizeGene; i++) {

			char gene = genes.charAt(i);

			switch (gene) {
			case 'N':
				movimentoX++;
				break;
			case 'S':
				movimentoX--;
				break;
			case 'L':
				moviemntoY++;
				break;
			case 'O':
				moviemntoY--;
				break;
			}

			if (getPercepcao(movimentoX, moviemntoY) == 6 && japegouOuro == false) {
				japegouOuro = true;
			}

			if (getPercepcao(movimentoX, moviemntoY) == 50) {
				movimentoFora = true;
			}

			if (getPercepcao(movimentoX, moviemntoY) == 1 || getPercepcao(movimentoX, moviemntoY) == 3) {
				return false;
			}
		}

		if (movimentoX == 0 && moviemntoY == 0 && japegouOuro == true && movimentoFora == false) {
			result = true;
		}

		return result;
	}

	public void runAag(String genes) throws InterruptedException {

		int sizeGene = genes.length();

		int movimentoX = 0;
		int moviemntoY = 0;

		for (int i = 0; i < sizeGene; i++) {

			char gene = genes.charAt(i);

			switch (gene) {
			case 'N':
				movimentoX++;
				break;
			case 'S':
				movimentoX--;
				break;
			case 'L':
				moviemntoY++;
				break;
			case 'O':
				moviemntoY--;
				break;
			}

			int percpcao = getPercepcao(movimentoX, moviemntoY);

			System.out.println(Util.imprimirMapaConfiguracaoInicial(this.mapa));
			Util.imprimirLegenda();
			System.out.println(Util.imprimirMapaPosicaoJogador(movimentoX, moviemntoY, dimensao));

			if (percpcao == Util.nada) {
				System.out.println("Percepção: nada na sala");
			} else if (percpcao == Util.brisa) {
				System.out.println("Percepção: jogador sentiu brisa");
			} else if (percpcao == Util.buraco) {
				System.out.println("Percepção: jogador morreu");
				System.out.println("Causa: jogador caiu no buraco");
			} else if (percpcao == Util.fedor) {
				System.out.println("Percepção: jogador sentiu fedor");
			} else if (percpcao == Util.wumpus) {
				System.out.println("Percepção: jogador morreu");
				System.out.println("Causa: wumpus devorou o jogador");
			} else if (percpcao == Util.ouro) {
				System.out.println("Percepção: jogador achou o ouro");
			}

			try {
				System.out.println("Solução Encontrada: " + genes);
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
				
				Thread.sleep(1000);

			} catch (Exception ex) {
				Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

	}
}
