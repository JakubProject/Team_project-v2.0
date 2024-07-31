import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ColorPixelArtGenerator {
    public static BufferedImage convertToPixelArt(BufferedImage original, int pixelSize) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage pixelatedImage = new BufferedImage(width, height, original.getType());
        Graphics2D g = pixelatedImage.createGraphics();
        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {
                int rgb = original.getRGB(x, y);
                g.setColor(new java.awt.Color(rgb));
                g.fillRect(x, y, pixelSize, pixelSize);
            }
        }
        g.dispose();
        return pixelatedImage;
    }
}
