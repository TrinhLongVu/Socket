package client;


import java.awt.Component;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class connectClient {

    public connectClient() {
    }

    public static boolean check = false;

    Socket connect(){

        String ip = new FirstPage().text;
        InetAddress ipServer = null;
        
        try {
            ipServer = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Lỗi kết nối đến server");
            Logger.getLogger(connectClient.class.getName()).log(Level.SEVERE, null, ex);             
            return null;
  
        }
        
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ipServer, 6666), 500);
            return socket;
        } catch (IOException ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Lỗi kết nối đến server");
            return null;
        }
        
    }
}
