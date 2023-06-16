import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Juser {
    public static final String FILE_PATH = "data/users"; // Ruta del archivo donde se guardarán las passwords
    
    static String nombre;
    
    Juser(String nombre){
    Juser.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }
    public static void setNombre(String nombre){
        Juser.nombre = nombre;
    }

    public static void createUser(Scanner scanner) {
        Jfun.clear();
        System.out.print("### Registrarse ###\n");
        System.out.print(">> Ingrese su nombre de usuario:\n>> ");
        String user = scanner.nextLine();
        if (verifUsr(user)) {
            Jfun.clear();
            System.out.println(">> El nombre de usuario ya está en uso.\nPresione [ENTER] para continuar.");
            scanner.nextLine();
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            System.out.print(">> Ingrese su password:\n>> ");
            String password = scanner.nextLine();
            Jfun.clear();
            System.out.println(">> Creando usuario...");
            Jfun.pausa(1500);
            writer.println(user + ":" + password);
            System.out.println(">> Usuario creado.\nPresione [ENTER] para continuar.");
            scanner.nextLine();
        } catch (IOException e) {
            Jfun.pausa(500);
            System.out.println(">> Error al crear usuario: " + e.getMessage());
        }
    }

    public static void logIn(Scanner scanner) {
        Console console = System.console();

        Jfun.clear();
        System.out.print("### Iniciar sesion ###\n");
        System.out.print(">> Ingrese su nombre de usuario:\n>> ");
        String user = scanner.nextLine();
        char[] passwordArray = console.readPassword(">> Ingrese su password:    (echo=off)\n>> ");
        String password = new String(passwordArray);

        
        if (verifUsr(user) && verifPass(user+":"+password)) {
            Jfun.clear();
            System.out.println(">> Bienvenido " + user + ".");
            setNombre(user);
            Jfun.pausa(2000);
            Jmenus.mainMenu();
            return;    
        }
    }
    public static boolean verifUsr(String nombredeusuario) {
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");
                String usuarioexiste = parts[0];
                if (usuarioexiste.equals(nombredeusuario)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> Error al abrir el archivo: " + e.getMessage());
        }
        return false;
    }  
    public static boolean verifPass(String contrasena) {
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();                
                if (line.equals(contrasena)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> Error al abrir el archivo: " + e.getMessage());
        }
        return false;
    }  




}