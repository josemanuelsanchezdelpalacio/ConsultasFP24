package ConsultasBD.LeerDatos;

import classes.Insertar.Favourites.Favourite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LeerFavoritos {

    public static void leerFavoritos() {
        ArrayList<Favourite> favoritosLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertFavourite.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                favoritosLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<Favourite>>() {
                }.getType());

                for (Favourite dato : favoritosLeer) {
                    System.out.println("IdProject: " + dato.getIdProject());
                    System.out.println("IdUser: " + dato.getIdUser());
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}