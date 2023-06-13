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
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) { //Intenta crear un archivo en la ruta establecida y escribirle contenido.
            System.out.println(">> Creando archivo " + name + ".");
            Jfun.pausa(500);
            writer.println(name + ":" + password); //Agrega el contenido en una nueva linea del archivo.
            System.out.println(">> Contraseña creada y guardada correctamente.");
        } catch (IOException e) {
            Jfun.pausa(500);
            System.out.println(">> Error al crear o guardar la contraseña: " + e.getMessage());
        }
    }

    public static void listPasswords() {
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) { // Intenta crear un archivo tomando los datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { //Recorre el registro.
                String line = fileScanner.nextLine(); // Linea por linea.
                System.out.println(line.split(":")[0]); //Dividir la linea por su separador e imprimir la primera parte.
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de contraseñas. Error:" + e);
        }
    }

    public static void readPassword(Scanner scanner) {
        System.out.print(">> Ingrese el nombre de la contraseña: ");
        String name = scanner.nextLine(); // Toma el dato buscado por el usuario.
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) { // Intenta crear un archivo tomando los datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { // Mientras tenga otra linea de datos.
                String line = fileScanner.nextLine(); // Crea un string para la linea.
                String[] parts = line.split(":"); // Divide la linea por un separador.
                String passwordName = parts[0]; // Crea un string para la primera parte de la linea.
                String password = parts[1]; // Crea un string para la segunda parte de la linea.
                if (passwordName.equals(name)) { // Si la primera parte de la linea coincide con el dato ingresado:
                    System.out.println(">> Contraseña: " + password+"\nPresione [ENTER] para continuar."); // Muestra la segunda parte de la linea.
                    scanner.nextLine(); // Consume la siguiente linea del scanner.
                    return;
                }
            }
            System.out.println(">> La contraseña no existe.");
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de contraseñas. Error:" + e);
        }
    }

    public static void deletePassword(Scanner scanner) {
        System.out.print(">> Ingrese el nombre de la contraseña a borrar: ");
        String name = scanner.nextLine(); // Toma el dato buscado por el usuario.
        List <String> lines = new ArrayList<>(); // Crea una lista.
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) { // Intenta crear un archivo tomando los datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { // Mientras tenga otra linea de datos.
                String line = fileScanner.nextLine(); // Crea un string para la linea.
                String[] parts = line.split(":"); // Divide la linea por un separador.
                String passwordName = parts[0]; // Crea un string para la primera parte de la linea.
                if (!passwordName.equals(name)) { // Si la primera parte de la linea no coincide con el dato ingresado:
                    lines.add(line); // Agregar la linea a la lista.
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de contraseñas. Error:" + e);
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) { //Intenta crear un archivo en modo escritura en la ruta establecida para:
            for (String line : lines) { // Recorrer la lista con un bucle for each.
                writer.println(line);// Escribe cada linea, borrando de esta forma la linea que contiene el par buscado.
            }
            System.out.println(">> Contraseña borrada correctamente.");
        } catch (IOException e) {
            System.out.println(">> Error al borrar la contraseña: " + e.getMessage());
        }
    }
}
