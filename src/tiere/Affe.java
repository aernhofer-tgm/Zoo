package tiere;

import ErnhoferKopecFockKoelblReilaender.Client;
import gehege.Affengehege;
import gui.GrafischeOberflaeche;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by hagen on 17.01.2016.
 */
public class Affe extends Thread {
    private boolean lebendig;
    private String name;
    private Client pc;
    private GrafischeOberflaeche gui;
    private int purzelbäume;

    public Affe(String name) {
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
            spielen();
            pc.write(("affe").getBytes());
            aktualisieren();
        }
    }

    public void initGui() {
        gui.frame.setSize(500, 300);
        gui.frame.setVisible(true);
        gui.wert1.setText(name);
        gui.ueberschrift.setText("Affe");
        gui.beschriftung2.setText("Anzahl Purzelbäume");
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
        gui.wert2.setText("" + getPurzelbäume());
    }

    public void spielen() {
        int zeit = (int) ((Math.random() + 1) * 7);
        System.out.println("Der kleine Affe " + name + " spielt den ganzen Tag, er hat schon "+ getPurzelbäume() +" Purzelbäume gemacht");
        System.out.println("---------------------------------");
        try {
            sleep(zeit * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " hat gerade einen Purzelbaum gemacht.");
        setPurzelbäume(getPurzelbäume()+1);
        System.out.println("---------------------------------");
        if (getPurzelbäume() == 3) {
            setLebendig(false);
            System.out.println("Oje jetzt hat sich " + name + " unabsichtlich in den tot gepurzelt");
            gui.frame.getContentPane().setBackground(Color.RED);
            gui.ueberschrift.setText("Affe ist tot. OJE");
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

    public void setPurzelbäume(int purzelbäume) {
        this.purzelbäume = purzelbäume;
    }

    public int getPurzelbäume(){return this.purzelbäume;}

}
