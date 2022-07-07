package util;

import java.util.ArrayList;

/**
 * Classe que representa a lista de salas 
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class Lista {
	private ArrayList<Sala> salas;
	
	Lista(){
		salas = new ArrayList<Sala>();
	}

	public int size(){
		return salas.size();
	}
	
	//adiciona uma sala
	public void adicionar(Sala sala){
		this.salas.add(sala);
	}

	//junta as duas lista prevalecendo a nova
	public void mergeLists(Lista nova){
		ArrayList<Sala> novos = nova.getArrayListSalas();
		for(Sala cur: novos){
			addOrSubstitute(cur);
		}
	}
	
	//adiciona uma nova sala, se ja tiver substitui a antiga
	public void addOrSubstitute(Sala sala){
		for(Sala cur: salas){
			//se for a mesma sala, substitui
			if(cur.getId().equals(sala.getId())){
				salas.remove(cur);
				salas.add(sala);
				return;
			}
		}
	}
	
	//remove peça da lista
	public void remove(String pos){
		Sala aux = getSala(pos);
		salas.remove(aux);
	}

	public void remove(Sala sala){
		salas.remove(sala);
	}
	
	public void remove(int x, int y){
		remove(x+""+y);
	}
	
	// ======= FUNCOES DE RETORNO =========
	
	//recupera uma sala
	public Sala get(int x, int y){
		return getSala((x+1)+""+(y+1)); //monta string e chama o metodo
	}
	
	//retorna a sala de acordo como id
	public Sala getSala(String id){
		for(Sala cur: salas){
			if(cur.getId().equals(id)){
				return cur;
			}
		}
		return null;
	}
	
	//retorna a peca que estiver na pos
	public boolean containsSala(String pos){
		for(Sala cur: salas){
			if(cur.getId().equals(pos)){
				return true;
			}
		}
		return false;
	}
	
	//retorna a peca que estiver na pos
		public boolean containsSala(Sala sala){
			for(Sala cur: salas){
				if((cur.getLinha() == sala.getLinha()) && (cur.getColuna() == sala.getColuna())){
					return true;
				}
			}
			return false;
		}
	
	//retorna arrayLista das salas
	public ArrayList<Sala> getArrayListSalas(){
		return salas;
	}
	

	
	public void printSalas()
	{
		for(Sala cur: salas){
			System.out.println(cur.getId());
		}
	}
}
