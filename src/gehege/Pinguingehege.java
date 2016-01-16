/**
 * 
 */
package gehege;

/**
 * @author andie
 *
 */
public class Pinguingehege extends Thread{

	private double wassertemp;
	private boolean sauber, heizung;
	

	public Pinguingehege(){
		setWassertemp(5);
		setSauber(true);
		this.start();
	}

	@Override
	public void run(){
		while(true){
			wasser();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void wasser(){
		if(heizung){
			setWassertemp((wassertemp*10+1)/10);
		}else{
			setWassertemp((wassertemp*10-2)/10);
		}
		System.out.println("Wassertemperatur: "+wassertemp);
		System.out.println("---------------------------------");
	}

	public double getWassertemp() {
		return wassertemp;
	}

	public void setWassertemp(double wassertemp) {
		this.wassertemp = wassertemp;
	}

	public boolean isSauber() {
		return sauber;
	}

	public void setSauber(boolean sauberkeit) {
		this.sauber = sauberkeit;
	}

	public boolean isHeizung() {
		return heizung;
	}

	public void setHeizung(boolean heizung) {
		this.heizung = heizung;
	}
}
