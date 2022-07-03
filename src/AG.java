public class AG {

	public static String caracteres = "NSLO";
	
	int numGenes;
	int tamPop;
	int tamGer;
	Populacao populacao;

	String solucaoAnterior = "";
	String solucaoAtual = "";
	int solucaoRepetiu = 0;
	
	public AG(int numGenes, int tamPop, int tamGer) {
		//numGenes = 100;
		//tamPop = 3;
		//tamGer = 5000;

		populacao = new Populacao(numGenes, tamPop);
	}

	public String run() {
		tamPop = populacao.getTamPopulacao();

		System.out.println("\nPopulação inicial: " + populacao);
		for (int i = 0; i <= tamGer; i++) {

			solucaoAnterior = solucaoAtual;

			//cria uma geração a partir da população
			Geracao g = new Geracao(populacao);
			g.run();

			System.out.println("\nGeração: " + i);

			tamPop = g.getPopulacao().getTamPopulacao();
			populacao = g.getPopulacao();

			
			//TODO pegar o com maior aptidão
			solucaoAtual = populacao.getIndividuos().get(0).getGenes();

			System.out.println(solucaoAtual);

			//if (solucaoAnterior.equals(solucaoAtual) && ambiente.runSolucao(e0)) {
			//	solucaoRepetiu++;
			//	System.out.println("Já resolvi! " + solucaoRepetiu);

			//	if (solucaoRepetiu > 50000) {
			//		break;
			//	}
			//}
		}

		return solucaoAtual;
	}

}