/**
 * 
 */
package tiere;

import ErnhoferKopecFockKoelblReilaender.Client;
import gui.GrafischeOberflaeche;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Alexander Koelbl
 *
 */
public class Delfin extends Thread{

	private boolean lebendig;
	private int tricks = 0;
	private boolean alleTricks = false;
	private String name;
	private Client pc;
	private GrafischeOberflaeche gui;

	public Delfin(String name){
		setLebendig(true);
		this.name = name;
		pc = new Client("localhost",1979);
		pc.connect();
		gui = new GrafischeOberflaeche();
		initGui();
		this.start();
	}

	@Override
	public void run(){
		while(lebendig){
			tricksLernern();
			pc.write(("delfin").getBytes());
			aktualisieren();
		}
	}
	
	public void initGui(){
		gui.frame.setSize(500, 300);
		gui.frame.setVisible(true);
		gui.wert1.setText(name);
		gui.ueberschrift.setText("Delfin");
		gui.beschriftung2.setText("Anzahl Tricks");
		gui.frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                pc.close();
                gui.frame.dispose();
                stop();
            }
        } );
	}
	
	public void aktualisieren(){
		gui.wert2.setText(""+tricks);
	}

	public void tricksLernern(){
		int zeit = (int) ((Math.random()+1)* 10);
		System.out.println("Dauer bis "+ name+ " einen neuen Trick lernt: " + zeit+"s");
		System.out.println("---------------------------------");
		try {
			sleep(zeit*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(tricks < 3){
			tricks += 1;
		}else{
			alleTricks = true;
		}

		switch (tricks){
			case 1:
				System.out.println(name + " hat gelernt mit der Flosse zu winken");
				break;
			case 2:
				System.out.println(name + " hat gelernt einen Vorwaertssalto zu machen");
				break;
			case 3:
				if(alleTricks == false) {
					System.out.println(name + " hat gelernt einen Rueckwaertssalto zu machen");
				}else{
					System.out.println(name + " hat schon alle Tricks gelernt");
				}
		}
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

	public int getTricks() {
		return tricks;
	}

	public void setTricks(int tricks) {
		if(tricks > 4){
			this.tricks = 3;
			this.alleTricks = true;
		}else{
			this.tricks = tricks;
		}
		System.out.println("Tricks: " + tricks);
	}

	public boolean getAlleTricks(){
		return alleTricks;
	}

	public void setAlleTricks(boolean alleTricks){
		this.alleTricks = alleTricks;
	}
}
