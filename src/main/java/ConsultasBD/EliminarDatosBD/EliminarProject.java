package ConsultasBD.EliminarDatosBD;

import Singleton.EmfSingleton;
import classes.Eliminar.DatosEliminarProject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.ProjectEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EliminarProject {
    public static ArrayList<DatosEliminarProject> projectsEliminar = new ArrayList<>();

    public static void eliminarProject(){

        Path p = Path.of("src/main/resources/eliminarProject.json");
        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                projectsEliminar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosEliminarProject>>() {
                }.getType());
                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for(DatosEliminarProject datos : projectsEliminar){
                    Query query = em.createQuery("select p from ProjectEntity p where p.title = :title");
                    query.setParameter("title", datos.getTitle());
                    List<ProjectEntity> result = query.getResultList();
                    if(result.isEmpty()){
                        System.out.println("El proyecto " + datos.getTitle() + " no existe");
                    }else{
                        ProjectEntity projectToDelete = result.get(0);

                        // Eliminar registros relacionados en FavouriteEntity
                        Query favQuery = em.createQuery("DELETE FROM FavouriteEntity f WHERE f.idProject = :idProject");
                        favQuery.setParameter("idProject", projectToDelete.getId());
                        favQuery.executeUpdate();

                        em.remove(projectToDelete);


                        System.out.println("Proyecto " + datos.getTitle() + " eliminado");
                        em.getTransaction().commit();
                    }

                }

                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}
