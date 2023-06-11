package src.Jpass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Jcrud {

    public static void createFile(String fileName, String fileContent) {
        System.out.println(">>Creando archivo " + fileName + ".");
        boolean fileCreated = false;
        File file = new File(fileName);
        try {
            fileCreated = file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileContent);
            fileWriter.close();
            if(fileCreated) {
                System.out.println(">> Archivo creado.");
            } else {
                System.out.println(">> Archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println(">>Excepcion al crear archivo: " + e);
        }
    }

    public void readFile(String fileName) {
        System.out.println(">>Leyendo archivo " + fileName + ".");
        File file = new File(fileName);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> Excepcion al abrir archivo: " + e + ".");
        }
    }

    public void deleteFile(String fileName) {
        System.out.println(">> Borrando " + fileName + ".");
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println(">> Archivo borrado correctamente.");
        } else {
            System.out.println(">> No se pudo borrar.");
        }
    }
}
