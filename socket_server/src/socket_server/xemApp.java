/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket_server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class xemApp {

    void run(Socket socket, ServerSocket server) throws IOException, InterruptedException {
       socket.close();
        socket = server.accept();
        Vector<String> datas = new Vector();
        Process process = new ProcessBuilder("powershell",
                "\"gps| ? {$_.mainwindowtitle.length -ne 0} | Format-Table -HideTableHeaders  name, ID")
                .start();
        new Thread(() -> {
            Scanner sc = new Scanner(process.getInputStream());
            if (sc.hasNextLine())
                sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                datas.add(line);
            }
        }).start();

        process.waitFor();
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject(datas);
        oos.flush();
    }
}