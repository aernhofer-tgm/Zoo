/**
 * 
 */
package gehege;

import ErnhoferKopecFockKoelblReilaender.Client;
import gui.GrafischeOberflaeche;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Alexander Koelbl
 *
 */
public class Delfingehege extends Thread{

	private double wassertemp;
	private boolean sauber, heizung;
	private int fuetterZeit;
	private Client pgc;
	private GrafischeOberflaeche gui;


	public Delfingehege(){
		setWassertemp(15);
		setSauber(true);
		setFuetterZeit(8);
		pgc = new Client("localhost",1979);
		pgc.connect();
		gui = new GrafischeOberflaeche();
		initGui();
		this.start();
	}

	@Override
	public void run(){
		while(true){
			String test = "delfingehege;" + String.valueOf(wassertemp);
			pgc.write(test.getBytes());
			ki();
			wasser();
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			aktualisieren();
		}
	}
	
	public void aktualisieren(){
		fuetterung(getFuetterZeit());
		gui.wert1.setText(""+wassertemp);
		if(heizung)
		{
			gui.wert2.setText("an");
		}else
		{
			gui.wert2.setText("aus");
		}
	}
	
	public void initGui(){
		gui.ueberschrift.setText("Delfingehege");
		gui.beschriftung1.setText("Wassertemperatur:");
		gui.beschriftung2.setText("Heizung:");
		gui.frame.setBounds(100, 100, 300, 100);
		gui.panel.setBackground(Color.LIGHT_GRAY);
		gui.frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                pgc.close();
                gui.frame.dispose();
                stop();
            }
        } );
        gui.frame.setSize(500, 300);
		gui.frame.setVisible(true);
	}

	public void wasser(){
		if(heizung){
			setWassertemp((wassertemp*10+1)/10);
		}else{
			setWassertemp((wassertemp*10-2)/10);
		}
		System.out.println("Wassertemperatur: " + wassertemp);
		System.out.println("---------------------------------");
	}

	public void fuetterung(int fuetterZeit){
		if(fuetterZeit == 0){
			System.out.println("Delfine wurden gefuettert");
			System.out.println("---------------------------------");
			setFuetterZeit(8);
		}else{
			setFuetterZeit((fuetterZeit - 1));
		}
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

	public void setFuetterZeit(int fuetterZeit){
		this.fuetterZeit = fuetterZeit;
	}

	public int getFuetterZeit(){
		return fuetterZeit;
	}

	public Client getPgc() {
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
