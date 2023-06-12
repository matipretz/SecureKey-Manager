package src.Jpass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jcrud {
    public static final String FILE_PATH = "data/register"; // Ruta del archivo donde se guardarán las contraseñas

    public static void createPassword(Scanner scanner) {
        Jfun.clear();
        System.out.print("### Crear password ###\n");
        System.out.print(">> Ingrese un nombre para la contraseña:\n>> ");
        String name = scanner.nextLine();
        System.out.print(">> Ingrese la contraseña:\\n>> ");
        String password = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            System.out.println(">> Creando archivo " + name + ".");
            Jfun.pausa(500);
            writer.println(name + ":" + password);
            System.out.println(">> Contraseña creada y guardada correctamente.");
        } catch (IOException e) {
            Jfun.pausa(500);
            System.out.println("Error al crear o guardar la contraseña: " + e.getMessage());
        }
    }

    public static void listPasswords() {
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) { //Intenta instanciar nuestro archivo de registro para:
            while (fileScanner.hasNextLine()) { //Recorre el registro.
                String line = fileScanner.nextLine(); // Linea por linea.
                System.out.println(line.split(":")[0]); //Asigna a la variable "name" el valor de la primera parte del arrego.
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de contraseñas. Error:" + e);
        }
    }

    public static void readPassword(Scanner scanner) {
        System.out.print("Ingrese el nombre de la contraseña: ");
        String name = scanner.nextLine();
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");
                String passwordName = parts[0];
                String password = parts[1];
                if (passwordName.equals(name)) {
                    System.out.println("Contraseña: " + password+"\nPresione [ENTER] para continuar.");
                    scanner.nextLine();
                    return;
                }
            }
            System.out.println("La contraseña no existe.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de contraseñas. Error:" + e);
        }
    }

    public static void deletePassword(Scanner scanner) {
        System.out.print("Ingrese el nombre de la contraseña a borrar: ");
        String name = scanner.nextLine();
        List<String> lines = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");
                String passwordName = parts[0];
                if (!passwordName.equals(name)) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de contraseñas. Error:" + e);
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String line : lines) {
                writer.println(line);
            }
            System.out.println("Contraseña borrada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al borrar la contraseña: " + e.getMessage());
        }
    }
}
