package socket_server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class close {
    void close(Socket socket, ServerSocket server) throws IOException, ClassNotFoundException{
        socket.close();
        socket = server.accept();
        String req = (String) new ObjectInputStream(socket.getInputStream()).readObject();
        Runtime rt = Runtime.getRuntime();
        rt.exec("taskkill /F /PID " + req);
    }
}
