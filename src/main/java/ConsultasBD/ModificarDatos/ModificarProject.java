package ConsultasBD.ModificarDatos;

import classes.Modificar.DatosModificarProject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conexiones.ConexionMySQL;
import entities.EntityEntity;
import entities.ProjectEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.nio.file.Path;
import java.sql.*;

import static libs.FicheroEscribible.leerFichero;

public class ModificarProject {
    public static void modificar() {
        Path p = Path.of("src/main/resources/modifProject.json");
        //leo el archivo JSON
        String textoJsonEmpleados = leerFichero(p);
        //creo un objeto Gson para convertir el JSON a objetos Java
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        //Creo los entitymanager para que se modificen los datos en las tablas
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //convierto el JSON a un array de objetos DatosModificarEntity
        DatosModificarProject[] datosModificarProjects = gson.fromJson(textoJsonEmpleados, DatosModificarProject[].class);
        //itero sobre los objetos DatosNewProject
        for (DatosModificarProject datos : datosModificarProjects) {
            int idProject = datos.getIdProject();
            ProjectEntity projectEntity = em.find(ProjectEntity.class, idProject);
            if (projectEntity != null) {
                projectEntity.setTitle(datos.getTitle());
                projectEntity.setWeb(datos.getWeb());
                projectEntity.setProjectDescription(datos.getDescripcion());
                projectEntity.setState(datos.getEstado());
                projectEntity.setEndDate(datos.getFinFecha());
                em.merge(projectEntity);
                em.getTransaction().commit();
                System.out.println("Proyecto modificado con ID " + idProject);
            } else {
                System.out.println("No se encontró ningun proyecto con ID: " + idProject);
            }
            em.close();
            emf.close();
        }
    }
}
