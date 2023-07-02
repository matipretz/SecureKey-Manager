import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class Varios {

    static void verificarDirectorios() { 
        String directory = "data";
        File file = new File(directory);
        if (!file.exists()) {
            boolean created = file.mkdir();
            if (!created) {
                System.out.print(">> Error creando carpeta: " + directory);
            }
        }
    }

    static void continuar(Scanner sc) {
        System.out.println(">> Presione [ENTER] para continuar.");
        sc.nextLine();
    }

    static boolean existeUsuario(List<Usuario> usuarios, String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return true;
            }
        }
        return false;
    }

    static void mensaje() {
        limpiar();
        System.out.print("###\nSecureKey Manager 0.2\n");
        pausa(100);

        System.out.print("CaC 4.0-23438-2023 SEM1\n");
        pausa(100);

        System.out.print("Alumno: Matías Martín Murad Pretz\n");
        pausa(100);

        System.out.print("33.366.158\n");
        pausa(100);

        System.out.print("Docente: Gonzalo F. Rubé\n");
        pausa(100);

        System.out.print("Tutora: Zoraida Flores\n");
        pausa(100);

        System.out.print("Llave: " + Varios.ENCRYPT_KEY + "\n");
        pausa(100);

        System.out.print("###\n>> Cargando...");
        pausa(2000);

        limpiar();
    }

    static void pausa(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void limpiar() {
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

    public static String ENCRYPT_KEY = "A7fG8Kp3dRbT9qY5hN2wX6jZ1cV4mS0l";

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