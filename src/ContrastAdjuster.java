import java.awt.Color;
import java.awt.image.BufferedImage;

public class ContrastAdjuster {

    /**
     * Adjusts the contrast of the image.
     *
     * @param image The original image.
     * @param contrastFactor The contrast adjustment factor. Values greater than 1.0 increase contrast,
     *                       and values between 0 and 1 decrease contrast.
     * @return The image with adjusted contrast.
     */
    public static BufferedImage adjustContrast(BufferedImage image, float contrastFactor) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage adjustedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));

                int red = clamp((int) (((color.getRed() / 255.0 - 0.5) * contrastFactor + 0.5) * 255.0));
                int green = clamp((int) (((color.getGreen() / 255.0 - 0.5) * contrastFactor + 0.5) * 255.0));
                int blue = clamp((int) (((color.getBlue() / 255.0 - 0.5) * contrastFactor + 0.5) * 255.0));

                Color newColor = new Color(red, green, blue);
                adjustedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return adjustedImage;
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
