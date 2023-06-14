import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Juser {
    public static final String FILE_PATH = "data/users"; // Ruta del archivo donde se guardarán las passwords

    public static void logIn() {
        
    }

    public static void createUser(Scanner scanner) {
        Jfun.clear();
        System.out.print("### Registrarse ###\n");
        System.out.print(">> Ingrese su nombre de usuario:\n>> ");
        String user = scanner.nextLine();
        System.out.print(">> Ingrese su password:\n>> ");
        String password = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) { //Intenta crear un archivo en la ruta establecida y escribirle contenido.
            Jfun.clear();
            System.out.println(">> Creando usuario...");
            Jfun.pausa(1500);
            writer.println(user + ":" + password); //Agrega el contenido en una nueva linea del archivo.
            System.out.println(">> Usuario creado.\nPresione [ENTER] para continuar.");
            scanner.nextLine(); // Consume la siguiente linea del scanner.
        } catch (IOException e) {
            Jfun.pausa(500);
            System.out.println(">> Error al crear o guardar usuario: " + e.getMessage());
        }
    }   
}

