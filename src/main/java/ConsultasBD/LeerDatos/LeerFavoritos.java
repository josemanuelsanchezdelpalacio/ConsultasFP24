package ConsultasBD.LeerDatos;

import classes.Insertar.DatosInsertarFamily;
import classes.Insertar.DatosInsertarFavourite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LeerFavoritos {

    public static void leerFavoritos() {
        ArrayList<DatosInsertarFavourite> favoritosLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertFavourite.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                favoritosLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarFavourite>>() {
                }.getType());

                for (DatosInsertarFavourite dato : favoritosLeer) {
                    System.out.println("IdProject: " + dato.getIdProject());
                    System.out.println("IdUser: " + dato.getIdUser());
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}