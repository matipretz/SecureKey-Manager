import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Usuario {
    static String PATH = "data";
    static String llave;
    static String valor;

    Usuario(String llave, String valor) {
        Usuario.llave = llave;
        Usuario.valor = valor;
    }

    public String getLlave() {
        return llave;
    }

    public static void setLlave(String llave) {
        Usuario.llave = llave;
    }

    public String getValor() {
        return valor;
    }

    public static void setValor(String valor) {
        Usuario.valor = valor;
    }

    static void registrarse(Scanner scanner) {
        String llave = scanner.nextLine();
        while (llave.trim().isEmpty()) {
            System.out.print(">> El nombre no puede estar vacío.\n>> Ingrese su nombre de usuario:\n>> ");
            llave = scanner.nextLine();
        }
        while (Varios.verificar(llave, null, PATH)) {
            Varios.clear();
            System.out.print(">> El nombre de usuario ya está en uso.\n>> Ingrese su nombre de usuario:\n>> ");
            llave = scanner.nextLine();
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(PATH, true))) {
            System.out.print(">> Ingrese su password:\n>> ");
            String valor = scanner.nextLine();
            while (valor.trim().isEmpty()) {
                System.out.print(">> El nombre no puede estar vacío. Ingrese un nombre para la password:\n>> ");
                valor = scanner.nextLine();
            }
            setLlave(llave);
            setValor(valor);
            writer.println(llave + ":" + valor);
            Menus.menuPrincipal();
        } catch (IOException e) {
            Varios.pausa(500);
            System.out.println(">> Error al crear usuario: " + e.getMessage());
        }
    }

    static void ingresar(Scanner scanner) {
        Console console = System.console();
        Varios.clear();
        System.out.print("### Iniciar sesion ###\n");
        System.out.print(">> Ingrese su nombre de usuario:\n>> ");
        String user = scanner.nextLine();
        char[] passwordArray = console.readPassword(">> Ingrese su password:    (echo=off)\n>> ");
        String password = new String(passwordArray);
        if (Varios.verificar(user, password, PATH)) {
            Varios.clear();
            System.out.println(">> Hola " + user + ".");
            setLlave(user);
            setValor(password);
            Varios.pausa(1000);
            Menus.menuPrincipal();
            return;
        }
    }

    static void accountSettings() {

    }
}