package util;

import static wumpus2.pkg2.WUMPUS22.ambiente;

import java.util.Collections;

public class AG {

    public static String caracteres = "NSLO";
    public static int iMax = 5;
    public static int xMax = 5;
    Populacao populacao = null;

    // Ambiente ambiente = new Ambiente();
    int rangeNumGenes = 100;
    int tamPop = 3;
    int tamGer = 50000;
    String solucaoAnterior = "";
    String solucaoAtual = "";
    int solucaoRepetiu = 0;

    public String run() {

       
        
        //população incial
        populacao = new Populacao(rangeNumGenes, tamPop, iMax, xMax);
        tamPop = populacao.getTamPopulacao();

        System.out.println("\nPopulação inicial: " + populacao);
        for (int i = 0; i <= tamGer; i++) {

            solucaoAnterior = solucaoAtual;
   
            Geracao g = new Geracao(populacao, rangeNumGenes, iMax, xMax);
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
            
            System.out.println(e0);;
            if (e0.equals(e1) || e0.equals(e2) || e0.equals(e3)) {

                //removo os tres individuos repetidos
                populacao.removeUtlimoIndividuo(populacao.getTamPopulacao() - 1);
                populacao.removeUtlimoIndividuo(populacao.getTamPopulacao() - 1);
                populacao.removeUtlimoIndividuo(populacao.getTamPopulacao() - 1);

                //add os novos
                populacao.addIndividuos(new Individuo(rangeNumGenes, iMax, xMax));
                populacao.addIndividuos(new Individuo(rangeNumGenes, iMax, xMax));
                populacao.addIndividuos(new Individuo(rangeNumGenes, iMax, xMax));

                int size = this.populacao.getIndividuos().size();

                Collections.sort(this.populacao.getIndividuos(), (Individuo o1, Individuo o2) -> {
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
            
            if(solucaoAnterior.equals(solucaoAtual) && ambiente.runSolucao(e0)){
               
                solucaoRepetiu++;
                 System.out.println("Já resolvi! " + solucaoRepetiu);
                 
                 if(solucaoRepetiu > 300){
                     break;
                    
                 }
            }

           // System.out.println(populacao);

        }

         return solucaoAtual;
    }

}
