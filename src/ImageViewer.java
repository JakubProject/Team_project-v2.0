import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageViewer {
    private BufferedImage image;

    public ImageViewer(BufferedImage image) {
        this.image = image;
    }

    public void showImage() {
        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        JScrollPane scrollPane = new JScrollPane(label);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
}
