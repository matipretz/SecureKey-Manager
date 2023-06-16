import java.util.Scanner;

public class Jmenus {
    public static void mainMenu() {
        while (true) {
            Jfun.clear();
            System.out.print("### SecureKey Manager 0.2 ###\nSeleccione una opción:\n1. Crear password.\n2. Leer passwords.\n3. Editar passwords.\n4. Borar password.\n5. Configuracion de cuenta.\n6. Cerrar sesion.\n>>");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    Jcrud.createPassword(scanner);
                    break;
                case 2:
                    Jcrud.readPassword(scanner);
                    break;
                case 3:
                    Jcrud.updatePassword(scanner);
                    break;
                case 4:
                    Jcrud.deletePassword(scanner);
                    break;
                case 5:
                    Jcuenta.accountSettings();
                    break;
                case 6:
                    Jfun.clear();
                    System.out.print(">> Cerrando sesion...");
                    Jfun.pausa(1500);
                    Jfun.clear();
                    loginMenu();
                default:
                    System.out.print(">> Opción no válida. Vuelva a intentarlo.");
                    break;
            }
        }        
    }
    public static void loginMenu() {
        while (true) {
            Jfun.clear();
            System.out.print("### SecureKey Manager 0.2 ###\nSeleccione una opción:\n1. Iniciar sesion.\n2. Registrarse.\n3. Salir.\n>>");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    Juser.logIn(scanner);
                    break;
                case 2:
                    Juser.createUser(scanner);
                    break;
                case 3:
                    Jfun.clear();
                    System.out.print(">> Saliendo...");
                    Jfun.pausa(1500);
                    Jfun.clear();
                    System.exit(0);
                default:
                    System.out.print(">> Opción no válida. Vuelva a intentarlo.");
                    break;
            }
        }        
    }
}
