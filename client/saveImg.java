package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

class saveImg {

    void save(BufferedImage image) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(
                new FileNameExtensionFilter("*.png", "png"));
        if (fileChooser.showSaveDialog(
                null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ImageIO.write(image, "png", new File(file.getAbsolutePath()));
            } catch (IOException ex) {
                System.out.println("Failed to save image!");
            }
        } else {
            System.out.println("No file choosen!");
        }
    }
}
