
public enum MapaObjetos {

	JOGADOR(1), OURO(2), BURACO(3), WUMPUS(4);

	private final int valor;

	MapaObjetos(int valorOpcao){
		valor = valorOpcao;
		}

	public int getValor() {
		return valor;
	}

}
