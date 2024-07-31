package ImageFilters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GrayscaleFilter {
    public static BufferedImage apply(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int gray = (int) (color.getRed() * 0.3 + color.getGreen() * 0.59 + color.getBlue() * 0.11);
                Color newColor = new Color(gray, gray, gray);
                grayscaleImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return grayscaleImage;
    }
}
