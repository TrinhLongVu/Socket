package socket_server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

class startApp {
    void start(Socket socket, ServerSocket server) throws IOException, ClassNotFoundException {
        socket.close();
        socket = server.accept();
        String req = (String) new ObjectInputStream(socket.getInputStream()).readObject();
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(req + ".exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}