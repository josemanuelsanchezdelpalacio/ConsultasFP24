package ConsultasBD.LeerDatos.LeerTablaCompleta;

import classes.Insertar.Favourites.Favourite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerFavoritos {

    public static String leerFavoritos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        String jsonSalidaFavoritos = "";
        Gson gson = new Gson();

        try {
            List<FavouriteEntity> favoritosLeer = em.createQuery("SELECT fav FROM FavouriteEntity fav", FavouriteEntity.class).getResultList();

            for (FavouriteEntity favorito : favoritosLeer) {
                System.out.println("IdProject: " + favorito.getIdProject());
                System.out.println("IdUser: " + favorito.getIdUser());
            }
            jsonSalidaFavoritos = gson.toJson(favoritosLeer);
        } catch (Exception e) {
            System.out.println("Error al leer los favoritos desde la base de datos");

        } finally {
            em.close();
            emf.close();
        }
        return jsonSalidaFavoritos;
    }
}