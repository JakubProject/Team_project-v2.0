import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class FileUtils {

    /**
     * Zapisuje obraz w podanej ścieżce.
     *
     * @param image BufferedImage obraz do zapisania
     * @param directory Ścieżka do folderu, w którym zapisujemy obraz
     * @param fileName Nazwa pliku
     * @throws IOException jeśli zapis nie powiedzie się
     */
    public static void saveImage(BufferedImage image, String directory, String fileName) throws IOException {
        ensureDirectoryExists(directory); // Upewnij się, że katalog istnieje
        File outputFile = new File(directory, fileName);
        ImageIO.write(image, "png", outputFile);
    }

    /**
     * Wyświetla listę dostępnych zdjęć w podanym katalogu.
     *
     * @param directory Ścieżka do katalogu z obrazami
     */
    public static void listImages(String directory) {
        File dir = new File(directory);
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".png") ||
                name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".jpeg"));
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + ". " + files[i].getName());
            }
        } else {
            System.out.println("Brak obrazów w folderze " + directory);
        }
    }

    /**
     * Zwraca tablicę plików obrazów w podanym katalogu.
     *
     * @param directory Ścieżka do katalogu z obrazami
     * @return Tablica plików obrazów
     */
    public static File[] getImageFiles(String directory) {
        File dir = new File(directory);
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".png") ||
                name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".jpeg"));
        // Posortuj pliki alfabetycznie
        if (files != null) {
            Arrays.sort(files);
        }
        return files;
    }

    /**
     * Upewnia się, że katalog istnieje; jeśli nie, tworzy go.
     *
     * @param directoryPath Ścieżka do katalogu
     */
    public static void ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Tworzy katalogi, jeśli nie istnieją
        }
    }
}
