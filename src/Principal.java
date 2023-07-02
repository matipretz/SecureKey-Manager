import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    /**
     * Permite al usuario registrarse, iniciar sesión, o salir del programa.
     * 
     * @param Usuarios     El archivo que contiene los datos de los usuarios registrados.
     * @param Contrasenas  El archivo que contiene los datos de las contraseñas.
     * @param usuarios     La lista de usuarios registrados.
     * @param sc           El objeto Scanner utilizado para leer la entrada del usuario.
     */
    public static void logueo(File Usuarios, File Contrasenas, List<Usuario> usuarios, Scanner sc) {
        while (true) {
            Varios.limpiar();
            System.out.print(
                    "### SecureKey Manager 0.2 ###\nSeleccione una opción:\n1. Registrarse.\n2. Iniciar sesion.\n3. Salir.\n>> ");

            try {
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1: // Registrarse
                        registrarse(Usuarios, usuarios, sc);
                        logueo(Usuarios, Contrasenas, usuarios, sc);
                        break;
                    case 2: // Iniciar sesión
                        Usuario usuarioConectado = ingresar(usuarios, sc);
                        if (usuarioConectado != null) {
                            menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
                        } else {
                            logueo(Contrasenas, Usuarios, usuarios, sc);
                        }
                        break;
                    case 3: // Salir
                        Varios.limpiar();
                        System.out.print(">> Saliendo...");
                        Varios.pausa(1500);
                        Varios.limpiar();
                        System.exit(0);                
                    default: // Opción inválida
                        Varios.limpiar();
                        System.out.println(">> Opción no válida. Vuelva a intentarlo.");
                        Varios.continuar(sc);
                        logueo(Usuarios, Contrasenas, usuarios, sc);
                        break;
                }
            } catch (Exception e) { // Error en la entrada del usuario
                sc.nextLine();
                Varios.limpiar();
                System.out.println(">> Opción no válida. Vuelva a intentarlo.");
                Varios.continuar(sc);
                logueo(Usuarios, Contrasenas, usuarios, sc);
            }
        }
    }

    /**
     * Realiza el proceso de registro de un nuevo usuario.
     *
     * @param Usuarios  El archivo que contiene los datos de los usuarios registrados.
     * @param usuarios  La lista de usuarios registrados.
     * @param sc        El objeto Scanner utilizado para leer la entrada del usuario.
     */
    public static void registrarse(File Usuarios, List<Usuario> usuarios, Scanner sc) {
        Varios.limpiar();
        
        System.out.print("### Registrarse ###\n");
        String nombreUsuario; // Solicitar y validar el nombre de usuario
        boolean nombreValido = false;
        do {
            System.out.print(">> Ingrese un nombre de usuario:\n>> ");
            nombreUsuario = sc.nextLine();
            if (nombreUsuario.isEmpty()) {
                Varios.limpiar();
                System.out.println(">> El nombre de usuario no puede estar vacío.");
            } else if (Varios.existeUsuario(usuarios, nombreUsuario)) {
                Varios.limpiar();
                System.out.println(">> El nombre de usuario ya está en uso.");
            } else {
                nombreValido = true;
            }
        } while (!nombreValido);
        String contrasenaUsuario; // Solicitar y validar la contraseña del usuario

        do {
            Console console = System.console();
             char[] passwordArray = console.readPassword(">> Ingrese su password:    (echo=off)\n>> ");
            String password = new String(passwordArray);
            contrasenaUsuario = password;
            if (contrasenaUsuario.isEmpty()) {
                Varios.limpiar();
                System.out.println(">> La password no puede estar vacía.");
            }
        } while (contrasenaUsuario.isEmpty());
        usuarios.add(new Usuario(nombreUsuario, contrasenaUsuario)); // Agregar el nuevo usuario a la lista de usuarios registrados
        Varios.limpiar();
        System.out.println(">> Creando usuario...");
        guardarUsuarios(Usuarios, usuarios); // Guardar los usuarios en el archivo
        Varios.pausa(1500);
        System.out.println(">> Usuario creado.");
        Varios.continuar(sc);
    }
    
    /**
     * Realiza el proceso de inicio de sesión.
     *
     * @param usuarios La lista de usuarios registrados.
     * @param sc       El objeto Scanner utilizado para leer la entrada del usuario.
     * @return El objeto Usuario correspondiente al usuario conectado, o null si el inicio de sesión falló.
     */
    public static Usuario ingresar(List<Usuario> usuarios, Scanner sc) {
        Console console = System.console();
        Varios.limpiar();
        System.out.print("### Iniciar sesion ###\n");
        System.out.print(">> Ingrese su nombre de usuario:\n>> ");// Solicitar y leer el nombre de usuario
        String user = sc.nextLine();
        char[] passwordArray = console.readPassword(">> Ingrese su password:    (echo=off)\n>> "); // Solicitar y leer la contraseña del usuario
        String password = new String(passwordArray);
        int pos = -1;
        for (int i = 0; i < usuarios.size(); i++) { // Buscar el usuario en la lista de usuarios registrados
            if (usuarios.get(i).getNombreUsuario().equals(user)) {
                pos = i;
            }
        }
        if (pos == -1) { // Validar el inicio de sesión
            Varios.limpiar();
            System.out.println("Usuario no encontrado");
            Varios.continuar(sc);
            return null;
        } else {
            if (usuarios.get(pos).getContrasenaUsuario().equals(password)) {
                Varios.limpiar();
                System.out.println(">> Hola " + user + ".");
                Varios.pausa(1000);
                return usuarios.get(pos);
            } else {
                Varios.limpiar();
                System.out.println("Password incorrecta");
                Varios.continuar(sc);
                return null;
            }
        }
    }

    /**
     * Muestra el menú de contraseñas y gestiona las diferentes opciones disponibles para el usuario conectado.
     *
     * @param Contrasenas       El archivo que almacena las contraseñas.
     * @param Usuarios          El archivo que almacena los usuarios registrados.
     * @param usuarios          La lista de usuarios registrados.
     * @param usuarioConectado  El objeto Usuario correspondiente al usuario conectado.
     * @param sc                El objeto Scanner utilizado para leer la entrada del usuario.
     */
    public static void menuContrasenas(File Contrasenas, File Usuarios, List<Usuario> usuarios,
            Usuario usuarioConectado, Scanner sc) {
        while (true) {
            Varios.limpiar();
            System.out.println("Cargando...");
            Varios.pausa(500);
            Varios.limpiar();
            System.out.print(
                    "### SecureKey Manager 0.2 ###2\nSeleccione una opción:\n1. Crear password.\n2. Editar passwords.\n3. Borar passwords.\n4. Ver password.\n5. Configuracion de cuenta.(EN DESARROLLO)\n6. Cerrar sesion.\n>> ");
            try {
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1:
                        crearContrasena(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
                        break;
                    case 2:
                        usuarioConectado.listarContrasenas();
                        modificarContrasena(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
                        break;
                    case 3:
                        usuarioConectado.listarContrasenas();
                        eliminarContrasena(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
                        break;
                    case 4:
                        usuarioConectado.listarContrasenas();
                        Varios.continuar(sc);
                        menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
                        break;
                    case 5:
                        Varios.limpiar();
                        System.out.println(">> En desarrollo.");
                        Varios.continuar(sc);
                        break;
                    case 6:
                        Varios.limpiar();
                        System.out.println(">> Hasta luego, " + usuarioConectado.getNombreUsuario() + ".");
                        usuarioConectado = null;
                        System.out.print(">> Cerrando sesion...");
                        Varios.pausa(1500);
                        Varios.limpiar();
                        logueo(Usuarios, Contrasenas, usuarios, sc);
                    default:
                        Varios.limpiar();
                        System.out.println(">> Opción no válida. Vuelva a intentarlo.");
                        Varios.continuar(sc);
                        break;
                }
            } catch (Exception e) {
                sc.nextLine();
                Varios.limpiar();
                System.out.println(">> Opción no válida. Vuelva a intentarlo.");
                Varios.continuar(sc);
                logueo(Usuarios, Contrasenas, usuarios, sc);
            }
        }
    }

    public static void crearContrasena(File Contrasenas, File Usuarios, List<Usuario> usuarios,
            Usuario usuarioConectado, Scanner sc) {
        Varios.limpiar();
        System.out.print("### Crear passwords ###\n");
        System.out.print(">> Ingrese un nombre para la password:\n>> ");
        String name = sc.nextLine();
        while (name.trim().isEmpty()) {
            Varios.limpiar();
            System.out.print(">> El nombre no puede estar vacio.\nIngrese un nombre para la password:\n>> ");
            name = sc.nextLine();
        }
        Console console = System.console();
        char[] passwordArray = console.readPassword(">> Ingrese la password:    (echo=off)\n>> ");
        String password = new String(passwordArray);
        while (password.trim().isEmpty()) { 
            System.out.print(">> La password no puede estar vacia. \n>> Ingrese la password:\n>> ");
            password = sc.nextLine();
        }
        usuarioConectado.setContrasena(new Contrasena(usuarioConectado.getNombreUsuario(), name, password));
        Varios.limpiar();
        System.out.println("Creando...");
        guardarContrasenas(Contrasenas, usuarios);
        Varios.pausa(500);
        System.out.println(">> Password creada y guardada correctamente.");
        Varios.continuar(sc);
        menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
    }

    public static void eliminarContrasena(File Contrasenas, File Usuarios, List<Usuario> usuarios,
            Usuario usuarioConectado, Scanner sc) {
        Varios.limpiar();
        System.out.print("### Borrar passwords ###\n");
        usuarioConectado.listarContrasenas();
        System.out.print(">> Ingrese el ID de la password a borrar:\n>> ");
        int indice = sc.nextInt();
        sc.nextLine();
        System.out.println(">> Confirma de esta operación? [s / n]");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            usuarioConectado.getContrasenas().remove(indice);
            guardarContrasenas(Contrasenas, usuarios);
            Varios.limpiar();
            System.out.println(">> Password eliminada.");
            Varios.continuar(sc);
            menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
        } else {
            Varios.limpiar();
            System.out.println(">> Eliminación cancelada");
            Varios.continuar(sc);
            menuContrasenas(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
        }
    }

    public static void modificarContrasena(File Contrasenas, File Usuarios, List<Usuario> usuarios,
            Usuario usuarioConectado, Scanner sc) {
        System.out.print(">> Ingrese ID de la password que desea modificar:\n>> ");
        int indice = sc.nextInt();
        sc.nextLine();
        System.out.println(">> [n] Modificar nombre.");
        System.out.print(">> [p] Modificar password.\n>> ");
        try {
            String opcion = sc.nextLine();
            switch (opcion) {
                case "n":
                    System.out.print(">> Ingrese nuevo nombre:\n>> ");
                    String nombre = sc.nextLine();
                    usuarioConectado.getContrasenas().get(indice).setNombre(nombre);
                    System.out.println("Modificando...");
                    Varios.pausa(500);
                    System.out.println(">> Nombre modificado correctamente.");
                    Varios.continuar(sc);
                    break;
                case "p":
                    System.out.print(">> Ingrese nueva contrasena:\n>> ");
                    String contrasena = sc.nextLine();
                    usuarioConectado.getContrasenas().get(indice).setContrasena(contrasena);
                    System.out.println("Modificando...");
                    Varios.pausa(500);
                    System.out.println(">> Password modificada correctamente.");
                    Varios.continuar(sc);
                    break;
                default:
                    System.out.println(">> Opción inválida.");
                    Varios.continuar(sc);
                    modificarContrasena(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
                    break;
            }
        } catch (Exception e) {
            System.out.println(">> Opción inválida.");
            Varios.continuar(sc);
            modificarContrasena(Contrasenas, Usuarios, usuarios, usuarioConectado, sc);
        }
        guardarContrasenas(Usuarios, usuarios);
        menuContrasenas(Usuarios, Usuarios, usuarios, usuarioConectado, sc);
    }

    public static void guardarUsuarios(File Usuarios, List<Usuario> usuarios) {
        try {
            FileWriter fw = new FileWriter(Usuarios);
            for (Usuario usuario : usuarios) {
                String encripted = Varios.encriptar(usuario.getNombreUsuario() + ":" + usuario.getContrasenaUsuario());
                fw.write(encripted);
            }
            fw.close();
        } catch (IOException e) {
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
                String decripted = Varios.desencriptar(datos);
                atributosUsuario = decripted.split(":");
                listaProvisoria.add(new Usuario(atributosUsuario[0], atributosUsuario[1]));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return listaProvisoria;
    }

    public static void guardarContrasenas(File Contrasenas, List<Usuario> usuarios) {
        try {
            FileWriter fw = new FileWriter(Contrasenas);
            for (Usuario usuario : usuarios) {
                for (Contrasena contrasena : usuario.getContrasenas()) {
                    String encripted = Varios.encriptar (usuario.getNombreUsuario() + ":" + contrasena.getNombre() + ":"+ contrasena.getContrasena());
                    fw.write(encripted);
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static List<Contrasena> traerContrasenas(File Contrasenas) {
        List<Contrasena> provisoriaContrasenas = new ArrayList<Contrasena>();
        String[] datosContrasena = new String[3];
        try {
            Scanner sc = new Scanner(Contrasenas);
            while (sc.hasNextLine()) {
                String datos = sc.nextLine();
                String decripted = Varios.desencriptar (datos);
                datosContrasena = decripted.split(":");
                provisoriaContrasenas.add(new Contrasena(datosContrasena[0], datosContrasena[1], datosContrasena[2]));
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return provisoriaContrasenas;
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

        for (Contrasena contrasena : traerContrasenas(Contrasenas)) {
            for (Usuario usuario : usuarios) {
                if (contrasena.getId().equals(usuario.getNombreUsuario())) {
                    usuario.setContrasena(contrasena);
                }
            //System.out.println(usuario.getNombreUsuario()+":"+usuario.getContrasenaUsuario());
            //System.out.println(contrasena.getId()+":"+contrasena.getNombre()+":"+contrasena.getContrasena());

            }
        }
        Scanner sc = new Scanner(System.in);
        Varios.continuar(sc);
        logueo(Usuarios, Contrasenas, usuarios, sc);
    }
}
