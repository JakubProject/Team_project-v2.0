package ImageFilters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class InvertFilter {
    public static BufferedImage apply(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage invertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));

                int red = 255 - color.getRed();
                int green = 255 - color.getGreen();
                int blue = 255 - color.getBlue();

                Color newColor = new Color(red, green, blue);
                invertedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return invertedImage;
    }
}
