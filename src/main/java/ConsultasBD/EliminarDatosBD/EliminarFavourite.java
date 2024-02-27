package ConsultasBD.EliminarDatosBD;

import Singleton.EmfSingleton;
import classes.Eliminar.DatosEliminarColaboraciones;
import classes.Eliminar.DatosEliminarFavourite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.CollaborationEntity;
import entities.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EliminarFavourite {

    public static ArrayList<DatosEliminarFavourite> favoritosEliminar = new ArrayList<>();
    public static void eliminarFavorito() {
        Path p = Path.of("src/main/resources/eliminarFavoritos.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                favoritosEliminar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosEliminarFavourite>>() {
                }.getType());
                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosEliminarFavourite datos : favoritosEliminar) {
                    Query query = em.createQuery("select f from FavouriteEntity f where f.idUser = :idUser and f.idProject = :idProject ");
                    query.setParameter("idProject", datos.getIdProject());
                    query.setParameter("idUser", datos.getIdUser());
                    List<FavouriteEntity> result = query.getResultList();
                    if (result.isEmpty()) {
                        System.out.println("El favorito no existe");
                    } else {
                        em.remove(result.get(0));
                        System.out.println("Favorito eliminado");
                    }
                }
                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}