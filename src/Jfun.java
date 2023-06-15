import java.io.File;

public class Jfun {
    public static void chkDirs() { //Verifica la existencia de la ruta necesaria y la crea si falta.
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
    public static void loading() { // Mensaje de bienvenida.
        clear();
        System.out.print("###\nSecureKey Manager 0.2\nCaC 4.0-23438-2023 SEM1\nAlumno: Matías Martín Murad Pretz\n33.366.158\nDocente: Gonzalo F. Rubé\nTutora: Zoraida Flores\n###\n");
        pausa(2000);
        clear();
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


public static void cursor() {
    Thread cursorThread = new Thread(() -> {
            boolean showCursor = true;
            while (true) {
                try {
                    Thread.sleep(500); // Pausa de 500 milisegundos
                    if (showCursor) {
                        System.out.print("\b");
                    } else {
                        System.out.print("_");
                    }
                    showCursor = !showCursor;
                    System.out.flush();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        cursorThread.start();
    
}
}