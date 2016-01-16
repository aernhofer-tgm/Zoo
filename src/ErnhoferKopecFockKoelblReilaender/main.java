/**
 * 
 */
package ErnhoferKopecFockKoelblReilaender;

import java.io.IOException;

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
		//Server server = new Server(12345);
		//Werter w = new Werter();
		//Pinguingehege ph = new Pinguingehege();

		PinguingehegeClient pgc = new PinguingehegeClient("localhost",1979);
		//PinguingehegeClient pgc2 = new PinguingehegeClient("localhost",1979);
		//WerterClient wc = new WerterClient("localhost",12345);

		pgc.connect();
		//pgc2.connect();
		pgc.write("Pinguingehege;3".getBytes());
		pgc.read();
		//pgc2.write("Zwei Ja".getBytes());

		System.out.println(new String(pgc.read()));

		/*
		server.start();
		pgc.connect();
		wc.connect();

		pgc.write("Hallo".getBytes());
		wc.read();

		//wc.write("Gusch".getBytes());
		//pgc.read();
		 * 
		 */

		/*
		int port = Integer.parseInt("6006");
		   try
		   {
		      Thread t = new GreetingServer(port);
		      t.start();
		   }catch(IOException e)
		   {
		      e.printStackTrace();
		   }

		GreetingClient gc = new GreetingClient();
		//GreetingClient gc2 = new GreetingClient();
		gc.write("Hallo");
		//gc2.read();
		 */

		//Pinguin p1 = new Pinguin("David69");
		//p1.start();
		
		System.out.println("Der Panda ist TOT");
		System.out.println("---------------------------------");

		pgc.close();
		//pgc2.close();
		
	}

}
