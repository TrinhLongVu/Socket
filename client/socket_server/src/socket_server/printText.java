/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket_server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class printText {
    void run(Socket socket, ServerSocket server, String key) throws IOException{
        socket.close();
        socket = server.accept();
        String re = key;
        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(re);
                
    }
}
