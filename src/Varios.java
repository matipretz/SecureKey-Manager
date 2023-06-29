import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class Varios {

    static void verificarDirectorios() { // Verifica la existencia de la ruta necesaria y la crea si falta.
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

    static void mensaje() { // Mensaje de bienvenida.
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

        System.out.print(ajustar(getMacAddress()) + "\n");
        pausa(100);

        System.out.print("###\n>> Cargando...");
        pausa(2000);

        limpiar();
    }

    static void pausa(int ms) { // Pausa la ejecucion durante x milisegundos.
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void limpiar() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    static String ajustar(String input) {
        int desiredLength = 32;
        if (input.length() < desiredLength) { // Agregar caracteres al final.
            while (input.length() < desiredLength) {
                input += "A7fG8Kp3dRbT9qY5hN2wX6jZ1cV4mS0l";
            }
        } else if (input.length() > desiredLength) { // Cortar caracteres al final.
            input = input.substring(0, desiredLength);
        }
        return input;
    }

    public static String getMacAddress() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localhost);
            byte[] macBytes = networkInterface.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < macBytes.length; i++) {
                sb.append(String.format("%02X%s", macBytes[i], (i < macBytes.length - 1) ? "-" : ""));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    static String getHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    static String ENCRYPT_KEY = ajustar(getHash(getMacAddress()));

    static String encriptar(String text) {
        try {
            SecretKey aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.out.println("Error al cifrar la contraseña: " + e.getMessage());
            return null;
        }
    }

    static String desencriptar(String encryptedText) {
        try {
            SecretKey aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Error al descifrar la contraseña: " + e.getMessage());
            return null;
        }
    }
}