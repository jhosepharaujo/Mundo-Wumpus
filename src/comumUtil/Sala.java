package comumUtil;

/**
 * Classe para representar uma sala dentro do mapa.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class Sala {
	private int linha;
	private int coluna;

	public Sala(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
}
