package reativo;

import java.util.ArrayList;

/**
 * Classe que representa a lista de salas do modo reativo.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class ListaSalas extends comumUtil.ListaSalas {
	private ArrayList<Sala> salas;
	
	public ListaSalas() {
		salas = new ArrayList<Sala>();
	}

	// adiciona uma sala
	public void adicionar(Sala sala) {
		this.salas.add(sala);
	}

	// junta as duas lista prevalecendo a nova
	public void mergeListas(ListaSalas nova) {
		ArrayList<Sala> novos = nova.getArrayListSalas();
		for (Sala cur : novos) {
			addOuSubstituir(cur);
		}
	}

	// adiciona uma nova sala, se ja tiver substitui a antiga
	public void addOuSubstituir(Sala sala) {
		for (Sala cur : salas) {
			// se for a mesma sala, substitui
			if (cur.getId().equals(sala.getId())) {
				salas.remove(cur);
				salas.add(sala);
				return;
			}
		}
	}

	// remove peça da lista
	public void remove(String pos) {
		Sala aux = getSala(pos);
		salas.remove(aux);
	}

	public void remove(Sala sala) {
		salas.remove(sala);
	}

	public void remove(int x, int y) {
		remove(x + "" + y);
	}

	// ======= FUNCOES DE RETORNO =========

	// recupera uma sala
	public Sala get(int x, int y) {
		return getSala((x + 1) + "" + (y + 1)); // monta string e chama o metodo
	}

	// retorna a sala de acordo como id
	public Sala getSala(String id) {
		for (Sala cur : salas) {
			if (cur.getId().equals(id)) {
				return cur;
			}
		}
		return null;
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


	// retorna a peca que estiver na pos
	public boolean containsSala(String pos) {
		for (Sala cur : salas) {
			if (cur.getId().equals(pos)) {
				return true;
			}
		}
		return false;
	}

	// retorna arrayLista das salas
	public ArrayList<Sala> getArrayListSalas() {
		return salas;
	}

	// retorno a sala com menor peso
	public Sala getBestRoom() {
		Sala best = null;
		int melhor = 999999; // considere infinito =(
		for (Sala cur : salas) { // pego a sala com menor peso
			if (cur.pesoTotal() < melhor) {
				best = cur;
				melhor = cur.pesoTotal();
			}
		}
		return best;
	}

	// ===== CONTROLE DE PROBABILIDADES ====

	// todas as salas livres
	public void todosVazios() {
		for (Sala cur : salas) {
			cur.salaLivre();
		}
	}

	// todos Possiveis Buraco e Wumpus
	public void todosPossiveisBuracoEWumpus(int pesoBuraco, int pesoWumpus) {
		for (Sala cur : salas) {
			cur.possivelBuracoEWumpus(pesoBuraco, pesoWumpus);
		}

	}

	// todos Possiveis Buraco
	public void todosPossiveisBuraco(int pesoBuraco) {
		for (Sala cur : salas) {
			cur.possivelBuraco(pesoBuraco);
		}

	}

	// todos Possiveis Wumpus
	public void todosPossiveisWumpus(int pesoWumpus) {
		for (Sala cur : salas) {
			cur.possivelWumpus(pesoWumpus);
		}
	}

	public void printSalas() {
		for (Sala cur : salas) {
			System.out.println(cur.getId());
		}
	}
}