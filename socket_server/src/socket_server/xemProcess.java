package socket_server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

class xemProcess {
    void xem(Socket socket, ServerSocket server) throws IOException {
        socket.close();
        socket = server.accept();

        Vector<String> datas = new Vector();

        try {
            String line;
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = input.readLine()) != null) {
                datas.addElement(line); // <-- Parse data here.
            }
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            oos.writeObject(datas);
            oos.flush();
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}