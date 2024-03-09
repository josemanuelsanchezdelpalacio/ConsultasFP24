package ConsultasBD.LeerDatos.LeerTablaCompleta;

import classes.Insertar.Proyecto.DatosInsertarProject;
import classes.Insertar.Proyecto.Proyecto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.ProjectEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerProyectos {

    public static void leerProyectos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            List<ProjectEntity> proyectosLeer = em.createQuery("SELECT p FROM ProjectEntity p", ProjectEntity.class)
                    .getResultList();

            for (ProjectEntity proyecto : proyectosLeer) {
                System.out.println("IdProject: " + proyecto.getTitle());
                System.out.println("Logo: " + proyecto.getLogo());
                System.out.println("Web: " + proyecto.getWeb());
                System.out.println("ProjectDescription: " + proyecto.getProjectDescription());
                System.out.println("State: " + proyecto.getState());
                System.out.println("InitDate: " + proyecto.getInitDate());
                System.out.println("EndDate: " + proyecto.getEndDate());
            }
        } catch (Exception e) {
            System.out.println("Error al leer los proyectos desde la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}