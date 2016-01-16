/**
 * 
 */
package ErnhoferKopecFockKoelblReilaender;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class WerterClient {

    private Socket socket;

    private DataInputStream in;
    private DataOutputStream out;

    private String host = "";
    private int port = 0;

    public WerterClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            this.socket = new Socket(host, port);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] read() {
        try {
            int length = this.in.readInt();
            if (length > 0) {
                byte[] message = new byte[length];
                this.in.readFully(message, 0, message.length);
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void write(byte[] bytes) {
        try {
            out.writeInt(bytes.length);
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
