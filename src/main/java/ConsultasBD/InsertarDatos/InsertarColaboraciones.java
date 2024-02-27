package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.Colaboraciones.Colaboracion;
import classes.Insertar.Colaboraciones.DatosInsertarCollaboration;
import com.google.gson.Gson;
import entities.CollaborationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import libs.FicheroEscribible;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InsertarColaboraciones {

    public static void insertarColaboraciones() {
        Path p = Path.of("src/main/resources/jsonTablas/insertCollaboration.json");
        DatosInsertarCollaboration colaboracionesInsertar;
        String txtJson;

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                txtJson = Files.readString(p);
                Gson gson = new Gson();
                colaboracionesInsertar = gson.fromJson(txtJson, DatosInsertarCollaboration.class);

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (Colaboracion datos : colaboracionesInsertar.getCollaborations()) {
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