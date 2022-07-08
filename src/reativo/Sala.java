package reativo;

public class Sala extends comumUtil.Sala {
	private int pesoBuraco;
	private int pesoWumpus;
	private int pesoVisitado;
	private String anterior;
	private boolean achouOuro;
	private boolean ehSaida;
	private boolean clear;

	public Sala(int linha, int coluna) {
		super(linha, coluna);
		clear = false;
		pesoBuraco = 0;
		pesoWumpus = 0;
		pesoVisitado = 0;
	}

	public void possivelBuraco(int pesoBuraco) {
		if (clear)// se eu tiver certeza que a sala esta vazia n somo os pesos
			return;
		this.pesoBuraco += pesoBuraco;
	}

	public void possivelWumpus(int pesoWumpus) {
		if (clear)// se eu tiver certeza que a sala esta vazia n somo os pesos
			return;
		this.pesoWumpus += pesoWumpus;
	}

	public void possivelBuracoEWumpus(int pesoBuraco, int pesoWumpus) {
		if (clear)// se eu tiver certeza que a sala esta vazia n somo os pesos
			return;
		this.pesoBuraco += pesoBuraco;
		this.pesoWumpus += pesoWumpus;
	}

	public void foiVisitado(int pesoVisitado) {
		this.pesoVisitado += pesoVisitado;
	}

	public void salaLivre() {
		this.pesoWumpus = 0;
		this.pesoBuraco = 0;
		clear = true;
	}

	public int pesoTotal() {
		return pesoBuraco + pesoWumpus + pesoVisitado;
	}

	// seto sala anterior a ela
	public void setAnterior(String ant) {
		this.anterior = ant;
	}

	public String getAnterior() {
		return anterior;
	}

	// Achou ouro
	public void achouOuro() {
		achouOuro = true;
	}

	// responde se essa sala � a saida
	public boolean ouro() {
		return achouOuro;
	}

	// A saida do labirinto
	public void aquiEhSaida() {
		ehSaida = true;
	}

	// responde se essa sala � a saida
	public boolean saida() {
		return ehSaida;
	}

	/**
	 * método que retorna a posição da sala formatada.
	 * 
	 * @return
	 */
	public String getId() {
		return this.getLinha() + ":" + this.getColuna();
	}

}