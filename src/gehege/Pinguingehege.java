/**
 * 
 */
package gehege;

import ErnhoferKopecFockKoelblReilaender.PinguingehegeClient;

/**
 * @author andie
 *
 */
public class Pinguingehege extends Thread{

	private double wassertemp;
	private boolean sauber, heizung;
	private PinguingehegeClient pgc;


	public Pinguingehege(){
		setWassertemp(5);
		setSauber(true);
		pgc = new PinguingehegeClient("localhost",1979);
		pgc.connect();
		this.start();
	}

	@Override
	public void run(){
		while(true){
			String test = "pinguingehege;3";
			pgc.write(test.getBytes());
			ki();
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

	public PinguingehegeClient getPgc() {
		return pgc;
	}

	public void ki(){
		String befehl = pgc.read();
		String[] werte = befehl.split(";");
		switch(werte[0]){
		case "heizung":
			if(werte[1].equals("an")){
				setHeizung(true);
			}else if(werte[1].equals("aus")){
				setHeizung(false);
			}
		}
	}
}
