
public class Main {

	public static void main(String[] args) {
		Mapa mapa = new Mapa(5);
		GUI gui = new GUI();
//		int valor = mapa.getMap()[0][0];
//		int outro_valor = mapa.getMap()[0][2];
//		
//		if(valor == outro_valor) {
//			System.out.println("Mesmo valor");
//		}
//
//		mapa.getMap()[0][2] = 3;
//		
//		outro_valor = mapa.getMap()[0][2];
		
//		if(valor == outro_valor) {
//			System.out.println("Mesmo valor");
//		} else {
//			System.out.println("Valor diferente");
//		}
		
		gui.showMap(mapa);
//		Player player;
//		Arquivos arqControl;
//		String position;
//		String[] ps;
//		
//		//construo as classes
//		player = new Player();
//		arqControl = new Arquivos();
//		
//		//leio arquivo
//		arqControl.readArq();
//		
//		//crio mapa
//		mapa = new Mapa(arqControl.mapa());
//		mapa.tamMapa(arqControl.quantLinha(), arqControl.quantColuna());
//		
//		//passo mapa para o player [Ele nao terá acesso ao mapa todo, somente para verificar sensores]
//		player.setMap(mapa);
//		
//		//recupero a posiçãoo inicial do player
//		position = mapa.getInitialPosition();
//		ps = position.split(" ");
//		
//		//inicializo o player
//		player.initialize(Integer.parseInt(ps[0]), Integer.parseInt(ps[1]));
//		
//		//encontro caminho
//		player.findPath();
		
	  }
	}

