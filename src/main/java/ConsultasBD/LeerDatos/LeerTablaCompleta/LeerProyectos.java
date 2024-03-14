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

    public static String leerProyectos() {

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        String jsonSalidaProjects = "";
        Gson gson = new Gson();

        try {

            //Obtengo la lista de proyectos
            List<ProjectEntity> proyectosLeer = em.createQuery("SELECT p FROM ProjectEntity p", ProjectEntity.class)
                    .getResultList();

            //Creo el json de salidfa
            jsonSalidaProjects = gson.toJson(proyectosLeer);
        } catch (Exception e) {
            System.out.println("Error al leer los proyectos desde la base de datos");

        } finally {
            em.close();
            emf.close();
        }

        //Devuelvo el json
        return jsonSalidaProjects;
    }
}