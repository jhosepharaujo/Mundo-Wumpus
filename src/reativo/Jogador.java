package reativo;
import java.util.ArrayList;

import comumUtil.MapaObjetos;

/**
 * Classe que representa o jogador.
 * 
 * @author Filipe Barros
 * @author Antônio Jhoseph
 *
 */
public class Jogador {
	protected ListaSalas fronteiras;
	protected ListaSalas visitados;
	protected ListaSalas estimados;
	
	protected Sala currentSala;
	protected GUI gui;
	
	protected Mapa mapa;
	
	protected boolean discoveredGold;
	protected boolean saindoLabirinto;
	protected boolean saiuLabirinto;
	private boolean morreu;
	
	protected int totalPeso;
	
	protected String caminho;
	
	protected int PESO_BURACO;
	protected int PESO_CHEIRO;
	protected int PESO_MOVIMENTO;
	protected int PESO_VISITADO;
	protected int PESO_ENCONTRAR_OURO;

	public Jogador(){
		fronteiras = new ListaSalas();
		visitados = new ListaSalas();
		estimados = new ListaSalas();
		gui = new GUI();
		discoveredGold = false;
		saindoLabirinto = false;
		saiuLabirinto = false;
		morreu = false;
		totalPeso = 0; 
		caminho = "";
		//seto pesos
		PESO_BURACO = 10;
		PESO_CHEIRO = 20;
		PESO_MOVIMENTO = 0;
		PESO_VISITADO = 2;
		PESO_ENCONTRAR_OURO = -100;
	}

	public void setMapa(Mapa mapa){
		this.mapa = mapa;
	}
	
	/**
	 * Método para setar as salas do estado de ínicio.
	 * Sala inicial (a mesma para a saída ao final).
	 * 
	 * @param linha
	 * @param coluna
	 */
	public void inicializar(int linha, int coluna){
		currentSala = new Sala(linha,coluna);
		currentSala.aquiEhSaida();
		currentSala.setAnterior("");
		visitados.adicionar(currentSala);
	}
	
	/**
	 * Método para ir gerando o caminho completo da movimentação do jogador até um estado final.
	 */
	public void acharCaminho(){
		while(!saiuLabirinto && !morreu){
			caminhar();
		}
		gui.imprimirMapa(mapa);
		if(!morreu)
			gui.jogadorSaiuDoLabirinto();
		gui.caminho(caminho);
	}
	
	// ========= CONTROLE =========
	
	/**
	 * Encontrou um buraco. Imprime mensagem.
	 */
	protected void encontrouBuraco(){
		totalPeso+= PESO_BURACO;
		morreu = true;
		gui.jogadorMorreu(currentSala.getLinha(), currentSala.getColuna(),"BURACO");
	}
	
	/**
	 * Encontrou um Wumpus. Imprime mensagem.
	 */
	protected void encontrouWumpus(){
		totalPeso += PESO_CHEIRO;
		morreu = true;
		gui.jogadorMorreu(currentSala.getLinha(), currentSala.getColuna(),"WUMPUS");
	}
	
	/**
	 * Indica que já saiu do labirinto.
	 * 
	 * @return
	 */
	public boolean saiuLabirinto(){
		return saiuLabirinto;
	}
	
	// ========= MOVIMENTACAO =======
	
	/**
	 * Método para fazer o jogador caminhar.
	 * 
	 * @return
	 */
	protected boolean caminhar(){
		Sala next = null;
		//guarda no caminho
		caminho += "["+currentSala.getId()+"] ";
		//atualizo peso total
		totalPeso += PESO_MOVIMENTO;
		//informa que a sala foi visitada
		currentSala.foiVisitado(PESO_VISITADO);
		if(discoveredGold){//se ja descobriu o ouro
			back();
			return true;
		}
		//verifica o que tem dentro da sala
		switch(mapa.recuperaObjetoNaSala(currentSala.getLinha(), currentSala.getColuna())){
			case 1://ouro
				currentSala.achouOuro();
				gui.jogadorPegouOuro(currentSala.getLinha(), currentSala.getColuna());
				totalPeso += PESO_ENCONTRAR_OURO;
				discoveredGold = true;
				saindoLabirinto = true;
				back();
				return true;
			case 2://buraco
				encontrouBuraco();
				return false;
			case 3://wumpus
				encontrouWumpus();
				return false;
			default://nada				
		}
		//recupero fronteiras
		if(!getFrontiers()){//n�o tem fronteiras n�o visitadas
			if(getVisitedFrontiers()){//verifica e recupera fronteira dos n�o visitados

				//recupero a melhor sala para se ir
				next = getBestRoom();
				
				//se nao estiver voltando
				if(!currentSala.getAnterior().equals(next.getId()))
					next.setAnterior(currentSala.getId()); //seto a sala anterior para a atual
				
				//atualizo a sala corrente para a prox
				currentSala = next;
				return true;		
			}
		}else{//tem fronteiras n�o visitadas
			
			//verifico os sensores e atualizo os estados das fronteiras
			switch(verificarSensores()){
				case 0: //vazio
					fronteiras.todosVazios();
				break;
				case 1: //vento
					fronteiras.todosPossiveisBuraco(PESO_BURACO);
				break;
				case 2: // cheiro
					fronteiras.todosPossiveisWumpus(PESO_CHEIRO);
				break;
				case 3: //cheiro e vento
					fronteiras.todosPossiveisBuracoEWumpus(PESO_BURACO, PESO_CHEIRO);
				break;
			}
			//recupero a melhor sala para se ir
			next = getBestRoom();
			//se nao estiver voltando
			if(!currentSala.getAnterior().equals(next.getId()))
				next.setAnterior(currentSala.getId()); //seto a sala anterior para a atual

			//removo da lista de fronteiras
			fronteiras.remove(next);
			//insiro as fronteiras nos estimados
			estimados.mergeListas(fronteiras);
			//adiciono nos visitados
			visitados.adicionar(next);
			currentSala = next;
			return true;
		}
		return false;
	}

