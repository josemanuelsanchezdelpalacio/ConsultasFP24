package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.Implementaciones.DatosInsertarImplement;
import classes.Insertar.Implementaciones.Implementacion;
import com.google.gson.Gson;
import entities.ImplementEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class InsertarImplementaciones {
    public static void insertarImplementaciones() {
        Path p = Path.of("src/main/resources/jsonTablas/insertImplement.json");
        DatosInsertarImplement implementacionesInsertar;
        String txtJson;

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                txtJson = Files.readString(p);
                Gson gson = new Gson();
                implementacionesInsertar = gson.fromJson(txtJson, DatosInsertarImplement.class);

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (Implementacion datos : implementacionesInsertar.getImplementations()) {
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
