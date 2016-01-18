package gehege;

import ErnhoferKopecFockKoelblReilaender.Client;
import gui.GrafischeOberflaeche;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author manue
 * @version 18.01.2016
 */
public class Loewengehege extends Thread {

    private String name;
    private int protein;
    private Client pgc;
    private GrafischeOberflaeche gui;

    public Loewengehege() {
        this.setProtein(10);
        pgc = new Client("localhost",1979);
        pgc.connect();
        this.gui = new GrafischeOberflaeche();
        this.initGui();
        this.start();
    }

    public void makeGains() {
        this.setProtein(this.getProtein()-1);
    }
    public void initGui(){
        gui.ueberschrift.setText("Löwengehege");
        gui.beschriftung1.setText("Protein:");
        gui.wert1.setText(String.valueOf(this.protein));
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
    public void setProtein(int protein) {
        this.protein = protein;
    }
    public int getProtein() {
        return protein;
    }
    public void ki(){
        if (this.getProtein() == 0) this.setProtein(10);
        System.out.println("Löwen haben ihre täglichen Gains gemacht");
        System.out.println("----------------------------------------");
    }
    @Override
    public void run(){
        while(true){
            String test = "Löwengehege;" + String.valueOf(this.getProtein());
            pgc.write(test.getBytes());
            this.ki();
            this.makeGains();
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.aktualisieren();
        }
    }
    public void aktualisieren() {
        gui.wert1.setText(String.valueOf(this.getProtein()));
    }
}
