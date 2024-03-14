package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.Favourites.DatosInsertarFavourite;
import classes.Insertar.Favourites.Favourite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsertarFavoritos {

    public static void insertarFavoritos() {
        Path p = Path.of("src/main/resources/jsonInsertar/insertFavourite.json");
        DatosInsertarFavourite favouritesInsertar;
        String txtJson;

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                txtJson = Files.readString(p);
                Gson gson = new Gson();
                favouritesInsertar = gson.fromJson(txtJson, DatosInsertarFavourite.class);

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (Favourite datos : favouritesInsertar.getFavourites()) {
                    if (!existeFavorito(em, datos.getIdProject(), datos.getIdUser())) {
                        FavouriteEntity favouriteEntity = new FavouriteEntity();
                        favouriteEntity.setIdProject(datos.getIdProject());
                        favouriteEntity.setIdUser(datos.getIdUser());

                        em.persist(favouriteEntity);
                        System.out.println("Favorito con ID de proyecto " + datos.getIdProject() + " ha sido insertado correctamente");
                    } else {
                        System.out.println("Favorito con ID de proyecto " + datos.getIdProject() + " ya existe, no se insertará.");
                    }
                }

                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }

    private static boolean existeFavorito(EntityManager em, int idProject, int idUser) {
        Query query = em.createQuery("SELECT COUNT(f) FROM FavouriteEntity f WHERE f.idProject = :idProject AND f.idUser = :idUser");
        query.setParameter("idProject", idProject);
        query.setParameter("idUser", idUser);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
