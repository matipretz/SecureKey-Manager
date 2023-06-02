import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Jfun {
    public static void save() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Name your password: ");
            String title = sc.nextLine();
            if (!title.trim().isEmpty()) {
                checkDirectories();
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter("data/reg/" + title, true);
                    fileWriter.write(title);
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage());
                } finally {
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (IOException e) {
                            System.out.println("Error closing file: " + e.getMessage());
                        }
                    }
                }
                break;
            } else {
                System.out.println("Password name cannot be empty. Please enter a valid name.");
            }
        }
    }

    public static void checkDirectories() {
        File directory = new File("data/reg");
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.out.println("Error creating directory: " + directory.getPath());
            }
        }
    }
    public static List<String> listarArchivos(String directorio) {
        checkDirectories();
        List<String> archivos = new ArrayList<>();
        File[] files = new File(directorio).listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
                archivos.add(fileNameWithoutExtension);
                System.out.println(fileNameWithoutExtension);
            }
        }
        return archivos;
    }
    public static void backup(String src, String dest, boolean restore) {
        checkDirectories();
        File srcFile = new File(src);
        File destFile = new File(dest);
        try {
            if (restore) {
                copyDirectory(srcFile.toPath(), destFile.toPath());
                System.out.println("Restoring backup...");
                System.out.println("Backup restored.");
            } else {
                copyDirectory(srcFile.toPath(), destFile.toPath());
                System.out.println("Creating backup...");
                System.out.println("Backup created.");
            }
        } catch (IOException e) {
            if (e instanceof NoSuchFileException) {
                try {
                    Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    if (restore) {
                        System.out.println("File restored.");
                    } else {
                        System.out.println("File copied.");
                    }
                } catch (IOException ex) {
                    System.out.println("Error copying file: " + ex.getMessage());
                }
            } else {
                System.out.println("Directory not copied. Error: " + e.getMessage());
            }
        }
    }
    public static void copyDirectory(Path source, Path destination) throws IOException {
        Files.walk(source)
                .forEach(sourcePath -> {
                    try {
                        Path targetPath = destination.resolve(source.relativize(sourcePath));
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.out.println("Error copying directory: " + e.getMessage());
                    }
                });
    }
}