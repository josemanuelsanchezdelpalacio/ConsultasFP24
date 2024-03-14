package ConsultasBD.EliminarDatosBD;

import Singleton.EmfSingleton;
import classes.Eliminar.DatosEliminarCentros;
import classes.Eliminar.DatosEliminarColaboraciones;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.CollaborationEntity;
import entities.EntityEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EliminarColaboraciones {

    public static ArrayList<DatosEliminarColaboraciones> colaboracionesEliminar = new ArrayList<>();
    public static void eliminarFavorito() {
        Path p = Path.of("src/main/resources/jsonEliminar/eliminarColaboraciones.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                colaboracionesEliminar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosEliminarColaboraciones>>() {
                }.getType());
                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for(DatosEliminarColaboraciones datos : colaboracionesEliminar){
                    Query query = em.createQuery("select c from CollaborationEntity c where c.idProject = :idProject ");
                    query.setParameter("idProject", datos.getIdProject());
                    List<CollaborationEntity> result = query.getResultList();
                    if(result.isEmpty()){
                        System.out.println("La colaboración no existe");
                    }else{
                        em.remove(result.get(0));
                        System.out.println("Colaboración eliminada");
                    }
                }
                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }



}
