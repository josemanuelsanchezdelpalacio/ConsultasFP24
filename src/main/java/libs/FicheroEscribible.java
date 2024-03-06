package libs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class FicheroEscribible {

    public static boolean ficheroEscribible(Path p){
        boolean ficheroOK = false;
        if(Files.exists(p)){
            if (Files.isWritable(p)) {
                ficheroOK = true;
            }
        }
        return ficheroOK;
    }

    public static boolean ficheroLegible(Path p) {
        boolean ficheroOK = false;
        if (Files.exists(p)) {
            if (Files.isReadable(p)) {
                ficheroOK = true;
            }
        }
        return ficheroOK;
    }

    public static String leerFichero(Path p) {
        StringBuilder texto = new StringBuilder();
        if (Files.exists(p) && !Files.isDirectory(p)) {
            try {
                for (String s : Files.readAllLines(p)) {
                    texto.append(s);
                }
            } catch (FileNotFoundException e) {
                System.out.println("No existe");
            } catch (MalformedInputException e) {
                System.out.println("Comprueba que la codificación del archivo sea UTF-8");
            } catch (NoSuchFileException e) {
                System.out.println("El archivo no existe");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto.toString();
    }
}
