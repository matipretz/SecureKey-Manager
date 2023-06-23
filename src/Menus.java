import java.util.Scanner;


public class Menus {    
    public static void loginMenu() {
        while (true) {
            Toys.clear();
            System.out.print("### SecureKey Manager 0.2 ###\nSeleccione una opción:\n1. Iniciar sesion.\n2. Registrarse.\n3. Salir.\n>>");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    CRUD.logIn(scanner);
                    break;
                case 2:
                    CRUD.crear(scanner);
                    break;
                case 3:
                    Toys.clear();
                    System.out.print(">> Saliendo...");
                    Toys.pausa(1500);
                    Toys.clear();
                    System.exit(0);
                default:
                    System.out.print(">> Opción no válida. Vuelva a intentarlo.");
                    break;
            }
        }        
    }
    public static void mainMenu() {
        while (true) {
            Toys.clear();
            System.out.println("Cargando...");
            Toys.pausa(500);
            Toys.clear();
            System.out.print("### SecureKey Manager 0.2 ###         " + CRUD.llave + "\nSeleccione una opción:\n1. Crear password.\n2. Leer passwords.\n3. Editar passwords.\n4. Borar password.\n5. Configuracion de cuenta.\n6. Cerrar sesion.\n>>");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    Guns.createPassword(scanner);
                    break;
                case 2:
                    Guns.readPassword(scanner);
                    break;
                case 3:
                    Guns.updatePassword(scanner);
                    break;
                case 4:
                    Guns.deletePassword(scanner);
                    break;
                case 5:
                    Settings.accountSettings();
                    break;
                case 6:
                    Toys.clear();
                    System.out.print(">> Cerrando sesion...");
                    Toys.pausa(1500);
                    Toys.clear();
                    loginMenu();
                default:
                    System.out.print(">> Opción no válida. Vuelva a intentarlo.");
                    break;
            }
        }        
    }
}