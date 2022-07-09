package genetico;

import java.util.Collections;
import java.util.List;

/**
 * Classe que representa a geração com a população de indivíduos.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class Geracao {

	public Populacao populacao;
	public List<Individuo> individuosSelecionados;
	public List<Individuo> novosIndividuos;
	public Individuo mutado1;
	public Individuo mutado2;
	public int selecionado1;
	public int selecionado2;

	public Geracao(Populacao populacao, int rangeNumGenes) {
		this.populacao = populacao;

	}

	/**
	 * Método para executar os passos do algoritmo genético
	 */
	public void run() {
		selecionarIindividuos();
		cruzarIndividuos();
		mutarIndividuo();
		rank();
	}

	/**
	 * Ordena em ordem decrescente os indivíduos mais aptos. Retira ao final os
	 * menos aptos para se chegar ao mesmo tamanho de população inicial.
	 */
	public void rank() {
		Collections.sort(this.populacao.getIndividuos(), (Individuo o1, Individuo o2) -> {
			if (o1.getAptidaoMov() < o2.getAptidaoMov()) {
				return 1;
			}

			if (o1.getAptidaoMov() > o2.getAptidaoMov()) {
				return -1;
			}

			return 0;
		});

		while (this.populacao.getIndividuos().size() != this.populacao.getTamPopulacao()) {
			this.populacao.getIndividuos().remove(this.populacao.getIndividuos().size() - 1);
		}
	}

	/**
	 * Seleciona dois indivíduos da população ao acaso.
	 */
	public void selecionarIindividuos() {
		this.individuosSelecionados = Genetico.selecionarIndividuos(this.populacao.getIndividuos());
	}

	/**
	 * Realiza o cruzamento dos indivíduos selecionados.
	 */
	public void cruzarIndividuos() {
		this.novosIndividuos = Genetico.cruzarIndividuos(individuosSelecionados);
	}

	/**
	 * Reliza a mutação dos genes dos indivíduos selecionados.
	 */
	public void mutarIndividuo() {
		this.selecionado1 = Util.numeroAleatorio(0, this.individuosSelecionados.size());
		this.mutado1 = Genetico.mutarIndividuo(this.novosIndividuos, this.selecionado1);

		this.selecionado2 = Util.numeroAleatorio(0, this.individuosSelecionados.size());
		this.mutado2 = Genetico.mutarIndividuo(this.novosIndividuos, this.selecionado2);

		// atualizar individuo mutado na populaçao
		this.individuosSelecionados.remove(selecionado1);
		this.individuosSelecionados.add(mutado1);
		// atualizar individuo mutado na populaçao
		this.individuosSelecionados.remove(selecionado2);
		this.individuosSelecionados.add(mutado2);

		// adicono os novos individuos na população
		for (Individuo in : this.individuosSelecionados) {
			this.populacao.addIndividuos(in);
		}
	}

	public Populacao getPopulacao() {
		return populacao;
	}

}
