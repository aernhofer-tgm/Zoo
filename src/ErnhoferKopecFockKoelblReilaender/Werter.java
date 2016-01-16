package ErnhoferKopecFockKoelblReilaender;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Werter extends Thread {
	protected Socket socket;
	private DataInputStream in;
	private DataOutputStream out = null;

	public Werter(Socket clientSocket) {
		this.socket = clientSocket;
	}

	public void run() {
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			return;
		}
		art(read());
		//write("Es geht".getBytes());
	}

	public String read() {
		try {
			int length = in.readInt();
			byte[] message = new byte[length];
			in.readFully(message, 0, message.length);
			//System.out.println("Server: "+new String(message));
			//decryptSymKeyCallback(message);
			return new String(message);
		} catch (Exception e) {
			e.printStackTrace();
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
		case("pinguingehege"):
			pinguingehege(werte);
			break;
		}
	}
	
	public void pinguingehege(String[] werte){
		if(Integer.parseInt(werte[1])<4){
			write("heizung;an".getBytes());
		}else if(Integer.parseInt(werte[1])>15){
			write("heizung;aus".getBytes());
		}
	}
}