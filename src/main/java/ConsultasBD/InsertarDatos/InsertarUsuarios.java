package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.Usuarios.DatosInsertarUser;
import classes.Insertar.Usuarios.Usuario;
import com.google.gson.Gson;
import entities.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InsertarUsuarios {
    public static void insertarUsuarios() {
        Path p = Path.of("src/main/resources/jsonTablas/insertUser.json");
        DatosInsertarUser usuariosInsertar;
        String txtJson;

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                txtJson = Files.readString(p);
                Gson gson = new Gson();
                usuariosInsertar = gson.fromJson(txtJson, DatosInsertarUser.class);

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (Usuario datos : usuariosInsertar.getUsers()) {
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
