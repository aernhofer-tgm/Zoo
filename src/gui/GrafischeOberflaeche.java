package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;

public class GrafischeOberflaeche {
	public JLabel ueberschrift, beschriftung1, wert1, beschriftung2, wert2;
	public JPanel panel;
	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrafischeOberflaeche window = new GrafischeOberflaeche();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GrafischeOberflaeche() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 220, 100);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		ueberschrift = new JLabel("Pinguin");
		ueberschrift.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(ueberschrift, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		beschriftung1 = new JLabel("Name:");
		beschriftung1.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		panel.add(beschriftung1);
		
		wert1 = new JLabel("David69");
		panel.add(wert1);
		
		beschriftung2 = new JLabel("Anzahl der Eier:");
		beschriftung2.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
		panel.add(beschriftung2);
		
		wert2 = new JLabel("0");
		panel.add(wert2);
	}

}
