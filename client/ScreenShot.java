package client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ScreenShot {
    Image setSizeImage(BufferedImage image) throws IOException {
        ImageIcon imageIcon = new ImageIcon(image);
        Image img = imageIcon.getImage();
        int newWidth = img.getWidth(null) / 2;
        int newHight = img.getHeight(null) / 2;
        Image newimg = image.getScaledInstance(newWidth, newHight, java.awt.Image.SCALE_SMOOTH);
        return newimg;
    }
    BufferedImage image;
    void screenShot() throws Exception {
        connectClient a = new connectClient();
        Socket socket = a.connect();
        image = ImageIO.read(socket.getInputStream());
        
        Image newimg = setSizeImage(image);
        
        JLabel label = new JLabel(new ImageIcon(newimg));
        JPanel panel = new JPanel();
        JButton saveImg = new JButton("lưu ảnh");
        JButton takeShot = new JButton("Chụp");
        
        saveImg.setPreferredSize(new Dimension(newimg.getWidth(null) / 2, 100));
        takeShot.setPreferredSize(new Dimension(newimg.getWidth(null) / 2, 100));
        
        GridBagConstraints gbc = new GridBagConstraints();
        JFrame f = new JFrame("ScreenShot");
        
        panel.setLayout(new GridBagLayout());
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(saveImg, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(takeShot, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panel.add(label, gbc);
        
        
        takeShot.addActionListener(new ActionListener() {
            public BufferedImage image1 = null;
            public void actionPerformed(ActionEvent e) {
                requestServer r = new requestServer();
                r.request("screenShot");
              
                try {
                    image1 = ImageIO.read(a.connect().getInputStream());
                    Image imgT = setSizeImage(image1);
                    ImageIcon icon = new ImageIcon(imgT);
                    label.setIcon(icon);
                    image = image1;
                } catch (IOException ex) {
                    Logger.getLogger(ScreenShot.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        saveImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveImg saveimg = new saveImg();
                saveimg.save(image);
            }
        });
        
        f.getContentPane().add(panel);
        f.pack();
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
