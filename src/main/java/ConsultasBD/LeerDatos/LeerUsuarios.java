package ConsultasBD.LeerDatos;

import classes.Insertar.Usuarios.DatosInsertarUser;
import classes.Insertar.Usuarios.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerUsuarios {

    public static void leerUsuarios() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            List<UsersEntity> usuariosLeer = em.createQuery("SELECT u FROM UsersEntity u", UsersEntity.class).getResultList();

            for (UsersEntity usuario : usuariosLeer) {
                System.out.println("IdEntity: " + usuario.getIdEntity());
                System.out.println("IdLogin: " + usuario.getLogin());
                System.out.println("UserName: " + usuario.getUserName());
                System.out.println("Surname: " + usuario.getSurname());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("LinkedIn: " + usuario.getLinkedIn());
            }
        } catch (Exception e) {
            System.out.println("Error al leer los usuarios desde la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