	//volta
	protected void back(){
		//se estiver saindo do labirinto
		if(saindoLabirinto){
			if(currentSala.saida()){//se a sala atual for a saida
				saiuLabirinto = true;
				return;
			}
		}
		//volta
		currentSala = visitados.getSala(currentSala.getAnterior());		
	}
	
	//retorna a melhor sala para ir
	protected Sala getBestRoom(){
		//recupero a sala com o menor peso
		Sala best = fronteiras.getBestRoom();
		Sala ant = visitados.getSala(currentSala.getAnterior());
		if(ant != null){ //se existir um anterior
			//verifico se � melhor voltar uma casa que ir para melhor sala das fronteiras
			if(best.pesoTotal() > ant.pesoTotal()){
				best = ant;
			}
		}
		return best;
	}
	
	//verifica sensores [cheiro e vento = 3 / cheiro - 2 / vento - 1 / vazio - 0]
	protected int verificarSensores(){
		ArrayList<Sala> salas = fronteiras.getArrayListSalas();
		boolean fedor = false;
		boolean brisa = false;
		//percorro as salas e verifico os sensores
		for(Sala cur: salas){	
			if(this.percepcaoSensor(cur.getLinha(), cur.getColuna()) == 1)
				brisa = true;
			else if(this.percepcaoSensor(cur.getLinha(), cur.getColuna()) == 2)
				fedor = true;
		}
		//retorno o que os sensores detectaram
		if(fedor && brisa)
			return 3;
		else if(fedor)
			return 2;
		else if(brisa)
			return 1;
		
		return 0;	
	}
	
	// ===== VERIFICA SENSORES =====

	// 0 Nada / 1 Vento / 2 cheiro
	private int percepcaoSensor(int linha, int coluna) {
		if (mapa.getMapa()[linha][coluna] == MapaObjetos.BRISA.getValor())
			return 1;
		else if (mapa.getMapa()[linha][coluna] == MapaObjetos.FEDOR.getValor())
			return 2;

		return 0;
	}
	
	// =============== CONTROLE DE FRONTEIRAS =================
	
	// === FRONTEIRAS JA VISITADAS
	
	protected boolean getVisitedFrontiers(){
		fronteiras = new ListaSalas(); //crio a nova lista de fronteiras [garbage collector seu lindo!]
		boolean ct = false;
		// ==== PEGA FRONTEIRA DA SALA DE CIMA ====
		if(verifyAndAddVisitedFrontier(mapa.getFronteiraCima(currentSala))) //recupera fronteira de cima
				ct = true;
		// ==== PEGA FRONTEIRA DA SALA DE BAIXO ====
		if(verifyAndAddVisitedFrontier(mapa.getFronteiraBaixo(currentSala))) //recupera fronteira de cima
				ct = true;
		// ==== PEGA FRONTEIRA DA SALA DA ESQUERDA ====
		if(verifyAndAddVisitedFrontier(mapa.getFronteiraEsquerda(currentSala))) //recupera fronteira de cima
				ct = true;
		// ==== PEGA FRONTEIRA DA SALA DA ESQUERDA ====
		if(verifyAndAddVisitedFrontier(mapa.getFronteiraDireita(currentSala))) //recupera fronteira de cima
				ct = true;
		
		return ct;
	}

	//verifica e adiciona a fronteira
	protected boolean verifyAndAddVisitedFrontier(Sala front){
		if(front != null){
			if(visitados.containsSala(front.getId())){//se ja foi visitado adiciona
					fronteiras.adicionar(visitados.getSala(front.getId()));//adiciona na lista fronteiras
					return true;
			}
		}
		return false;		
	}

	
	// === FRONTEIRAS NAO VISITADAS
	
	//recupera fronteiras
	protected boolean getFrontiers(){
		fronteiras = new ListaSalas(); //crio a nova lista de fronteiras [garbage collector seu lindo!]
		boolean ct = false;
		// ==== PEGA FRONTEIRA DA SALA DE CIMA ====
		if(verifyAndAddFrontier(mapa.getFronteiraCima(currentSala))) //recupera fronteira de cima
				ct = true;
		// ==== PEGA FRONTEIRA DA SALA DE BAIXO ====
		if(verifyAndAddFrontier(mapa.getFronteiraBaixo(currentSala))) //recupera fronteira de cima
				ct = true;
		// ==== PEGA FRONTEIRA DA SALA DA ESQUERDA ====
		if(verifyAndAddFrontier(mapa.getFronteiraEsquerda(currentSala))) //recupera fronteira de cima
				ct = true;
		// ==== PEGA FRONTEIRA DA SALA DA ESQUERDA ====
		if(verifyAndAddFrontier(mapa.getFronteiraDireita(currentSala))) //recupera fronteira de cima
				ct = true;

		return ct;
	}
	
	//verifica e adiciona a fronteira
	protected boolean verifyAndAddFrontier(Sala front){
		if(front != null){
			if(!visitados.containsSala(front.getId())){//se ja foi visitado nao adiciona
				if(estimados.containsSala(front.getId())){//se ja foi estimado
					fronteiras.adicionar(estimados.getSala(front.getId()));//adiciona o da lista de estimados
					return true;
				}else{
					fronteiras.adicionar(front); //se nao adiciona esse novo
					return true;
				}
			}
		}
		return false;		
	}
}