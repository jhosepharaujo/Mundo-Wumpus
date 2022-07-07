package genetico;

import java.util.logging.Level;
import java.util.logging.Logger;

import genetico.util.Ambiente;
import genetico.util.Util;
import genetico.util.AG;

public class Main {

	public static Ambiente ambiente = new Ambiente(5);

	public static void main(String[] args) {

		try {
			System.out.println(Util.formataSaidaDaMatriz2(ambiente.mapa));
			ambiente.runAag(new AG().run());
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
