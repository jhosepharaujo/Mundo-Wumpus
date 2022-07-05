import java.util.Collections;
import java.util.List;

public class Geracao {

	public Populacao populacao;
	public List<Individuo> individuosSelecionados;
	public List<Individuo> novosIndividuos;
	public Individuo mutado1;
	public Individuo mutado2;
	public int selecionado1;
	public int selecionado2;

	public Geracao(Populacao populacao) {
		this.populacao = populacao;
	}

	public void run(Mapa mapa) {
		selecionarIndividuos(Util.numeroAleatorio(1, populacao.getTamPopulacao()/2));
		cruzarIndividuos(mapa);
		mutarIndividuo(mapa);

		//rank();

	}

	public void rank() {

		int size = this.populacao.getIndividuos().size();

		Collections.sort(this.populacao.getIndividuos(), (Individuo o1, Individuo o2) -> {
			// TODO testar nulos
			if (o1.getAptidaoMov() < o2.getAptidaoMov()) {
				return 1;
			}

			if (o1.getAptidaoMov() > o2.getAptidaoMov()) {
				return -1;
			}

			return 0;
		});

		this.populacao.removeUtlimoIndividuo(size - 1);
		this.populacao.removeUtlimoIndividuo(size - 2);
		this.populacao.removeUtlimoIndividuo(size - 3);
		this.populacao.removeUtlimoIndividuo(size - 4);

		// System.out.println(this.populacao.getIndividuos());
	}

	public void selecionarIndividuos(int qtdSelecionados) {
		this.individuosSelecionados = Util.selecionarIndividuos(this.populacao.getIndividuos(), qtdSelecionados);
	}

	public void cruzarIndividuos(Mapa mapa) {
		this.novosIndividuos = Util.cruzarIndividuos(individuosSelecionados, mapa);
	}

	public void mutarIndividuo(Mapa mapa) {
		this.selecionado1 = Util.numeroAleatorio(0, this.individuosSelecionados.size());
		this.mutado1 = Util.mutarIndividuo(this.novosIndividuos, this.selecionado1, mapa);

		this.selecionado2 = Util.numeroAleatorio(0, this.individuosSelecionados.size());
		this.mutado2 = Util.mutarIndividuo(this.novosIndividuos, this.selecionado2, mapa);

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