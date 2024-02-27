package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.DatosInsertarProject;
import classes.Proyecto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.ProjectEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsertarProyectos {

//    public static ArrayList<DatosInsertarProject> proyectosInsertar = new ArrayList<>();

    public static void insertarProyectos() {
        Path p = Path.of("src/main/resources/jsonTablas/insertProject.json");
        DatosInsertarProject proyectosInsertar;
        String txtJson;

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                txtJson = Files.readString(p);
                Gson gson = new Gson();
                proyectosInsertar = gson.fromJson(txtJson, DatosInsertarProject.class);

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (Proyecto datos : proyectosInsertar.getProyectos()) {
                    if(existeProyecto(em,datos.getTitle())){
                        System.out.println("Proyecto con título " + datos.getTitle() + " ya existe");
                    }else{
                        ProjectEntity projectEntity = new ProjectEntity();
                        projectEntity.setTitle(datos.getTitle());
                        projectEntity.setLogo(datos.getLogo());
                        projectEntity.setWeb(datos.getWeb());
                        projectEntity.setProjectDescription(datos.getDescripcion());
                        projectEntity.setState(datos.getState());
                        projectEntity.setInitDate(datos.getInitDate());
                        projectEntity.setEndDate(datos.getEndDate());

                        em.persist(projectEntity);
                        System.out.println("Proyecto con título " + datos.getTitle() + " ha sido insertado correctamente");
                    }
                }

                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }

    private static boolean existeProyecto(EntityManager em, String title) {
        Query query = em.createQuery("SELECT COUNT(p) FROM ProjectEntity p WHERE p.title = :title");
        query.setParameter("title", title);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}

