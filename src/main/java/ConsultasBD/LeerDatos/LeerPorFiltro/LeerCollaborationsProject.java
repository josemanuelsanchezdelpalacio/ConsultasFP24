package ConsultasBD.LeerDatos.LeerPorFiltro;

import classes.Leer.DatosLeerEntity;
import classes.Leer.DatosLeerProjects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.CollaborationEntity;
import entities.EntityEntity;
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

public class LeerCollaborationsProject {

    public static String filtarCollaboratiosPorProject(){

        //Obtengo los datos del json con los proyectos a buscar
        Path p = Path.of("src/main/resources/jsonLeer/leerColaborationsProject.json");
        String textoJsonProjects = leerFichero(p);
        Gson gson = new GsonBuilder().create();
        DatosLeerProjects[] listaLeerProjects = gson.fromJson(textoJsonProjects, DatosLeerProjects[].class);
        String jsonCollaboratiosn = "";

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();


        for(DatosLeerProjects proyecto: listaLeerProjects){
            String title = proyecto.getTitle();
            int idProject = 0;
            //Busco los proyectos por su titulo
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProjectEntity> cq = cb.createQuery(ProjectEntity.class);
            Root<ProjectEntity> root = cq.from(ProjectEntity.class);
            cq.select(root).where(cb.equal(root.get("title"), title));

            //Obtengo la lista de resultados
            List<ProjectEntity> listaProjectsEntity = em.createQuery(cq).getResultList();
            if(!listaProjectsEntity.isEmpty()){
                for(ProjectEntity project : listaProjectsEntity) {
                    idProject = project.getId();
                }
                //Creamos un CriteriaBuilder para poder buscar por el id del proyecto y obtenr las colaboraciones de ese proyecto
                CriteriaBuilder cb1 = em.getCriteriaBuilder();
                CriteriaQuery<CollaborationEntity> cq1 = cb1.createQuery(CollaborationEntity.class);
                Root<CollaborationEntity> root1 = cq1.from(CollaborationEntity.class);
                cq1.select(root1).where(cb1.equal(root1.get("idProject"), idProject));
                List<CollaborationEntity> listaCollaborationsEntity = em.createQuery(cq1).getResultList();
                if(!listaCollaborationsEntity.isEmpty()){
                    for(CollaborationEntity collaboration : listaCollaborationsEntity) {
                        System.out.println("Id de la colaboración: " + collaboration.getId());
                        System.out.println("Id del proyecto: " + collaboration.getIdProject());
                        System.out.println("Id del usuario: " + collaboration.getIdUser());
                        System.out.println("Id de la familia: " + collaboration.getIdFamily());
                        if (collaboration.getManager()) {
                            System.out.println("Manager del proyecto: Si");
                        } else {
                            System.out.println("Manager del proyecto: No");
                        }
                    }

                    //Creo el json de salida
                    jsonCollaboratiosn = gson.toJson(listaCollaborationsEntity);

                }else{

                    System.out.println("El proyecto aun no tiene colaboraciones: ");


                }

            }else{
                System.out.println("No se ha encontrado ningún proyecto.");
            }

            em.close();
            emf.close();
        }
        //Devuelvo el json
        return jsonCollaboratiosn;
    }
}
