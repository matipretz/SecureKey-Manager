import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void logueo(File Usuarios, File Contrasenas, List<Usuario> usuarios, Scanner sc) {
        while (true) {
            Varios.limpiar();
            System.out.print(
                    "### SecureKey Manager 0.2 ###\nSeleccione una opción:\n1. Registrarse.\n2. Iniciar sesion.\n3. Salir.\n>> ");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    registrarse(Usuarios, usuarios, sc);
                    logueo(Usuarios, Contrasenas, usuarios, sc);
                    break;
                case 2:
                    Usuario usuarioConectado = ingresar(usuarios, sc);
                    if (usuarioConectado != null) {
                        menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
                    } else {
                        System.out.println("No se ha podido iniciar sesión");
                        logueo(Contrasenas, Usuarios, usuarios, sc);
                    }
                    break;
                case 3:
                    Varios.limpiar();
                    System.out.print(">> Saliendo...");
                    Varios.pausa(1500);
                    Varios.limpiar();
                    System.exit(0);
                default:
                    System.out.print(">> Opción no válida. Vuelva a intentarlo.");
                    logueo(Usuarios, Contrasenas, usuarios, sc);
                    break;
            }
        }
    }
    public static void registrarse(File Usuarios, List<Usuario> usuarios, Scanner sc) {
        Varios.limpiar();
        System.out.print("### Registrarse ###\n");
        System.out.print(">> Ingrese un nombre de usuario:\n>> ");
        String nombreUsuario = sc.nextLine();
        System.out.println("Ingrese una contraseña:\n>> ");
        String contrasenaUsuario = sc.nextLine();
        usuarios.add(new Usuario(nombreUsuario, contrasenaUsuario));
        System.out.println(">> Creando usuario...");
        guardarUsuarios(Usuarios, usuarios);
        Varios.pausa(1500);
        System.out.println(">> Usuario creado.\n>> Presione [ENTER] para continuar.");
        sc.nextLine();
    }
    public static Usuario ingresar(List<Usuario> usuarios, Scanner sc) {
        Console console = System.console();
        Varios.limpiar();
        System.out.print("### Iniciar sesion ###\n");
        System.out.print(">> Ingrese su nombre de usuario:\n>> ");
        String user = sc.nextLine();
        char[] passwordArray = console.readPassword(">> Ingrese su password:    (echo=off)\n>> ");
        String password = new String(passwordArray);
        int pos = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(user)) {
                pos = i;
            }
        }
        if (pos == -1) {
            System.out.println("Usuario no encontrado");
            return null;
        } else {
            if (usuarios.get(pos).getContrasenaUsuario().equals(password)) {
                Varios.limpiar();
                System.out.println(">> Hola " + user + ".");
                Varios.pausa(1000);
                return usuarios.get(pos); // si coinciden, me retorna el usuario en cuestión, accediendo mediante get()
            } else {
                System.out.println("Contraseña incorrecta");
                return null; // si no coinciden, me retorna null. Esta parte debería mejorarse
            }
        }
    }
    public static void menuContrasenas (File Contrasenas, File Usuarios, List <Usuario> usuarios, Usuario usuarioConectado, Scanner sc){
        while (true) {
                Varios.limpiar();
                System.out.println("Cargando...");
                Varios.pausa(500);
                Varios.limpiar();
                System.out.print("### SecureKey Manager 0.2 ###2\nSeleccione una opción:\n1. Crear password.\n2. Editar passwords.\n3. Borar passwords.\n4. Ver password.\n5. Configuracion de cuenta.(EN DESARROLLO)\n6. Cerrar sesion.\n>> ");
                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        crearContrasena(Contrasenas,Usuarios, usuarios, usuarioConectado, sc);
                        break;
                    case 2:
                        usuarioConectado.listarContrasenas();
                        modificarContrasena(Usuarios, usuarios, usuarioConectado, sc);
                        break;
                    case 3:
                        usuarioConectado.listarContrasenas();
                        eliminarContrasena(Contrasenas,Usuarios, usuarios, usuarioConectado, sc);
                        break;
                    case 4:
                        usuarioConectado.listarContrasenas();
                        System.out.println(">> Presione [ENTER] para continuar.");
                        sc.nextLine();
                        menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, scanner);
                        break;
                    case 5:
                        System.out.println(">> En desarrollo.");
                        System.out.println(">> Presione [ENTER] para continuar.");
                        sc.nextLine();
                        //Usuario.cuenta();
                        break;
                    case 6:
                        Varios.limpiar();
                        System.out.println(">> Hasta luego, " + usuarioConectado.getNombreUsuario()+".");
                        usuarioConectado = null;
                        System.out.print(">> Cerrando sesion...");
                        Varios.pausa(1500);
                        Varios.limpiar();
                        logueo(Usuarios, Contrasenas, usuarios, sc);
                    default:
                        System.out.print(">> Opción no válida. Vuelva a intentarlo.");
                        break;
                }
            }
    }
    public static void crearContrasena(File Contrasenas, File Usuarios, List <Usuario> usuarios, Usuario usuarioConectado, Scanner sc){
        Varios.limpiar();
        System.out.print("### Crear passwords ###\n");
        System.out.print(">> Ingrese un nombre para la password:\n>> ");
        String name = sc.nextLine();
        while (name.trim().isEmpty()) {
            Varios.limpiar();
            System.out.print(">> El nombre no puede estar vacio.\nIngrese un nombre para la password:\n>> ");
            name = sc.nextLine();
        }
        System.out.print(">> Ingrese la password:\n>> ");
        String password = sc.nextLine();
        while (password.trim().isEmpty()) { // Validar que el campo password no esté vacio
            System.out.print(">> La password no puede estar vacia. Ingrese la password:\n>> ");
            password = sc.nextLine();
        }
        System.out.println(usuarioConectado.getNombreUsuario());
        usuarioConectado.setContrasena(new Contrasenas(usuarioConectado.getNombreUsuario(), name, password));
        guardarContrasenas(Contrasenas, usuarios);
        System.out.println(">> Password creada y guardada correctamente.\n>> Presione [ENTER] para continuar.");
        sc.nextLine();
        menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
    }
    public static void eliminarContrasena(File Contrasenas, File Usuarios, List <Usuario> usuarios, Usuario usuarioConectado, Scanner sc){
        Varios.limpiar();
        System.out.print("### Borrar passwords ###\n");


        usuarioConectado.listarContrasenas();

        System.out.print(">> Ingrese el ID de la password a borrar:\n>> ");
        int indice = sc.nextInt();
        sc.nextLine();
        System.out.println(">> Está seguro/a de esta operación? [s / n]");
        String resp = sc.nextLine();
        if (resp.equals("s")) {
            usuarioConectado.getContrasenas().remove(indice);
            guardarContrasenas(Contrasenas, usuarios);
            System.out.println(">> Contrasena eliminada.");
            menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
        } else {
            System.out.println(">> Eliminación cancelada");
            menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
        }
    }
    public static void modificarContrasena(File Usuarios, List <Usuario> usuarios, Usuario usuarioConectado, Scanner sc){
        System.out.println(">> Ingrese ID de la postal que desea modificar:\n>> ");
        int indice = sc.nextInt();
        sc.nextLine();
        System.out.println(">> [n] Modificar nombre");
        System.out.println(">> [c] Modificar destinatario");
        String opcion = sc.nextLine();
        switch (opcion) {
            case "n":
                System.out.print(">> Ingrese nuevo nombre:\n>> ");
                String nombre = sc.nextLine();
                usuarioConectado.getContrasenas().get(indice).setNombre(nombre);
                break;
            case "c":
                System.out.print(">> Ingrese nueva contrasena:\n>> ");
                String contrasena = sc.nextLine();
                usuarioConectado.getContrasenas().get(indice).setContrasena(contrasena);
                break;
            default:
                System.out.println(">> Opción inválida.");
                modificarContrasena(Usuarios, usuarios, usuarioConectado, sc);
                break;
        }
        guardarContrasenas(Usuarios, usuarios);
        menuContrasenas(Usuarios, Usuarios, usuarios, usuarioConectado, sc);
    }
    public static void guardarUsuarios(File Usuarios, List<Usuario> usuarios) {
        try {
            FileWriter fw = new FileWriter(Usuarios);
            for (Usuario usuario : usuarios) {
                fw.write(usuario.getNombreUsuario() + ":" + usuario.getContrasenaUsuario() + "\n");
            }
            fw.close();
        } 
        catch (IOException e) {
            System.out.println(e);
        }
    }
    public static List<Usuario> traerUsuarios(File Usuarios) {
        List<Usuario> listaProvisoria = new ArrayList<Usuario>();
        String[] atributosUsuario = new String[2];
        try {
            Scanner sc = new Scanner(Usuarios);
            while (sc.hasNextLine()) {
                String datos = sc.nextLine();
                atributosUsuario = datos.split(":");
                listaProvisoria.add(new Usuario(atributosUsuario[0], atributosUsuario[1]));
            }
            sc.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return listaProvisoria;
    }
    public static void guardarContrasenas(File Contrasenas, List<Usuario> usuarios){
        try {
            FileWriter fw = new FileWriter(Contrasenas);
            for (Usuario usuario : usuarios) {
                for (Contrasenas contrasena : usuario.getContrasenas()) {
                    fw.write(usuario.getNombreUsuario() + ";" + contrasena.getNombre() + ";" + contrasena.getContrasena()+ "\n");
                }
            }
            fw.close();
        } 
        catch (IOException e) {
            System.out.println(e);
        }
    }
    public static List<Contrasenas> traerContrasenas(File Contrasenas){
        List<Contrasenas> provisoria = new ArrayList<Contrasenas>();
        String[] datosPostal = new String[5];
        try (Scanner sc = new Scanner(Contrasenas)){
            while (sc.hasNextLine()) {
                String datos = sc.nextLine();
                datosPostal = datos.split(";");
                provisoria.add(new Contrasenas(datosPostal[0], datosPostal[1], datosPostal[2]));
            }          
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return provisoria;        
    }
    public static void main(String[] args) throws IOException {
        Varios.verificarDirectorios();
        Varios.mensaje();
        File Usuarios = new File("data/Usuarios.txt");
        Usuarios.createNewFile();
        File Contrasenas = new File("data/Contrasenas.txt");
        Contrasenas.createNewFile();
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = traerUsuarios(Usuarios);
        for (Contrasenas contrasena : traerContrasenas(Contrasenas)) {
            for (Usuario usuario : usuarios) {
                if (contrasena.getId().equals(usuario.getNombreUsuario())) {
                    usuario.setContrasena(contrasena);
                }
            }
        }
        Scanner sc = new Scanner(System.in);
        logueo(Usuarios, Contrasenas, usuarios, sc);
    }
}