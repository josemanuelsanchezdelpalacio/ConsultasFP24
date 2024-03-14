package ConsultasBD.EliminarDatosBD;

import Singleton.EmfSingleton;
import classes.Eliminar.DatosEliminarFavourite;
import classes.Eliminar.DatosEliminarTechnology;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.FavouriteEntity;
import entities.TechnologyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EliminarTechnology {

    public static ArrayList<DatosEliminarTechnology> technologyEliminar = new ArrayList<>();
    public static void eliminarFavorito() {
        Path p = Path.of("src/main/resources/jsonEliminar/eliminarTechnology.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                technologyEliminar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosEliminarTechnology>>() {
                }.getType());
                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosEliminarTechnology datos : technologyEliminar) {
                    Query query = em.createQuery("select t from TechnologyEntity t where t.tag = :tag and t.techName = :techName ");
                    query.setParameter("tag", datos.getTag());
                    query.setParameter("techName", datos.getTechName());
                    List<TechnologyEntity> result = query.getResultList();
                    if (result.isEmpty()) {
                        System.out.println("La tecnología no existe");
                    } else {
                        em.remove(result.get(0));
                        System.out.println("Tecnología eliminada");
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