package ImageFilters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SepiaFilter {
    public static BufferedImage apply(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage sepiaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));

                int red = (int) (color.getRed() * 0.393 + color.getGreen() * 0.769 + color.getBlue() * 0.189);
                int green = (int) (color.getRed() * 0.349 + color.getGreen() * 0.686 + color.getBlue() * 0.168);
                int blue = (int) (color.getRed() * 0.272 + color.getGreen() * 0.534 + color.getBlue() * 0.131);

                Color newColor = new Color(clamp(red), clamp(green), clamp(blue));
                sepiaImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return sepiaImage;
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
