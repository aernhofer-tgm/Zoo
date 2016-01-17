package gehege;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ErnhoferKopecFockKoelblReilaender.Client;
import gui.GrafischeOberflaeche;

/**
 * Created by hagen on 17.01.2016.
 */
public class Affengehege extends Thread {

    private int bananen;
    private boolean sauber, hungrig;
    private Client pgc;
    private GrafischeOberflaeche gui;

    public Affengehege(){
        setBananen(10);
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
            String test = "affengehege;" + String.valueOf(bananen);
            pgc.write(test.getBytes());
            ki();
            essen();
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            aktualisieren();
        }
    }

    public void aktualisieren() {
        gui.wert1.setText(String.valueOf(bananen));
    }

    public void initGui(){
        gui.ueberschrift.setText("Affengehege");
        gui.beschriftung1.setText("Bananen:");
        gui.wert1.setText(String.valueOf(bananen));
        gui.beschriftung2.setText("");
        gui.wert2.setText("");
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

    public void essen() {
        setBananen(getBananen()-1);
        System.out.println("Bananen Anzahl: " + getBananen());
        System.out.println("---------------------------------");
    }


    public void setBananen(int bananen) {
        this.bananen = bananen;
    }

    public int getBananen() {return this.bananen;}

    public boolean isSauber() {
        return sauber;
    }

    public void setSauber(boolean sauberkeit) {
        this.sauber = sauberkeit;
    }

    public Client getPgc() {
        return pgc;
    }

    public void ki(){
        if (getBananen() == 0) setBananen(10);
        System.out.println("Affen wurden gefüttert");
        System.out.println("---------------------------------");
    }
}
