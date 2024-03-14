package ConsultasBD.LeerDatos.LeerPorFiltro;

import classes.Leer.DatosLeerProjects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.ProjectEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.nio.file.Path;
import java.util.List;

import static libs.FicheroEscribible.leerFichero;

public class LeerProyectoFiltrado {

    public static String filtrarProyecto(){

        //Obtengo los datos del json con los proyectos a buscar
        Path p = Path.of("src/main/resources/jsonLeer/leerProyecto.json");
        String textoJsonProjects = leerFichero(p);
        Gson gson = new GsonBuilder().create();
        DatosLeerProjects[] listaLeerProjects = gson.fromJson(textoJsonProjects, DatosLeerProjects[].class);
        String jsonSalidaProjects = "";

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();


        for(DatosLeerProjects proyecto: listaLeerProjects){
            String title = proyecto.getTitle();

            //Busco los proyectos por su titulo
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProjectEntity> cq = cb.createQuery(ProjectEntity.class);
            Root<ProjectEntity> root = cq.from(ProjectEntity.class);
            cq.select(root).where(cb.equal(root.get("title"), title));

            //Obtengo la lista de resultados
            List<ProjectEntity> listaProjectsEntity = em.createQuery(cq).getResultList();
            if(!listaProjectsEntity.isEmpty()){

                //Creo el json de salida
                jsonSalidaProjects = gson.toJson(listaProjectsEntity);

            }else{
                System.out.println("No se ha encontrado ningún proyecto.");
            }

            em.close();
            emf.close();
        }

        //Devuelvo el json
        return jsonSalidaProjects;
    }
}
