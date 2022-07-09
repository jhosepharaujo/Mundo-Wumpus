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

    public Populacao(){
        this.individuos = new ArrayList<>();
    }

    public List<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuos(List<Individuo> individuos) {
        this.individuos = individuos;
    }

    public void addIndividuos(Individuo individuo) {
        this.individuos.add(individuo);
    }

    public boolean populacaoComTodosIndividuosIguais()
    {
        int contator = 0;
        Individuo primeiro_individuo = this.individuos.get(0);
        for (Individuo individuo : this.individuos) {
            if(primeiro_individuo.equals(individuo))
            {
                contator++;
            }
        }
        return contator == this.individuos.size();
    }


    public int getTamPopulacao() {
        return tamPopulacao;
    }
    

    public void setTamPopulacao(int tamPopulacao) {
		this.tamPopulacao = tamPopulacao;
	}

	@Override
    public String toString() {
        return "\nPopulacao{" + "individuos=" + individuos + ", tamPopulacao=" + tamPopulacao + '}';
    }

}
