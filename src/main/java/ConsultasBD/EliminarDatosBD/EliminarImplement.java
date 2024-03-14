package ConsultasBD.EliminarDatosBD;

import Singleton.EmfSingleton;
import classes.Eliminar.DatosEliminarFavourite;
import classes.Eliminar.DatosEliminarImplement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.FavouriteEntity;
import entities.ImplementEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EliminarImplement {

    public static ArrayList<DatosEliminarImplement> implementacionesEliminar = new ArrayList<>();
    public static void eliminarImplementacion() {
        Path p = Path.of("src/main/resources/jsonEliminar/eliminarImplementacion.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                implementacionesEliminar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosEliminarImplement>>() {
                }.getType());
                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosEliminarImplement datos : implementacionesEliminar) {
                    Query query = em.createQuery("select i from ImplementEntity i where i.idTechnology = :idTechnology and i.idProject = :idProject ");
                    query.setParameter("idProject", datos.getIdProject());
                    query.setParameter("idTechnology", datos.getIdTechnology());
                    List<ImplementEntity> result = query.getResultList();
                    if (result.isEmpty()) {
                        System.out.println("La implementacion no existe");
                    } else {
                        em.remove(result.get(0));
                        System.out.println("Implementación eliminada");
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
