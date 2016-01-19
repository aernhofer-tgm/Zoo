package ErnhoferKopecFockKoelblReilaender;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Werter extends Thread {
	protected Socket socket;
	private DataInputStream in;
	private DataOutputStream out = null;
	private boolean heizung;
	private float time = 0;

	public Werter(Socket clientSocket) {
		this.socket = clientSocket;
	}

	public void run() {
		while(true){
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				return;
			}
			try{
				art(read());
			}catch(Exception e){
				stop();
			}
		}
	}

	public String read() {
		try {
			int length = in.readInt();
			byte[] message = new byte[length];
			in.readFully(message, 0, message.length);
			return new String(message);
		} catch (Exception e) {
			stop();
			return null;
		}
	}

	public void write(byte[] bytes) {
		try {
			out.writeInt(bytes.length);
			out.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void art(String text){
		String[] werte = text.split(";");
		switch(werte[0]){
			case("delfingehege"):
				delfingehege(werte);
				break;
			case("pinguingehege"):
				pinguingehege(werte);
				break;
			case("pinguin"):
				if(time==0){
					time = System.currentTimeMillis();
				}else if(System.currentTimeMillis() > time + 20000){
					time = 0;
					write("eierabholen".getBytes());
					System.out.println(System.currentTimeMillis() + ": Pinguineier werden abgeholt!");
					return;
				}
				write("".getBytes());
			break;
		}
	}

	public void pinguingehege(String[] werte){
		if(Double.parseDouble(werte[1])<=6){
			heizung = true;
			System.out.println(System.currentTimeMillis() + ": Pinguingehege Wasserheizung wurde aktiviert!");
		}else if(Double.parseDouble(werte[1])>=10){
			heizung = false;
			System.out.println(System.currentTimeMillis() + ": Pinguingehege Wasserheizung wurde deaktiviert!");
		}
		if(heizung){
			write("heizung;an".getBytes());
		}else{
			write("heizung;aus".getBytes());
		}
	}

	public void delfingehege(String[] werte){
		if(Double.parseDouble(werte[1])<=10){
			heizung = true;
		}else if(Double.parseDouble(werte[1])>=20){
			heizung = false;
		}
		if(heizung){
			write("heizung;an".getBytes());
		}else{
			write("heizung;aus".getBytes());
		}
	}
}