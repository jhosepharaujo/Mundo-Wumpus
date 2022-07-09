package genetico;

import static genetico.Util.caracteres;
import static genetico.Main.mapa;

public final class Individuo {

	private Gene gene;
	private int aptidao = 0;

	public Individuo(String genes) {
		this.gene = new Gene(genes);
		avaliarPerformance(this.gene.getGene());
	}

	// gera um indivíduo aleatório
	public Individuo(int rangeNumGenes) {

		int numero = Util.numeroAleatorio(10, rangeNumGenes);
		String seqGene = "";
		for (int i = 0; i < numero; i++) {
			// Ssorteia uma posicao aleatoria dos caracteres definidos
			seqGene += caracteres.charAt(Util.numeroAleatorio(0, caracteres.length()));
		}
		this.gene = new Gene(seqGene);
		avaliarPerformance(this.gene.getGene());
	}

	public void avaliarPerformance(String genes) {
		boolean japegouOuro = false;
		boolean jachegou = false;

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

			int percepcao = mapa.getPercepcao(movimentoX, moviemntoY);
			
			switch (percepcao) {
			// nada
			case 0:
				if (japegouOuro) {
					aptidao = aptidao + 10;
				} else {
					aptidao = aptidao - 10;
				}
				break;

			// fedor
			case 2:
				if (japegouOuro) {
					aptidao = aptidao + 10;
				} else {
					aptidao = aptidao - 10;
				}
				break;

			// brisa
			case 4:
				if (japegouOuro) {
					aptidao = aptidao + 10;
				} else {
					aptidao = aptidao - 10;
				}
				break;

			// ouro
			case 6:
				if (japegouOuro == false) {
					aptidao = aptidao + 10000;
					japegouOuro = true;
				} else {
					aptidao = aptidao - 100;
				}
				break;

			// foco
			case 3:
				if (japegouOuro) {
					aptidao = aptidao - 1000;
				} else {
					aptidao = aptidao - 100;
				}
				break;

			// wumpus
			case 1:
				if (japegouOuro) {
					aptidao = aptidao - 1000;
				} else {
					aptidao = aptidao - 100;
				}
				break;
			case 50:
				if (japegouOuro) {
					aptidao = aptidao - 1000;
				} else {
					aptidao = aptidao - 500;
				}
				break;
			}
			
			if (japegouOuro) {
				if (jachegou == false) {
					if (moviemntoY == 0 && movimentoX == 0) {
						aptidao = aptidao + 100000;
						jachegou = true;
					} else {
						aptidao = aptidao - 100;
					}
				} else if (jachegou) {
					aptidao = aptidao - 10000;
				}
			}
		}
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public int getAptidaoMov() {
		return aptidao;
	}

	@Override
	public boolean equals(Object obj) {
		Individuo individuo = (Individuo) obj;
		return this.gene.getGene().equals(individuo.gene.getGene());
	}

}
