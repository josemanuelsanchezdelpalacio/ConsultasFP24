package ConsultasBD.EliminarDatosBD;

import Singleton.EmfSingleton;
import classes.Eliminar.DatosEliminarTechnology;
import classes.Eliminar.DatosEliminarUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.TechnologyEntity;
import entities.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EliminarUser {

    public static ArrayList<DatosEliminarUser> userEliminar = new ArrayList<>();
    public static void eliminarUser() {
        Path p = Path.of("src/main/resources/jsonEliminar/eliminarUser.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                userEliminar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosEliminarUser>>() {
                }.getType());
                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosEliminarUser datos : userEliminar) {
                    Query query = em.createQuery("select u from UsersEntity u where u.idEntity = :idEntity and u.login = :login ");
                    query.setParameter("idEntity", datos.getIdEntity());
                    query.setParameter("login", datos.getLogin());
                    List<UsersEntity> result = query.getResultList();
                    if (result.isEmpty()) {
                        System.out.println("El usuario no existe");
                    } else {
                        em.remove(result.get(0));
                        System.out.println("Usuario eliminado");
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
