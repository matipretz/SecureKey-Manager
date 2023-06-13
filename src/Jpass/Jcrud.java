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
    public static final String FILE_PATH = "data/register"; // Ruta del archivo donde se guardarán las passwords

    public static void createPassword(Scanner scanner) {
        Jfun.clear();
        System.out.print("### Crear passwords ###\n");
        System.out.print(">> Ingrese un nombre para la password:\n>> ");
        String name = scanner.nextLine();
        System.out.print(">> Ingrese la password:\n>> ");
        String password = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) { //Intenta crear un archivo en la ruta establecida y escribirle contenido.
            Jfun.clear();
            System.out.println(">> Creando archivo " + name + ".");
            Jfun.pausa(500);
            writer.println(name + ":" + password); //Agrega el contenido en una nueva linea del archivo.
            System.out.println(">> password creada y guardada correctamente.\nPresione [ENTER] para continuar.");
            scanner.nextLine(); // Consume la siguiente linea del scanner.
        } catch (IOException e) {
            Jfun.pausa(500);
            System.out.println(">> Error al crear o guardar la password: " + e.getMessage());
        }
    }
    public static void listPasswords() {
            Jfun.pausa(500);
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) { // Intenta crear un archivo tomando los datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { //Recorre el registro.
                String line = fileScanner.nextLine(); // Linea por linea.
                System.out.println(line.split(":")[0]); //Dividir la linea por su separador e imprimir la primera parte.
                Jfun.pausa(50);
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de passwords. Error:" + e);
        }
    }
    public static void readPassword(Scanner scanner) {
        Jfun.clear();
        System.out.print("### Leer passwords ###\n");
        listPasswords();
        System.out.print(">> Ingrese el nombre de la password: ");
        String name = scanner.nextLine(); // Toma el dato buscado por el usuario.
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) { // Intenta crear un archivo tomando los datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { // Mientras tenga otra linea de datos.
                String line = fileScanner.nextLine(); // Crea un string para la linea.
                String[] parts = line.split(":"); // Divide la linea por un separador.
                String passwordName = parts[0]; // Crea un string para la primera parte de la linea.
                String password = parts[1]; // Crea un string para la segunda parte de la linea.
                if (passwordName.equals(name)) { // Si la primera parte de la linea coincide con el dato ingresado:
                    System.out.println(">> Password: " + password+"\nPresione [ENTER] para continuar."); // Muestra la segunda parte de la linea.
                    scanner.nextLine(); // Consume la siguiente linea del scanner.
                    return;
                }
            }
            System.out.println(">> La password no existe.");
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de passwords. Error:" + e);
        }
    }
    public static void updatePassword(Scanner scanner) {
        Jfun.clear();
        System.out.print("### Editar passwords ###\n");
        listPasswords();
        System.out.print(">> Ingrese el nombre de la password a editar: ");
        String name = scanner.nextLine(); // Toma el dato buscado por el usuario.
        List<String> lines = new ArrayList<>(); // Crea una lista.
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) { // Intenta crear un archivo tomando los datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { // Mientras tenga otra linea de datos.
                String line = fileScanner.nextLine(); // Crea un string para la linea.
                String[] parts = line.split(":"); // Divide la linea por un separador.
                String passwordName = parts[0]; // Crea un string para la primera parte de la linea.
                if (passwordName.equals(name)) { // Si la primera parte de la linea coincide con el dato ingresado:
                    System.out.print(">> Ingrese la nueva password: ");
                    String newPassword = scanner.nextLine();// Toma el nuevo valor.
                    line = passwordName + ":" + newPassword; // Agregar la linea a la lista.                    
                }
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de passwords. Error: " + e);
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String line : lines) { // Recorrer la lista con un bucle for each.
                writer.println(line);// Escribe cada linea, editando de esta forma la linea que contiene el par buscado.
            }
            System.out.println(">> Password: " + name + " actualizada.\nPresione [ENTER] para continuar."); // Muestra la segunda parte de la linea.
            scanner.nextLine(); // Consume la siguiente linea del scanner.
        } catch (IOException e) {
            System.out.println(">> Error al editar la password: " + e.getMessage());
        }
    }
    public static void deletePassword(Scanner scanner) {
        Jfun.clear();
        System.out.print("### Borrar passwords ###\n");
        listPasswords();
        System.out.print(">> Ingrese el nombre de la password a borrar: ");
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
            System.out.println(">> No se encontró el archivo de passwords. Error:" + e);
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) { //Intenta crear un archivo en modo escritura en la ruta establecida para:
            for (String line : lines) { // Recorrer la lista con un bucle for each.
                writer.println(line);// Escribe cada linea, borrando de esta forma la linea que contiene el par buscado.
            }
            System.out.println(">> Password " + name + " borrada correctamente.\nPresione [ENTER] para continuar.");
            scanner.nextLine(); // Consume la siguiente linea del scanner.
        } catch (IOException e) {
            System.out.println(">> Error al borrar la password: " + e.getMessage());
        }
    }
}