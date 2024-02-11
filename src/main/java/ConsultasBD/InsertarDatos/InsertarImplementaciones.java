package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.DatosInsertarImplement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.ImplementEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsertarImplementaciones {

    public static ArrayList<DatosInsertarImplement> implementacionesInsertar = new ArrayList<>();

    public static void insertarImplementaciones() {
        Path p = Path.of("src/main/resources/jsonTablas/insertImplement.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                implementacionesInsertar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarImplement>>() {
                }.getType());

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosInsertarImplement datos : implementacionesInsertar) {
                    if (!existeImplementacion(em, datos.getIdProject(), datos.getIdTechnology())) {
                        ImplementEntity implementEntity = new ImplementEntity();
                        implementEntity.setIdProject(datos.getIdProject());
                        implementEntity.setIdTechnology(datos.getIdTechnology());

                        em.persist(implementEntity);
                        System.out.println("Implementación con ID de proyecto " + datos.getIdProject() + " ha sido insertada correctamente");
                    } else {
                        System.out.println("Implementación con ID de proyecto " + datos.getIdProject() + " ya existe, no se insertará.");
                    }
                }

                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }

    private static boolean existeImplementacion(EntityManager em, int idProject, int idTechnology) {
        Query query = em.createQuery("SELECT COUNT(i) FROM ImplementEntity i WHERE i.idProject = :idProject AND i.idTechnology = :idTechnology");
        query.setParameter("idProject", idProject);
        query.setParameter("idTechnology", idTechnology);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
