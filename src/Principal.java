import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void logueo(List<Usuario> usuarios, Scanner sc) {
        while (true) {
            Varios.limpiar();
            System.out.print("### SecureKey Manager 0.2 ###\nSeleccione una opción:\n1. Registrarse.\n2. Iniciar sesion.\n3. Salir.\n>>");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    registrarse(usuarios, sc);
                    break;
                case 2:
                    ingresar(usuarios, sc);
                    break;
                case 3:
                    Varios.limpiar();
                    System.out.print(">> Saliendo...");
                    Varios.pausa(1500);
                    Varios.limpiar();
                    System.exit(0);
                default:
                    System.out.print(">> Opción no válida. Vuelva a intentarlo.");
                    break;
            }
        }
    }

    public static void registrarse(List<Usuario> usuarios, Scanner sc) {
        Varios.limpiar();
        System.out.print("### Registrarse ###\n");
        System.out.print(">> Ingrese su nombre de usuario:\n>> ");
        String nombreUsuario = sc.nextLine();
        System.out.println("Ingrese una contraseña");
        String contrasenaUsuario = sc.nextLine();
        usuarios.add(new Usuario(nombreUsuario, contrasenaUsuario));
        System.out.println(">> Creando usuario...");
        Varios.pausa(1500);
        System.out.println(">> Usuario creado.\n>> Presione [ENTER] para continuar.");
        sc.nextLine();
        logueo(usuarios, sc);
    }

    static void ingresar(List<Usuario> usuarios, Scanner sc) {
        Console console = System.console();
        Varios.limpiar();
        System.out.print("### Iniciar sesion ###\n");
        System.out.print(">> Ingrese su nombre de usuario:\n>> ");
        String user = sc.nextLine();
        char[] passwordArray = console.readPassword(">> Ingrese su password:    (echo=off)\n>> ");
        String password = new String(passwordArray);
        if (Varios.verificar(user, password, PATH)) {
            Varios.limpiar();
            System.out.println(">> Hola " + user + ".");
            setNombreUsuario(user);
            setContrasenaUsuario(password);
            Varios.pausa(1000);
            Menus.principal();
            return;
        }
    }

    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Scanner sc = new Scanner(System.in);
        Varios.verificarDirectorios();
        Varios.mensaje();
        logueo(usuarios, sc);
    }
}