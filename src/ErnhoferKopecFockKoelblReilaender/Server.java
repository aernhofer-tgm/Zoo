package ErnhoferKopecFockKoelblReilaender;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static final int PORT = 1979;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new threa for a client
            new Werter(socket).start();
        }
    }
}