import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class AG {

	public static String caracteres = "NSLO";
	
	int numGenes;
	int tamPop;
	int tamGer;
	Populacao populacao;

	String solucaoAnterior = "";
	String solucaoAtual = "";
	int solucaoRepetiu = 0;
	private List<Individuo> melhoresIndividuos;
	
	
	public AG(int numGenes, int tamPop, int tamGer, Mapa mapa) {
		this.tamGer = tamGer;
	    populacao = new Populacao(numGenes, tamPop, mapa);
		this.melhoresIndividuos = new ArrayList<>();
	}

	public void ordenaPorMelhorSolucao(List<Individuo> lista)
	{
		Collections.sort(lista, (Individuo o1, Individuo o2) -> {
			//TODO testar nulos
			if (o1.getAptidaoMov() < o2.getAptidaoMov()) {
				return 1;
			}

			if (o1.getAptidaoMov() > o2.getAptidaoMov()) {
				return -1;
			}

			return 0;
		});
	}

	public String  run(Mapa mapa) {
		tamPop = populacao.getTamPopulacao();

		System.out.println("\nPopulação inicial: " + populacao);
		for (int i = 0; i <= this.tamGer; i++) {

			//cria uma geração a partir da população
			Geracao g = new Geracao(populacao);
			g.run(mapa);

			System.out.println("\nGeração: " + i);

			tamPop = g.getPopulacao().getTamPopulacao();
			populacao = g.getPopulacao();

			this.ordenaPorMelhorSolucao(populacao.getIndividuos());
			
			this.melhoresIndividuos.add(populacao.getIndividuos().get(0));
			System.out.println("\nMelhor gene: " + populacao.getIndividuos().get(0).getGenes());

		}

		this.ordenaPorMelhorSolucao(this.melhoresIndividuos);
		Individuo melhor = this.melhoresIndividuos.get(0);

		System.out.println(melhor);

		return melhor.getGenes();
	}

}