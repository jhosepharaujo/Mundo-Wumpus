package genetico;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static Mapa ambiente = new Mapa(5, 2);

	public static void main(String[] args) {

		try {
			int rangeNumGenes = 100;
			int tamanhoPopulacao = 3;
			int tamanhoGeracao = 50000;

			ambiente.runAag(new Genetico().run(tamanhoPopulacao, tamanhoGeracao, rangeNumGenes));
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
