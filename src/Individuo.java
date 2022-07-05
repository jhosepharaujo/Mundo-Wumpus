
public final class Individuo {
	
	public static String caracteres = "NSLO";
	private String genes = "";
	private int aptidao = 0;
	private Mapa mapa;

	public Individuo(String genes, Mapa mapa) {
		this.genes = genes;
		this.mapa = mapa;
		avaliarPerformance(genes);
	}

	// gera um indivíduo aleatório
	public Individuo(int rangeNumGenes, Mapa mapa) {

		for (int i = 0; i < Util.numeroAleatorio(10, rangeNumGenes); i++) {
			// Sorteia uma posicao aleatoria dos caracteres definidos
			this.genes += caracteres.charAt(Util.numeroAleatorio(0, caracteres.length()));
		}

		this.mapa = mapa;

		avaliarPerformance(genes);
	}

	public void avaliarPerformance(String genes) {

		//String estadoAtual = "A";
		boolean achouOuro = false;
		boolean jaFinalizou = false;

		//String estadoPercepcao = "";

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

			//TODO 
			//int percepcao = ambiente.getPercepcao(movimentoX, moviemntoY);
			int percepcao = this.mapa.getPercepcao(movimentoX, moviemntoY);

			switch (percepcao) {

			// nada
			case 0:
				if (achouOuro) {
					aptidao = aptidao + 10;
				} else {
					aptidao = aptidao - 10;
				}
				break;

			// wumpus
			case 4:
				if (achouOuro) {
					aptidao = aptidao - 1000;
				} else {
					aptidao = aptidao - 100;
				}
				break;

			// fedor
			case 6:
				if (achouOuro) {
					aptidao = aptidao + 10;
				} else {
					aptidao = aptidao - 10;
				}
				break;

			// buraco
			case 3:
				if (achouOuro) {
					aptidao = aptidao - 1000;
				} else {
					aptidao = aptidao - 100;
				}
				break;

			// brisa
			case 5:
				if (achouOuro) {
					aptidao = aptidao + 10;
				} else {
					aptidao = aptidao - 10;
				}
				break;

			// ouro
			case 2:
				if (!achouOuro) {
					aptidao = aptidao + 10000;
					achouOuro = true;
				} else {
					aptidao = aptidao - 100;
				}
				break;

			case 50:
				if (achouOuro) {
					aptidao = aptidao - 1000;
				} else {
					aptidao = aptidao - 500;
				}
				break;

			}

			if (achouOuro) {
				if (jaFinalizou == false) {
					if (moviemntoY == 0 && movimentoX == 0) {
						aptidao = aptidao + 100000;
						jaFinalizou = true;
					} else {
						aptidao = aptidao - 100;
					}
				} else if (jaFinalizou) {
					aptidao = aptidao - 10000;
				}
			}

		}

	}

	public String getGenes() {
		return genes;
	}

	public void setGenes(String genes) {
		this.genes = genes;
	}

	public int getAptidaoMov() {
		return aptidao;
	}

	@Override
	public String toString() {
		return "Individuo{" + "genes=" + genes + ", sizeGenes=" + genes.length() + ", aptidao=" + aptidao + '}';
	}

}