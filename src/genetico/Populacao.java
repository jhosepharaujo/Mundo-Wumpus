package genetico;

import java.util.ArrayList;
import java.util.List;

public class Populacao {

    private List<Individuo> individuos;
    private int tamPopulacao;

    //cria uma população com indivíduos aleatória
    public Populacao(int rangeNumGenes, int tamPop) {
        this.tamPopulacao = tamPop;
        this.individuos = new ArrayList<>();
        for (int i = 0; i <= tamPop; i++) {
            this.individuos.add(i, new Individuo(rangeNumGenes));
        }
    }

    public List<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuos(List<Individuo> individuos) {
        this.individuos = individuos;
    }

    public void addIndividuos(Individuo individuo) {
        this.individuos.add(individuo);
        this.tamPopulacao = this.individuos.size();

    }

    public void removeUtlimoIndividuo(int index) {
        this.individuos.remove(index);
        this.tamPopulacao = this.individuos.size();

    }


    public int getTamPopulacao() {
        return tamPopulacao;
    }

    @Override
    public String toString() {
        return "\nPopulacao{" + "individuos=" + individuos + ", tamPopulacao=" + tamPopulacao + '}';
    }

}
