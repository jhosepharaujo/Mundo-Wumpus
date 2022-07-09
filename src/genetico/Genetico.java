package genetico;

import static genetico.Main.mapa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que o algoritmo genético, onde é passado o tamanho da população, a
 * quantidade de gerações e o número máximo de genes de um indivíduo.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class Genetico {

	private static final int NUM_IND_SELECIONADOS = 2;

	Populacao populacao = null;
	String solucaoAnterior = "";
	String solucaoAtual = "";
	int solucaoRepetiu = 0;
	int tamanhoPopulacaoInicial;

	public String run(int tamPop, int tamGer, int rangeNumGenes) {
		populacao = new Populacao(rangeNumGenes, tamPop);
		this.tamanhoPopulacaoInicial = tamPop;
		System.out.println("\nPopulação inicial: " + populacao);
		
		for (int i = 0; i <= tamGer; i++) {
			solucaoAnterior = solucaoAtual;

			Geracao g = new Geracao(populacao, rangeNumGenes);
			g.run();

			System.out.println("\nGeração: " + i);
			System.out.println("\nPopulação: " + populacao.getTamPopulacao());

			populacao = g.getPopulacao();

			Individuo in0 = populacao.getIndividuos().get(0);
			solucaoAtual = in0.getGene().getGene();

			System.out.println(solucaoAtual);

			if (populacao.populacaoComTodosIndividuosIguais()) {
				populacao.getIndividuos().clear();
				populacao = new Populacao(rangeNumGenes, this.tamanhoPopulacaoInicial - 1);
				populacao.addIndividuos(in0);

				Collections.sort(this.populacao.getIndividuos(), (Individuo o1, Individuo o2) -> {
					if (o1.getAptidaoMov() < o2.getAptidaoMov()) {
						return 1;
					}

					if (o1.getAptidaoMov() > o2.getAptidaoMov()) {
						return -1;
					}

					return 0;
				});
			}

			if (solucaoAnterior.equals(solucaoAtual) && mapa.runSolucao(in0.getGene().getGene())) {
				solucaoRepetiu++;

				System.out.println("Solução Encontrada: " + solucaoAtual);

				// tenta verificar melhor solução 10% da geração vezes
				int condicaoParada = (tamGer * 10) / 100;

				if (solucaoRepetiu > condicaoParada) {
					break;
				}
			}
		}

		if (solucaoRepetiu == 0) {
			System.out.println("A quantidade de gerações não foi suficiente para achar a solução!");
			return "";
		}

		return solucaoAtual;
	}

	/**
	 * Selecionar Indivíduos da população
	 * 
	 * @param individuos
	 * @return
	 */
	public static List<Individuo> selecionarIndividuos(List<Individuo> individuos) {

		List<Individuo> selecionados = new ArrayList<>();

		for (int i = 0; i < NUM_IND_SELECIONADOS; i++) {
			int random = Util.numeroAleatorio(0, individuos.size());
			selecionados.add(individuos.get(random));
		}
		return selecionados;
	}

	public static List<Individuo> cruzarIndividuos(List<Individuo> individuosSelecionados) {

		List<String> partes = new ArrayList<>();
		List<Gene> listaGenes = new ArrayList<>();
		List<Individuo> novosIndividuos = new ArrayList<>();

		for (Individuo individuo : individuosSelecionados) {
			int sizeGene = individuo.getGene().getGene().length();
			int radom1 = Util.numeroAleatorio(1, sizeGene);
			int radom2 = Util.numeroAleatorio(1, sizeGene);

			// tres partes do gene
			String p1 = "";
			String p2 = "";
			String p3 = "";

			for (int i = 0; i < sizeGene; i++) {

				char gene = individuo.getGene().getGene().charAt(i);

				if (i <= radom1) {
					p1 = p1 + gene;
				} else if (i >= radom1 && i <= radom2) {
					p2 = p2 + gene;
				} else if (i >= radom2) {
					p3 = p3 + gene;
				}

			}

			partes.add(p1);
			partes.add(p2);
			partes.add(p3);
		}

		for (int i = 0; i < individuosSelecionados.size(); i++) {
			// int random = Util.numeroAleatorio(0, partes.size() - 1);
			String p1 = partes.get(0);
			String p2 = partes.get(1);
			String p3 = partes.get(2);

			listaGenes.add(new Gene(p1, p2, p3));

			partes.remove(0);
			partes.remove(0);
			partes.remove(0);
		}

		for (Gene gene : listaGenes) {
			novosIndividuos.add(new Individuo(gene.getGene()));
		}

		return novosIndividuos;
	}

	public static Individuo mutarIndividuo(List<Individuo> novosIndividuos, int escolhido) {
		String opcoes = "NSLO";

		Individuo in = novosIndividuos.get(escolhido);
		int radom = Util.numeroAleatorio(0, opcoes.length());
		char mutacao = opcoes.charAt(radom);
		int radom1 = Util.numeroAleatorio(0, in.getGene().getGene().length());
		StringBuffer sb = new StringBuffer(in.getGene().getGene());
		sb.replace(radom1, radom1 + 1, mutacao + "");
		String mutado = sb.toString();
		in.getGene().setGene((mutado));

		return new Individuo(in.getGene().getGene());
	}

}
