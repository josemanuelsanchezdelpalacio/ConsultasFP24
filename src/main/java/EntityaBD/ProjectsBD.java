package EntityaBD;

import classes.DatosNewProject;
import classes.Respuesta;
import com.google.gson.Gson;
import entities.ProjectEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;

import static DTO.leerJson.datos;

public class ProjectsBD {

    public static String subirProyectos() {
        //creo un EntityManagerFactory para la persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        for(DatosNewProject proyecto: datos.projects){
            ProjectEntity project = new ProjectEntity(proyecto.getTitle(),proyecto.getLogo(), proyecto.getWeb(), proyecto.getDescripcion(), proyecto.getState(), (Date) proyecto.getInitDate(), (Date) proyecto.getEndDate());
            System.out.println(project.getTitle());
            //creo un EntityManager y persisto el objeto ProjectEntity en la base de datos
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(project);
                //creo un objeto Gson para convertir una respuesta a json
                Gson gson = new Gson();
                String respuesta = "Datos subidos correctamente";
                Respuesta respuestaObj = new Respuesta(respuesta);
                String jsonRespuesta = gson.toJson(respuestaObj);

                em.getTransaction().commit();

                return jsonRespuesta;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error en la operación de la base de datos");
            } finally {
                em.close();

            }
        }

        emf.close();
        return null;
    }
}
