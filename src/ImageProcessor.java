import java.awt.image.BufferedImage;

public abstract class ImageProcessor {
    protected BufferedImage image;

    public ImageProcessor(BufferedImage image) {
        this.image = image;
    }

    public abstract BufferedImage processImage();
}
