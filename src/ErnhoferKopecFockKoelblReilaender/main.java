/**
 * 
 */
package ErnhoferKopecFockKoelblReilaender;

import gehege.Pinguingehege;
import tiere.*;

/**
 * @author andie
 *
 */
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server(12345);
		Werter w = new Werter();
		Pinguingehege ph = new Pinguingehege();
		
		PinguingehegeClient pgc = new PinguingehegeClient("localhost",12345);
		WerterClient wc = new WerterClient("localhost",12345);

		server.start();
		pgc.connect();
		//wc.connect();
		
		pgc.write("Hallo".getBytes());
		//wc.read();
		
		//wc.write("Gusch".getBytes());
		//pgc.read();
		
		Pinguin p1 = new Pinguin("David69");
		//p1.start();
		System.out.println("Der Panda ist TOT");
		System.out.println("---------------------------------");
	}

}
