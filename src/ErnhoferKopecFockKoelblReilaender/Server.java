/**
 * 
 */
package ErnhoferKopecFockKoelblReilaender;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(() -> {
            try {
                clientSocket = serverSocket.accept();
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void read() {
        try {
            int length = in.readInt();
            byte[] message = new byte[length];
            in.readFully(message, 0, message.length);
            System.out.println(new String(message));
            //decryptSymKeyCallback(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataInputStream getIn() {
        return in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    //public abstract void decryptSymKeyCallback(byte[] encryptedSymKey);
}
