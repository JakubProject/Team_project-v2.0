import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main {

    private static final String RESOURCES_PATH = "resources";
    private static final String OUTPUT_PATH = "tests"; // Ścieżka do folderu z przerobionymi zdjęciami

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TerminalUtils.clearConsole();

            System.out.println("=====================================");
            System.out.println("    Witamy w programie Pixel Art    ");
            System.out.println("=====================================");
            System.out.println("Wybierz opcję:");
            System.out.println("1. Spikselizowanie zdjęcia w kolorze szarym");
            System.out.println("2. Spikselizowanie zdjęcia w oryginalnych kolorach");
            System.out.println("3. Zmiana jasności obrazu");
            System.out.println("4. Zmiana kontrastu obrazu");
            System.out.println("5. Zastosowanie filtru");
            System.out.println("6. Czyszczenie wszystkich przerobionych zdjęć");
            System.out.println("7. Zakończ działanie programu");

            int choice = getValidInteger(scanner);

            if (choice == 7) {
                System.out.println("Dziękujemy za korzystanie z programu. Do zobaczenia!");
                break;
            }

            if (choice == 6) {
                clearProcessedImages();
                continue;
            }

            TerminalUtils.clearConsole();
            System.out.println("=====================================");
            System.out.println("      Wybór zdjęcia do przeróbki     ");
            System.out.println("=====================================");
            System.out.println("Dostępne zdjęcia:");
            FileUtils.listImages(RESOURCES_PATH);

            System.out.println("Podaj numer zdjęcia do przerobienia:");
            int imageChoice = getValidInteger(scanner);

            File[] imageFiles = FileUtils.getImageFiles(RESOURCES_PATH);
            if (imageFiles == null || imageFiles.length == 0 || imageChoice < 1 || imageChoice > imageFiles.length) {
                System.out.println("Nieprawidłowy wybór.");
                continue;
            }

            File selectedFile = imageFiles[imageChoice - 1];
            BufferedImage image;
            try {
                image = ImageIO.read(selectedFile);
            } catch (IOException e) {
                System.out.println("Nie udało się wczytać obrazu: " + selectedFile.getName());
                continue;
            }

            BufferedImage processedImage = null;
            String outputFileName = null;

            switch (choice) {
                case 1:
                    int grayPixelSize;
                    while (true) {
                        System.out.println("=====================================");
                        System.out.println("     Rozmiar piksela dla szarości     ");
                        System.out.println("=====================================");
                        System.out.println("Podaj rozmiar piksela (1-100):");
                        grayPixelSize = getValidInteger(scanner);

                        if (grayPixelSize >= 1 && grayPixelSize <= 100) {
                            break;
                        } else {
                            System.out.println("Nieprawidłowy rozmiar piksela. Spróbuj ponownie.");
                        }
                    }
                    processedImage = GrayScalePixelArtGenerator.convertToGrayScale(image, grayPixelSize);
                    outputFileName = selectedFile.getName().replace(".", "_gray_pixelated.");
                    break;
                case 2:
                    int colorPixelSize;
                    while (true) {
                        System.out.println("=====================================");
                        System.out.println(" Rozmiar piksela dla kolorów        ");
                        System.out.println("=====================================");
                        System.out.println("Podaj rozmiar piksela (1-100):");
                        colorPixelSize = getValidInteger(scanner);

                        if (colorPixelSize >= 1 && colorPixelSize <= 100) {
                            break;
                        } else {
                            System.out.println("Nieprawidłowy rozmiar piksela. Spróbuj ponownie.");
                        }
                    }
                    processedImage = ColorPixelArtGenerator.convertToPixelArt(image, colorPixelSize);
                    outputFileName = selectedFile.getName().replace(".", "_color_pixelated.");
                    break;
                case 3:
                    float brightnessPercentage;
                    while (true) {
                        System.out.println("=====================================");
                        System.out.println("     Zmiana jasności obrazu          ");
                        System.out.println("=====================================");
                        System.out.println("Podaj procentowy współczynnik jasności:");
                        System.out.println("Wartość większa niż 100 zwiększa jasność obrazu.");
                        System.out.println("Wartość mniejsza niż 100 zmniejsza jasność obrazu.");
                        System.out.println("Np. 120 oznacza zwiększenie jasności o 20%,");
                        System.out.println("a 80 oznacza zmniejszenie jasności o 20%.");
                        System.out.print("Procentowy współczynnik jasności: ");
                        String input = scanner.nextLine();
                        try {
                            brightnessPercentage = Float.parseFloat(input);
                            if (brightnessPercentage > 0) {
                                break;
                            } else {
                                System.out.println("Procentowy współczynnik jasności musi być większy niż 0.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Nieprawidłowy format liczby. Wprowadź poprawną liczbę.");
                        }
                    }
                    float brightnessFactor = brightnessPercentage / 100.0f;
                    processedImage = BrightnessAdjuster.adjustBrightness(image, brightnessFactor);
                    outputFileName = selectedFile.getName().replace(".", "_brightened.");
                    break;
                case 4:
                    float contrastPercentage;
                    while (true) {
                        System.out.println("=====================================");
                        System.out.println("     Zmiana kontrastu obrazu         ");
                        System.out.println("=====================================");
                        System.out.println("Podaj procentowy współczynnik kontrastu:");
                        System.out.println("Wartość większa niż 100 zwiększa kontrast obrazu.");
                        System.out.println("Wartość mniejsza niż 100 zmniejsza kontrast obrazu.");
                        System.out.println("Np. 120 oznacza zwiększenie kontrastu o 20%,");
                        System.out.println("a 80 oznacza zmniejszenie kontrastu o 20%.");
                        System.out.print("Procentowy współczynnik kontrastu: ");
                        String input = scanner.nextLine();
                        try {
                            contrastPercentage = Float.parseFloat(input);
                            if (contrastPercentage > 0) {
                                break;
                            } else {
                                System.out.println("Procentowy współczynnik kontrastu musi być większy niż 0.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Nieprawidłowy format liczby. Wprowadź poprawną liczbę.");
                        }
                    }
                    float contrastFactor = contrastPercentage / 100.0f;
                    processedImage = ContrastAdjuster.adjustContrast(image, contrastFactor);
                    outputFileName = selectedFile.getName().replace(".", "_contrasted.");
                    break;
                case 5:
                    // Wybór filtru
                    System.out.println("=====================================");
                    System.out.println("     Wybór filtru                    ");
                    System.out.println("=====================================");
                    System.out.println("Wybierz filtr:");
                    System.out.println("1. Sepia");
                    System.out.println("2. Szarość");
                    System.out.println("3. Inwersja kolorów");
                    System.out.println("4. Niebieski odcień");
                    System.out.println("5. Czerwony odcień");
                    int filterChoice = getValidInteger(scanner);
                    switch (filterChoice) {
                        case 1:
                            processedImage = ImageFilters.SepiaFilter.apply(image);
                            outputFileName = selectedFile.getName().replace(".", "_sepia.");
                            break;
                        case 2:
                            processedImage = ImageFilters.GrayscaleFilter.apply(image);
                            outputFileName = selectedFile.getName().replace(".", "_grayscale.");
                            break;
                        case 3:
                            processedImage = ImageFilters.InvertFilter.apply(image);
                            outputFileName = selectedFile.getName().replace(".", "_inverted.");
                            break;
                        case 4:
                            processedImage = ImageFilters.BlueTintFilter.apply(image);
                            outputFileName = selectedFile.getName().replace(".", "_blue_tint.");
                            break;
                        case 5:
                            processedImage = ImageFilters.RedTintFilter.apply(image);
                            outputFileName = selectedFile.getName().replace(".", "_red_tint.");
                            break;
                        default:
                            System.out.println("Nieprawidłowy wybór filtru.");
                            continue;
                    }
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja.");
                    continue;
            }

            // Pokazuje obraz po przetworzeniu
            if (processedImage != null) {
                ImageViewer viewer = new ImageViewer(processedImage);
                viewer.showImage();

                System.out.println("=====================================");
                System.out.println(" Czy chcesz zapisać zmodyfikowany obraz? (tak/nie) ");
                System.out.println("=====================================");
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("tak")) {
                    File outputDirectory = new File(OUTPUT_PATH);
                    if (!outputDirectory.exists()) {
                        outputDirectory.mkdir();
                    }

                    File outputFile = new File(outputDirectory, outputFileName);
                    try {
                        ImageIO.write(processedImage, "jpg", outputFile);
                        System.out.println("Obraz zapisany jako: " + outputFile.getAbsolutePath());
                    } catch (IOException e) {
                        System.out.println("Nie udało się zapisać obrazu.");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Zmiany zostały odrzucone.");
                }
            }
        }

        scanner.close();
    }

    private static int getValidInteger(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy format liczby. Wprowadź poprawną liczbę.");
            }
        }
    }

    private static void clearProcessedImages() {
        File outputDirectory = new File(OUTPUT_PATH);
        if (!outputDirectory.exists() || !outputDirectory.isDirectory()) {
            System.out.println("Folder z przerobionymi zdjęciami nie istnieje.");
            return;
        }

        File[] files = outputDirectory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("Nie przerobiono jeszcze żadnych zdjęć.");
            return;
        }

        for (File file : files) {
            if (!file.isDirectory()) {
                if (file.delete()) {
                    System.out.println("Usunięto plik: " + file.getName());
                } else {
                    System.out.println("Nie udało się usunąć pliku: " + file.getName());
                }
            }
        }
    }
}
