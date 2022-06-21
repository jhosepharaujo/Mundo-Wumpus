
public class Main {

	public static void main(String[] args) {
		Mapa mapa = new Mapa(4);
		GUI gui = new GUI();
		Player player = new Player();

//		//passo mapa para o player [Ele nao terá acesso ao mapa todo, somente para verificar sensores]
		player.setMap(mapa);
		player.initialize(mapa.getX_player(), mapa.getY_player());
//		
//		//encontro caminho
		player.findPath();
		
	  }
	}

