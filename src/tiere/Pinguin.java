/**
 * 
 */
package tiere;

import ErnhoferKopecFockKoelblReilaender.Client;
import gui.GrafischeOberflaeche;

/**
 * @author andie
 *
 */
public class Pinguin extends Thread{

	private boolean lebendig;
	private int eier;
	private String name;
	private Client pc;
	private GrafischeOberflaeche pinguin;

	public Pinguin(String name){
		this.eier = 0;
		setLebendig(true);
		this.name = name;
		pc = new Client("localhost",1979);
		pc.connect();
		pinguin = new GrafischeOberflaeche();
		pinguin.frame.setVisible(true);
		this.start();
	}

	@Override
	public void run(){
		while(lebendig){
			eilegen();
			pc.write(("pinguin").getBytes());
			ki();
			aktualisieren();
		}
	}
	
	public void aktualisieren(){
		pinguin.wert1.setText(name);
		pinguin.wert2.setText(""+eier);
		
	}

	public void eilegen(){
		int zeit = (int) ((Math.random()+1)*3);
		System.out.println("Dauer bis "+ name+ " ein Ei legt: " + zeit+"s");
		System.out.println("---------------------------------");
		try {
			sleep(zeit*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " hat gerade ein Ei gelegt.");
		setEier(eier+1);
		System.out.println("---------------------------------");

		//Pinguin p = new Pinguin("Pinguin");

	}

	public boolean isLebendig() {
		return lebendig;
	}

	public void setLebendig(boolean lebendig) {
		this.lebendig = lebendig;
	}

	public void teoten(){
		this.lebendig = false;
	}

	public int getEier() {
		return eier;
	}

	public void setEier(int eier) {
		this.eier = eier;
		System.out.println("Eier: " + eier);
	}

	public void ki(){
		String befehl = ""+pc.read();
		if(befehl.equals("eierabholen")){
			eier=0;
			System.out.println("Soeben wurden die Eier von "+name+" abgeholt.");
		}
	}
}
