package comumUtil;

import java.util.ArrayList;

/**
 * Classe que representa a lista de salas.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class ListaSalas {
	private ArrayList<Sala> salas;

	public ListaSalas() {
		salas = new ArrayList<Sala>();
	}

	/**
	 * Adiciona uma nova sala à lista
	 * 
	 * @param sala
	 */
	public void adicionar(Sala sala) {
		this.salas.add(sala);
	}

	/**
	 * Verifica se a sala já está na lista
	 * 
	 * @param sala
	 * @return
	 */
	public boolean containsSala(Sala sala) {
		for (Sala cur : salas) {
			if ((cur.getLinha() == sala.getLinha()) && (cur.getColuna() == sala.getColuna())) {
				return true;
			}
		}
		return false;
	}

}
