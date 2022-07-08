package genetico;

import static genetico.Main.ambiente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Genetico {

	Populacao populacao = null;

	// Ambiente ambiente = new Ambiente();
	String solucaoAnterior = "";
	String solucaoAtual = "";
	int solucaoRepetiu = 0;

	public String run(int tamPop, int tamGer, int rangeNumGenes) {
		// população incial
		populacao = new Populacao(rangeNumGenes, tamPop);
		tamPop = populacao.getTamPopulacao();

		System.out.println("\nPopulação inicial: " + populacao);
		for (int i = 0; i <= tamGer; i++) {

			solucaoAnterior = solucaoAtual;

			Geracao g = new Geracao(populacao, rangeNumGenes);
			g.run();

			System.out.println("\nGeração: " + i);

			tamPop = g.getPopulacao().getTamPopulacao();
			populacao = g.getPopulacao();

			Individuo in0 = populacao.getIndividuos().get(0);
			Individuo in1 = populacao.getIndividuos().get(1);
			Individuo in2 = populacao.getIndividuos().get(2);
			Individuo in3 = populacao.getIndividuos().get(3);

			String e0 = in0.getGenes();
			String e1 = in1.getGenes();
			String e2 = in2.getGenes();
			String e3 = in3.getGenes();

			solucaoAtual = e0;

			System.out.println(e0);
			
			if (e0.equals(e1) || e0.equals(e2) || e0.equals(e3)) {

				// removo os tres individuos repetidos
				populacao.removeUtlimoIndividuo(populacao.getTamPopulacao() - 1);
				populacao.removeUtlimoIndividuo(populacao.getTamPopulacao() - 1);
				populacao.removeUtlimoIndividuo(populacao.getTamPopulacao() - 1);

				// add os novos
				populacao.addIndividuos(new Individuo(rangeNumGenes));
				populacao.addIndividuos(new Individuo(rangeNumGenes));
				populacao.addIndividuos(new Individuo(rangeNumGenes));

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

			}

			if (solucaoAnterior.equals(solucaoAtual) && ambiente.runSolucao(e0)) {
				solucaoRepetiu++;
				
				System.out.println("Solução Encontrada: " + solucaoAtual);
				
				//tenta verificar melhor solução 10% da geração vezes
				int condicaoParada = (tamGer*10)/100;
				
				if (solucaoRepetiu > condicaoParada) {
					break;
				}
			}
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

		// sorteia dois numeros
		int radom1 = Util.numeroAleatorio(0, individuos.size());
		int radom2 = Util.numeroAleatorio(0, individuos.size());
		int radom3 = Util.numeroAleatorio(0, individuos.size());
		int radom4 = Util.numeroAleatorio(0, individuos.size());

		selecionados.add(individuos.get(radom1));
		selecionados.add(individuos.get(radom2));
		selecionados.add(individuos.get(radom3));
		selecionados.add(individuos.get(radom4));

		return selecionados;
	}

	// faz o lance do sorteio
	public static List<Individuo> cruzarIndividuos(List<Individuo> individuosSelecionados) {

		// tamanho do gene do individuo1
		int sizeGene1 = individuosSelecionados.get(0).getGenes().length();

		// soterio dos valores com os tamanhos dos genes do individuo1
		int radom1 = Util.numeroAleatorio(1, sizeGene1);
		int radom2 = Util.numeroAleatorio(1, sizeGene1);

		// tres partes do indivio1
		String p1 = "";
		String p2 = "";
		String p3 = "";

		//
		for (int i = 0; i < sizeGene1; i++) {

			char gene = individuosSelecionados.get(0).getGenes().charAt(i);

			if (i <= radom1) {
				p1 = p1 + gene;
			} else if (i >= radom1 && i <= radom2) {
				p2 = p2 + gene;
			} else if (i >= radom2) {
				p3 = p3 + gene;
			}

		}

		// -----------------------------------------------------------------------------------------
		// tamanho do gene do individuo1
		int sizeGene2 = individuosSelecionados.get(1).getGenes().length();

		// soterio dos valores com os tamanhos dos genes do individuo1
		int radom3 = Util.numeroAleatorio(1, sizeGene2);
		int radom4 = Util.numeroAleatorio(1, sizeGene2);

		// tres partes do indivio1
		String p4 = "";
		String p5 = "";
		String p6 = "";

		//
		for (int i = 0; i < sizeGene2; i++) {

			char gene = individuosSelecionados.get(1).getGenes().charAt(i);

			if (i <= radom3) {
				p4 = p4 + gene;
			} else if (i >= radom3 && i <= radom4) {
				p5 = p5 + gene;
			} else if (i >= radom4) {
				p6 = p6 + gene;
			}

		}

		// -----------------------------------------------------------------------------------------
		// tamanho do gene do individuo1
		int sizeGene3 = individuosSelecionados.get(2).getGenes().length();

		// soterio dos valores com os tamanhos dos genes do individuo1
		int radom5 = Util.numeroAleatorio(1, sizeGene3);
		int radom6 = Util.numeroAleatorio(1, sizeGene3);

		// tres partes do indivio1
		String p7 = "";
		String p8 = "";
		String p9 = "";

		//
		for (int i = 0; i < sizeGene3; i++) {

			char gene = individuosSelecionados.get(2).getGenes().charAt(i);

			if (i <= radom5) {
				p7 = p7 + gene;
			} else if (i >= radom5 && i <= radom6) {
				p8 = p8 + gene;
			} else if (i >= radom6) {
				p9 = p9 + gene;
			}

		}

		// -----------------------------------------------------------------------------------------
		// tamanho do gene do individuo1
		int sizeGene4 = individuosSelecionados.get(3).getGenes().length();

		// soterio dos valores com os tamanhos dos genes do individuo1
		int radom7 = Util.numeroAleatorio(1, sizeGene4);
		int radom8 = Util.numeroAleatorio(1, sizeGene4);

		// tres partes do indivio1
		String p10 = "";
		String p11 = "";
		String p12 = "";

		//
		for (int i = 0; i < sizeGene4; i++) {

			char gene = individuosSelecionados.get(3).getGenes().charAt(i);

			if (i <= radom7) {
				p10 = p10 + gene;
			} else if (i >= radom7 && i <= radom8) {
				p11 = p11 + gene;
			} else if (i >= radom8) {
				p12 = p12 + gene;
			}

		}

		String i1 = p1 + p5 + p3;
		String i2 = p4 + p2 + p6;
		String i3 = p7 + p11 + p9;
		String i4 = p10 + p8 + p12;

		List<Individuo> novosIndividuos = new ArrayList<>();
		novosIndividuos.add(new Individuo(i1));
		novosIndividuos.add(new Individuo(i2));
		novosIndividuos.add(new Individuo(i3));
		novosIndividuos.add(new Individuo(i4));

		return novosIndividuos;
	}

	// na mutação eu acho erro e aletoreamente atriuo um resultado para ele sme me
	// preucupar se estra certo ou não
	public static Individuo mutarIndividuo(List<Individuo> novosIndividuos, int escolhido) {
		String opcoes = "NSLO";

		Individuo in = novosIndividuos.get(escolhido);
		int radom = Util.numeroAleatorio(0, opcoes.length());
		char mutacao = opcoes.charAt(radom);
		int radom1 = Util.numeroAleatorio(0, in.getGenes().length());
		StringBuffer sb = new StringBuffer(in.getGenes());
		sb.replace(radom1, radom1 + 1, mutacao + "");
		String mutado = sb.toString();
		in.setGenes(mutado);

		return new Individuo(in.getGenes());
	}

}
