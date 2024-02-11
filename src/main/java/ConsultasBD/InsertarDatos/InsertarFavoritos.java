package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.DatosInsertarFavourite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsertarFavoritos {

    public static ArrayList<DatosInsertarFavourite> favoritosInsertar = new ArrayList<>();

    public static void insertarFavoritos() {
        Path p = Path.of("src/main/resources/jsonTablas/insertFavourite.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                favoritosInsertar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarFavourite>>() {
                }.getType());

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosInsertarFavourite datos : favoritosInsertar) {
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
