package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.DatosInsertarUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsertarUsuarios {

    public static ArrayList<DatosInsertarUser> usuariosInsertar = new ArrayList<>();

    public static void insertarUsuarios() {
        Path p = Path.of("src/main/resources/jsonTablas/insertUser.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                usuariosInsertar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarUser>>() {
                }.getType());

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosInsertarUser datos : usuariosInsertar) {
                    if (!existeUsuario(em, datos.getLogin())) {
                        UsersEntity userEntity = new UsersEntity();
                        userEntity.setIdEntity(datos.getIdEntity());
                        userEntity.setLogin(datos.getLogin());
                        userEntity.setUserName(datos.getUserName());
                        userEntity.setSurname(datos.getSurname());
                        userEntity.setEmail(datos.getEmail());
                        userEntity.setLinkedIn(datos.getLinkedIn());

                        em.persist(userEntity);
                        System.out.println("Usuario con login " + datos.getLogin() + " ha sido insertado correctamente");
                    } else {
                        System.out.println("Usuario con login " + datos.getLogin() + " ya existe, no se insertará.");
                    }
                }

                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }

    private static boolean existeUsuario(EntityManager em, String login) {
        Query query = em.createQuery("SELECT COUNT(u) FROM UsersEntity u WHERE u.login = :login");
        query.setParameter("login", login);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
