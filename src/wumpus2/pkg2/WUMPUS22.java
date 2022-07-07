package wumpus2.pkg2;

import java.util.logging.Level;
import java.util.logging.Logger;

import util.AG;
import util.Ambiente;
import util.Util;

public class WUMPUS22 {

	public static Ambiente ambiente = new Ambiente(5);

	public static void main(String[] args) {

		try {
			System.out.println(Util.formataSaidaDaMatriz2(ambiente.mapa));
			ambiente.runAag(new AG().run());
		} catch (Exception ex) {
			Logger.getLogger(WUMPUS22.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
