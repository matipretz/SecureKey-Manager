import java.util.Scanner;

public class Menus {
    public static void logueo() {
        while (true) {
            Varios.limpiar();
            System.out.print(
                    "### SecureKey Manager 0.2 ###\nSeleccione una opción:\n1. Iniciar sesion.\n2. Registrarse.\n3. Salir.\n>>");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    Usuario.ingresar(scanner);
                    break;
                case 2:
                    Usuario.registrarse(scanner);
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

    public static void principal() {
        while (true) {
            Varios.limpiar();
            System.out.println("Cargando...");
            Varios.pausa(500);
            Varios.limpiar();
            System.out.print("### SecureKey Manager 0.2 ###         " + Usuario.llave
                    + "\nSeleccione una opción:\n1. Crear password.\n2. Leer passwords.\n3. Editar passwords.\n4. Borar password.\n5. Configuracion de cuenta.\n6. Cerrar sesion.\n>>");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    CLAB.crear(scanner);
                    break;
                case 2:
                    CLAB.leer(scanner);
                    break;
                case 3:
                    CLAB.actualizar(scanner);
                    break;
                case 4:
                    CLAB.borrar(scanner);
                    break;
                case 5:
                    Usuario.cuenta();
                    break;
                case 6:
                    Varios.limpiar();
                    System.out.print(">> Cerrando sesion...");
                    Varios.pausa(1500);
                    Varios.limpiar();
                    logueo();
                default:
                    System.out.print(">> Opción no válida. Vuelva a intentarlo.");
                    break;
            }
        }
    }
}