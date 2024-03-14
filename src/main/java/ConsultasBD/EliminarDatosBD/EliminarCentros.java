package ConsultasBD.EliminarDatosBD;

import Singleton.EmfSingleton;
import classes.Eliminar.DatosEliminarCentros;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

public class EliminarCentros {

    public static ArrayList<DatosEliminarCentros> centrosEliminar = new ArrayList<>();
    public static void eliminarCentro() {
        Path p = Path.of("src/main/resources/jsonEliminar/eliminarCentros.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                centrosEliminar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosEliminarCentros>>() {
                }.getType());
                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for(DatosEliminarCentros datos : centrosEliminar){
                    Query query = em.createQuery("select e from EntityEntity e where e.entityCode = :code");
                    query.setParameter("code", datos.getEntityCode());
                    List<EntityEntity> result = query.getResultList();
                    if(result.isEmpty()){
                        System.out.println("El centro " + datos.getEntityName() + " con código " + datos.getEntityCode() + " no existe");
                    }else{
                        em.remove(result.get(0));
                        System.out.println("Centro " + datos.getEntityName() + " con código " + datos.getEntityCode() + " ha sido eliminado correctamente");
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
