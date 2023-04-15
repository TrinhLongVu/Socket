package client;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class requestServer {

    void request(String req) {
        connectClient a = new connectClient();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(a.connect().getOutputStream());
            oos.writeObject(req);
        } catch (IOException ex) {
            Logger.getLogger(FirstPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
