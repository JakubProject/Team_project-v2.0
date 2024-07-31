package ImageFilters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BlueTintFilter {
    public static BufferedImage apply(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage blueTintImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue() + 50;

                Color newColor = new Color(clamp(red), clamp(green), clamp(blue));
                blueTintImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return blueTintImage;
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
