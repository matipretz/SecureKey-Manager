import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.management.openmbean.InvalidKeyException;

 class Toys {
     static void chkDirs() { //Verifica la existencia de la ruta necesaria y la crea si falta.
        String[] directories = {"data"};
        for (String directory : directories) {
            File file = new File(directory);
            if (!file.exists()) {
                boolean created = file.mkdirs();
                if (!created) {
                    System.out.print(">> Error creando carpeta: " + directory);
                }
            }
        }
    }
    static void loading() { // Mensaje de bienvenida.
        clear();
        System.out.print("###\nSecureKey Manager 0.2\nCaC 4.0-23438-2023 SEM1\nAlumno: Matías Martín Murad Pretz\n33.366.158\nDocente: Gonzalo F. Rubé\nTutora: Zoraida Flores\n###\n");
        pausa(2000);
        clear();
    }
   static void pausa(int ms) { // Pausa la ejecucion durante x milisegundos.
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    static  void clear() { // Limpia la consola.
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static  String ajustar(String input) {
        int desiredLength = 32;
        if (input.length() < desiredLength) { // Agregar caracteres al final.
            while (input.length() < desiredLength) {
                input += "@";
            }
        } 
        else if (input.length() > desiredLength) {  // Cortar caracteres al final.
            input = input.substring(0, desiredLength);
        }
        return input;
    }
    static String ENCRYPT_KEY = (ajustar(CRUD.llave + CRUD.valor));

    static String encrypt(String text) {
        try {
            SecretKey aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | java.security.InvalidKeyException e) {
            System.out.println("Error al cifrar la contraseña: " + e.getMessage());
            return null;
        }
    }
    
    static String decrypt(String encryptedText) {
        try {
            SecretKey aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | java.security.InvalidKeyException e) {
            System.out.println("Error al descifrar la contraseña: " + e.getMessage());
            return null;
        }
    }
    static boolean verificar(String llave, String valor, String FILE_PATH) { // Verifica que la llave y/o el valor en FILE_PATH existan y no sean null.
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");
                String llaveExiste = parts[0];
                String valorExiste = parts[1];                
                if (llave != null && llaveExiste.equals(llave)) {
                    return true;
                }                
                if (valor != null && valorExiste.equals(valor)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> Error al abrir el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">> Error al cerrar el Scanner: " + e.getMessage());
        }        
        return false;
    }

}