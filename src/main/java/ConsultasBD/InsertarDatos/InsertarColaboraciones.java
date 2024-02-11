package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.DatosInsertarCollaboration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.CollaborationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsertarColaboraciones {

    public static ArrayList<DatosInsertarCollaboration> colaboracionesInsertar = new ArrayList<>();

    public static void insertarColaboraciones() {
        Path p = Path.of("src/main/resources/jsonTablas/insertCollaboration.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                colaboracionesInsertar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarCollaboration>>() {
                }.getType());

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosInsertarCollaboration datos : colaboracionesInsertar) {
                    if (!existeColaboracion(em, datos.getIdProject(), datos.getIdUser(), datos.getIdFamily())) {
                        CollaborationEntity collaborationEntity = new CollaborationEntity();
                        collaborationEntity.setIdProject(datos.getIdProject());
                        collaborationEntity.setIdUser(datos.getIdUser());
                        collaborationEntity.setIdFamily(datos.getIdFamily());
                        collaborationEntity.setManager(datos.isManager());

                        em.persist(collaborationEntity);
                        System.out.println("Colaboración con ID de proyecto " + datos.getIdProject() + " ha sido insertada correctamente");
                    } else {
                        System.out.println("Colaboración con ID de proyecto " + datos.getIdProject() + " ya existe, no se insertará.");
                    }
                }

                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }

    private static boolean existeColaboracion(EntityManager em, int idProject, int idUser, int idFamily) {
        return em.createQuery("SELECT COUNT(c) FROM CollaborationEntity c WHERE c.idProject = :idProject AND c.idUser = :idUser AND c.idFamily = :idFamily", Long.class)
                .setParameter("idProject", idProject)
                .setParameter("idUser", idUser)
                .setParameter("idFamily", idFamily)
                .getSingleResult() > 0;
    }
}