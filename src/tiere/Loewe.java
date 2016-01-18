package tiere;

import ErnhoferKopecFockKoelblReilaender.Client;
import gui.GrafischeOberflaeche;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by mreilaender on 17.01.2016.
 */
public class Loewe extends Thread {
    private boolean lebendig;
    private String name;
    private Client pc;
    private GrafischeOberflaeche gui;
    private int gains;

    public Loewe(String name) {
        setLebendig(true);
        this.name = name;
        pc = new Client("localhost", 1979);
        pc.connect();
        gui = new GrafischeOberflaeche();
        initGui();
        this.start();
    }

    @Override
    public void run() {
        while (lebendig) {
            gains();
            pc.write(("Loewe").getBytes());
            aktualisieren();
        }
    }

    public void initGui() {
        gui.frame.setSize(500, 300);
        gui.frame.setVisible(true);
        gui.wert1.setText(name);
        gui.ueberschrift.setText("Löwe");
        gui.beschriftung2.setText("Anzahl Gains");
        gui.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                pc.close();
                gui.frame.dispose();
                stop();
            }
        });
    }

    public void aktualisieren() {
        gui.wert2.setText("" + this.getGains());
    }

    public void gains() {
        int zeit = (int) ((Math.random() + 1) * 7);
        System.out.println("Der kleine Löwe " + name + " spielt den ganzen Tag, er hat schon "+ this.getGains() +" Gains gemacht");
        System.out.println("--------------------------------------------");
        try {
            sleep(zeit * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " hat gerade Gains gemacht.");
        this.setGains(this.getGains()+1);
        System.out.println("---------------------------------");
        if (this.getGains() == 3) {
            setLebendig(false);
            System.out.println("Löwe " + name + " hat zuviele gains gemacht und ist daran gestorben.");
            gui.frame.getContentPane().setBackground(Color.RED);
            gui.ueberschrift.setText("Löwe " + name + " ist tot. GAINS OVER");
            System.out.println("---------------------------------");
        }

    }

    public boolean isLebendig() {
        return lebendig;
    }

    public void setLebendig(boolean lebendig) {
        this.lebendig = lebendig;
    }

    public void sterben() {
        this.lebendig = false;
    }

    public int getGains() {
        return gains;
    }

    public void setGains(int gains) {
        this.gains = gains;
    }
}
