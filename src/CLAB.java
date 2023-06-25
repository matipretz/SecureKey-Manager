import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CLAB {
    static final String FILE_PATH = "data/" + Usuario.nombreUsuario; // Ruta del archivo donde se guardarán las passwords

    static void crear(Scanner scanner) {
        Varios.limpiar();
        System.out.print("### Crear passwords ###\n");
        System.out.print(">> Ingrese un nombre para la password:\n>> ");
        String name = scanner.nextLine();
        while (name.trim().isEmpty()) { // Validar que el campo name no esté vacio
            Varios.limpiar();
            System.out.print(">> El nombre no puede estar vacio.\nIngrese un nombre para la password:\n>> ");
            name = scanner.nextLine();
        }
        while (Varios.verificar(name, null, CLAB.FILE_PATH)) {
            Varios.limpiar();
            System.out.print(">> El nombre de usuario ya está en uso.\nIngrese un nombre para la password:\n>> ");
            name = scanner.nextLine();
        }
        System.out.print(">> Ingrese la password:\n>> ");
        String password = scanner.nextLine();
        while (password.trim().isEmpty()) { // Validar que el campo password no esté vacio
            System.out.print(">> La password no puede estar vacia. Ingrese la password:\n>> ");
            password = scanner.nextLine();
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(CLAB.FILE_PATH, true))) {
            Varios.limpiar();
            System.out.println(">> Creando password.");
            Varios.pausa(500);
            String encripted = Varios.encriptar(name + ":" + password);
            writer.println(encripted);
            System.out.println(">> Password creada y guardada correctamente.\n>> Presione [ENTER] para continuar.");
            scanner.nextLine();
        } catch (IOException e) {
            System.out.println(">> Error al crear o guardar la password: " + e.getMessage());
            Varios.pausa(1500);
        }
    }

    static void listar() {
        Varios.pausa(500);
        try (Scanner fileScanner = new Scanner(new File(CLAB.FILE_PATH))) { // Intenta crear un archivo tomando los
                                                                            // datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { // Recorre el registro.
                String line = fileScanner.nextLine(); // Linea por linea.
                String decripted = Varios.desencriptar(line);
                System.out.println(decripted.split(":")[0]); // Dividir la linea por su separador e imprimir la primera
                                                             // parte.
                Varios.pausa(50);
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de passwords. Error:" + e);
            Varios.pausa(1500);
            Menus.principal();
        }
    }

    static void leer(Scanner scanner) {
        Varios.limpiar();
        System.out.print("### Leer passwords ###\n");
        listar();
        System.out.print(">> Ingrese el nombre de la password: ");
        String name = scanner.nextLine(); // Toma el dato buscado por el usuario.
        try (Scanner fileScanner = new Scanner(new File(CLAB.FILE_PATH))) { // Intenta crear un archivo tomando los
                                                                            // datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { // Mientras tenga otra linea de datos.
                String line = fileScanner.nextLine(); // Crea un string para la linea.
                String decripted = Varios.desencriptar(line);

                String[] parts = decripted.split(":"); // Divide la linea por un separador.
                String passwordName = parts[0]; // Crea un string para la primera parte de la linea.
                String password = parts[1]; // Crea un string para la segunda parte de la linea.

                if (passwordName.equals(name)) { // Si la primera parte de la linea coincide con el dato ingresado:
                    System.out.println(">> Password: " + password + "\n>> Presione [ENTER] para continuar."); // Muestra
                                                                                                              // la
                                                                                                              // segunda
                                                                                                              // parte
                                                                                                              // de la
                                                                                                              // linea.
                    scanner.nextLine(); // Consume la siguiente linea del scanner.
                    return;
                }
            }
            System.out.println(">> No se encuentran passwords guardadas.\n>> Presione [ENTER] para continuar.");
            scanner.nextLine();
            Menus.principal();
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de passwords. Error:" + e);
            Varios.pausa(1500);
            Menus.principal();
        }
    }

    static void actualizar(Scanner scanner) {
        Varios.limpiar();
        System.out.print("### Editar passwords ###\n");
        listar();
        System.out.print(">> Ingrese el nombre de la password a editar: ");
        String name = scanner.nextLine(); // Toma el dato buscado por el usuario.
        List<String> lines = new ArrayList<>(); // Crea una lista.

        try (Scanner fileScanner = new Scanner(new File(CLAB.FILE_PATH))) { // Intenta crear un archivo tomando los
                                                                            // datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { // Mientras tenga otra linea de datos.
                String line = fileScanner.nextLine(); // Crea un string para la linea.
                String[] parts = line.split(":"); // Divide la linea por un separador.
                String passwordName = parts[0]; // Crea un string para la primera parte de la linea.
                if (passwordName.equals(name)) { // Si la primera parte de la linea coincide con el dato ingresado:
                    System.out.print(">> Ingrese la nueva password: ");
                    String newPassword = scanner.next();// Toma el nuevo valor.
                    line = passwordName + ":" + newPassword; // Agregar la linea actualizada a la lista.
                }
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de passwords. Error: " + e);
            Varios.pausa(1500);
            Menus.principal();
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(CLAB.FILE_PATH))) {
            for (String line : lines) { // Recorrer la lista con un bucle for each.
                writer.println(line); // Escribe cada linea, editando de esta forma la linea que contiene el par
                                      // buscado.
            }
            System.out.println(">> Password: " + name + " actualizada.\nPresione [ENTER] para continuar."); // Muestra
                                                                                                            // la
                                                                                                            // segunda
                                                                                                            // parte de
                                                                                                            // la linea.
            scanner.nextLine(); // Consume la siguiente linea del scanner.
        } catch (IOException e) {
            System.out.println(">> Error al editar la password: " + e.getMessage());
            Varios.pausa(1500);
        }
    }

    static void borrar(Scanner scanner) {
        Varios.limpiar();
        System.out.print("### Borrar passwords ###\n");
        listar();
        System.out.print(">> Ingrese el nombre de la password a borrar: ");
        String name = scanner.nextLine(); // Toma el dato buscado por el usuario.
        List<String> lines = new ArrayList<>(); // Crea una lista.
        try (Scanner fileScanner = new Scanner(new File(CLAB.FILE_PATH))) { // Intenta crear un archivo tomando los
                                                                            // datos de la ruta especificada para:
            while (fileScanner.hasNextLine()) { // Mientras tenga otra linea de datos.
                String line = fileScanner.nextLine(); // Crea un string para la linea.
                String[] parts = line.split(":"); // Divide la linea por un separador.
                String passwordName = parts[0]; // Crea un string para la primera parte de la linea.
                if (!passwordName.equals(name)) { // Si la primera parte de la linea no coincide con el dato ingresado:
                    lines.add(line); // Agregar la linea a la lista.
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> No se encontró el archivo de passwords. Error:" + e);
            Varios.pausa(1500);
            Menus.principal();
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) { // Intenta crear un archivo en modo
                                                                                // escritura en la ruta establecida
                                                                                // para:
            for (String line : lines) { // Recorrer la lista con un bucle for each.
                writer.println(line);// Escribe cada linea, borrando de esta forma la linea que contiene el par
                                     // buscado.
            }
            System.out.println(">> Password " + name + " borrada correctamente.\nPresione [ENTER] para continuar.");
            scanner.nextLine(); // Consume la siguiente linea del scanner.
        } catch (IOException e) {
            System.out.println(">> Error al borrar la password: " + e.getMessage());
            Varios.pausa(1500);
            Menus.principal();
        }
    }
}