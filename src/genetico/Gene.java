package genetico;

/**
 * Classe que representa o gene de um indivíduo. Ele é dividido em três partes
 * para auxiliar o cruzamento de genes no momento da execução do algoritmo
 * genético.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class Gene {

	private String parte1;
	private String parte2;
	private String parte3;

	private String geneCompleto;

	public Gene(String gene) {
		this.geneCompleto = gene;
	}

	public Gene(String p1, String p2, String p3) {
		setParte1(p1);
		setParte2(p2);
		setParte3(p3);
		gerarGene();
	}

	public Gene() {
	}

	public void setParte1(String parte) {
		this.parte1 = parte;
	}

	public void setParte2(String parte) {
		this.parte2 = parte;
	}

	public void setParte3(String parte) {
		this.parte3 = parte;
	}

	public void gerarGene() {
		this.geneCompleto = "";
		this.geneCompleto = this.parte1 + this.parte2 + this.parte3;
	}

	public void setGene(String gene) {
		this.geneCompleto = gene;
	}

	public String getGene() {
		return geneCompleto;
	}

}
