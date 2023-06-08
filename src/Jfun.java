package src;

import java.util.Scanner;

public class Jfun {
    static Scanner sc = new Scanner(System.in);

    public static void print(String msg) { // Metodo abreviado de salida.
        System.out.print(msg);
    }

    public static void pausa(int ms) { // Pausa la ejecucion durante x milisegundos.
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void clear() { // Limpia la consola.
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void welcome() { // Mensaje de bienvenida.
        clear();
        print("###\nSecureKey Manager 0.1\nCaC 4.0-23438-2023 SEM1\nAlumno: Matías Martín Murad Pretz\n33.366.158\nDocente: Gonzalo F. Rubé\nTutora: Zoraida Flores\n###");
        pausa(2000);
    }

    public static void menu() { // Menu principal.
        clear();
        print("### MENÚ PRINCIPAL ###\nSeleccione una opción:\n1. Iniciar sesión.\n2. Crear usuario.\n3. Salir.\n>");
        int caso = sc.nextInt();
        switch (caso) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                // crearUsuario();
                break;
            case 3:
                System.out.println("Saliendo...");
                pausa(1000);
                System.exit(0);
            default:
                System.out.println("Opción no válida. Vuelva a intentarlo.");
                break;
        }
    }

    public static void iniciarSesion() {
        clear();
        print("### INICIAR SESION ###\nUsuario:\n>");
        String usuario = sc.next();
        print("Password:\n>");
        String password = sc.next();
    }
    /*
     * def iniciarSesion
     * print ingrese usuario
     * in usuario
     * print ingrese pass
     * in pass
     * if ok
     * menuInterno
     * def ok
     * def crear usuario
     * print nombre de usuario
     * in nombre de usuario
     * print pass
     * in pass
     * save()
     * def menu interno
     * print crearPass o modificarPass o eliminarPass o cerrarSesion
     * def crearPass
     * def modificarPass
     * def eliminarPass
     * def cerrarSesion
     * def salir
     * def save
     */
}