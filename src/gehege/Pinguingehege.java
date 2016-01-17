/**
 * 
 */
package gehege;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ErnhoferKopecFockKoelblReilaender.Client;
import gui.GrafischeOberflaeche;

/**
 * @author andie
 *
 */
public class Pinguingehege extends Thread{

	private double wassertemp;
	private boolean sauber, heizung;
	private Client pgc;
	private GrafischeOberflaeche gui;


	public Pinguingehege(){
		setWassertemp(10);
		setSauber(true);
		pgc = new Client("localhost",1979);
		pgc.connect();
		gui = new GrafischeOberflaeche();
		initGui();
		this.start();
	}

	@Override
	public void run(){
		while(true){
			String test = "pinguingehege;" + String.valueOf(wassertemp);
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
		gui.ueberschrift.setText("Pinguingehege");
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
		gui.frame.setVisible(true);
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
