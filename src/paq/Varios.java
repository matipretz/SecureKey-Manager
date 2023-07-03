package paq;

import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * La clase Varios contiene diversas funciones auxiliares utilizadas en el programa principal.
 */
public class Varios {

    /**
     * Verifica la existencia del directorio "data" y lo crea si no existe.
     */
    public static void verificarDirectorios() {
        String directory = "data";
        File file = new File(directory);
        if (!file.exists()) {
            boolean created = file.mkdir();
            if (!created) {
                System.out.print(">> Error creando carpeta: " + directory);
            }
        }
    }

    /**
     * Muestra un mensaje en pantalla y espera a que el usuario presione Enter para
     * continuar.
     *
     * @param sc El objeto Scanner utilizado para leer la entrada del usuario.
     */
    public static void continuar(Scanner sc) {
        System.out.println(">> Presione [ENTER] para continuar.");
        sc.nextLine();
    }

    /**
     * Verifica si un nombre de usuario dado ya existe en la lista de usuarios.
     *
     * @param usuarios      La lista de usuarios existentes.
     * @param nombreUsuario El nombre de usuario a verificar.
     * @return true si el nombre de usuario ya existe, false en caso contrario.
     */
    public static boolean existeUsuario(List<Usuario> usuarios, String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra un mensaje de presentación en la consola.
     * @param sc El objeto Scanner utilizado para leer la entrada del usuario.

     */
    public static void mensaje(Scanner sc) {
        limpiar();
        System.out.print(
                "###### CaC 4.0-23438-2023 SEM1 ######\nBienvenido a SecureKey Manager 0.2\nTu gestor de passwords de confianza\n");
        pausa(100);

        System.out.print("CaC 4.0-23438-2023 SEM1\n");
        pausa(100);

        System.out.print("Alumno: Matías Martín Murad Pretz DNI: 33.366.158\n");
        pausa(100);

        System.out.print("Docente: Gonzalo F. Rubé, Tutora: Zoraida Flores\n");
        pausa(100);

        // System.out.print("Llave: " + Varios.ENCRYPT_KEY + "\n");
        pausa(100);

        continuar(sc);

        limpiar();
    }

    /**
     * Pausa la ejecución del programa durante el tiempo especificado en
     * milisegundos.
     *
     * @param ms El tiempo de pausa en milisegundos.
     */
    public static void pausa(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Limpia la consola para una mejor presentación.
     */
    public static void limpiar() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * La clave utilizada para encriptar y desencriptar texto.
     */
    public static String ENCRYPT_KEY = "A7fG8Kp3dRbT9qY5hN2wX6jZ1cV4mS0l";

    /**
     * Encripta el texto proporcionado utilizando el algoritmo AES y la clave de
     * encriptación.
     *
     * @param texto El texto a encriptar.
     * @return El texto encriptado.
     */
    public static String encriptar(String texto) {
        try {
            SecretKey aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher cifrador = Cipher.getInstance("AES");
            cifrador.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] textoCifrado = cifrador.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(textoCifrado);
        } catch (Exception e) {
            System.out.println("Error al encriptar el texto: " + e.getMessage());
            return null;
        }
    }

    /**
     * Desencripta el texto cifrado utilizando el algoritmo AES y la clave de
     * encriptación.
     *
     * @param textoCifrado El texto cifrado a desencriptar.
     * @return El texto desencriptado.
     */
    public static String desencriptar(String textoCifrado) {
        try {
            SecretKey aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher cifrador = Cipher.getInstance("AES");
            cifrador.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] textoBytes = Base64.getDecoder().decode(textoCifrado);
            byte[] textoDesencriptado = cifrador.doFinal(textoBytes);
            return new String(textoDesencriptado);
        } catch (Exception e) {
            System.out.println("Error al desencriptar el texto: " + e.getMessage());
            return null;
        }
    }
}