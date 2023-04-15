package socket_server;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;

public class screenShot {
    void screen(Socket socket, ServerSocket server) throws IOException, AWTException {
        socket.close();
        socket = server.accept();
        BufferedImage screencapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(screencapture, "png", socket.getOutputStream());
    }
}
