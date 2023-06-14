import java.util.Scanner;

public class JloginMenu {
    
    public static void loginMenu() {
        while (true) {
            Jfun.clear();
            System.out.print("### SecureKey Manager 0.2 ###\nSeleccione una opción:\n1. Iniciar sesion.\n2. Registrarse.\n3. Salir.\n>");
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
                    System.out.print("Saliendo...");
                    Jfun.pausa(1500);
                    Jfun.clear();
                    System.exit(0);
                default:
                    System.out.print("Opción no válida. Vuelva a intentarlo.");
                    break;
            }
        }        
    }
}