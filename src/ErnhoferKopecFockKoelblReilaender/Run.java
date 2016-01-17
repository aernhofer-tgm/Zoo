/**
 * 
 */
package ErnhoferKopecFockKoelblReilaender;

import gehege.Affengehege;
import gehege.Pinguingehege;
import tiere.Affe;
import tiere.Pinguin;

/**
 * @author andie
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Pinguingehege();
		new Pinguin("David69");
		new Pinguin("Stovski");

        new Affengehege();
        new Affe("Jeff");
	}
}
