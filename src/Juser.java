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
    static String contrasena;

    
    Juser(String nombre, String contrasena){
    Juser.nombre = nombre;
    Juser.contrasena = contrasena;
    }
    public String getNombre(){
        return nombre;
    }
    public static void setNombre(String nombre){
        Juser.nombre = nombre;
    }    
    public String getContrasena(){
        return contrasena;
    }
    public static void setContrasena(String contrasena){
        Juser.contrasena = contrasena;
    }
    public static void createUser(Scanner scanner) {
        Jfun.clear();
        System.out.print("### Registrarse ###\n");
        System.out.print(">> Ingrese su nombre de usuario:\n>> ");
        String user = scanner.nextLine();
        if (verificarCredenciales(user, null)) {
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
            Jmenus.mainMenu();
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
        if (verificarCredenciales(user, password)) {
            Jfun.clear();
            System.out.println(">> Hola " + user + ".");
            setNombre(user);
            setContrasena(password);
            Jfun.pausa(1000);
            Jmenus.mainMenu();
            return;    
        }
    }
    public static boolean verificarCredenciales(String nombredeusuario, String contrasena) {
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");
                String usuarioExiste = parts[0];
                String contrasenaGuardada = parts[1];
                
                if (nombredeusuario != null && usuarioExiste.equals(nombredeusuario)) {
                    return true;  // Verificar solo el nombre de usuario
                }
                
                if (contrasena != null && contrasenaGuardada.equals(contrasena)) {
                    return true;  // Verificar solo la contraseña
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> Error al abrir el archivo: " + e.getMessage());
        }
        return false;
    }

}