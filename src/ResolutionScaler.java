import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ResolutionScaler {
    private BufferedImage image;
    private int newWidth;
    private int newHeight;

    public ResolutionScaler(BufferedImage image, int newWidth, int newHeight) {
        this.image = image;
        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    public BufferedImage scaleResolution() {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resizedImage;
    }
}
