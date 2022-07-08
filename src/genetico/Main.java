package genetico;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static Mapa ambiente = new Mapa(5);

	public static void main(String[] args) {

		try {
			int rangeNumGenes = 100;
			int tamPop = 3;
			int tamGer = 50000;

			ambiente.runAag(new Genetico().run(tamPop, tamGer, rangeNumGenes));
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
