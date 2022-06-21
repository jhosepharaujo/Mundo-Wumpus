
public enum MapaObjetos {

	JOGADOR(1), BURACO(2), WUMPUS(3), OURO(4);

	private final int valor;

	MapaObjetos(int valorOpcao){
		valor = valorOpcao;
		}

	public int getValor() {
		return valor;
	}

}
