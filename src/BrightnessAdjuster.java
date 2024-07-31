import java.awt.Color;
import java.awt.image.BufferedImage;

public class BrightnessAdjuster {

    /**
     * Zmienia jasność obrazu.
     *
     * @param image BufferedImage obraz do zmiany jasności
     * @param factor Współczynnik zmiany jasności (np. 1.2 oznacza 20% jaśniejsze)
     * @return BufferedImage obraz po zmianie jasności
     */
    public static BufferedImage adjustBrightness(BufferedImage image, float factor) {
        BufferedImage adjustedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color originalColor = new Color(image.getRGB(x, y));
                int red = (int) Math.min(255, originalColor.getRed() * factor);
                int green = (int) Math.min(255, originalColor.getGreen() * factor);
                int blue = (int) Math.min(255, originalColor.getBlue() * factor);
                Color newColor = new Color(red, green, blue);
                adjustedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return adjustedImage;
    }
}
