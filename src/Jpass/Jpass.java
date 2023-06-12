package src.Jpass;

import java.util.Scanner;

public class Jpass {
    public static void main(String[] args) {        
        Jfun.chkDirs();
        Jfun.welcome();
        
        while (true) {
            Jfun.clear();
            System.out.print("### SecureKey Manager 0.2 ###\nSeleccione una opción:\n1. Crear password.\n2. Lista de passwords.\n3. Borar password.\n4. Salir.\n>");
            Scanner scanner = new Scanner(System.in);

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    Jcrud.createPassword(scanner);
                    break;
                case 2:
                    Jcrud.listPasswords();
                    Jcrud.readPassword(scanner);
                    break;
                case 3:
                    Jcrud.deletePassword(scanner);
                    break;
                case 4:
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