package ConsultasBD.LeerDatos.LeerTablaCompleta;

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

    public static String leerUsuarios() {

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        String jsonSalidaUsers = "";
        Gson gson = new Gson();

        try {

            //Obtengo la lista de usuarios
            List<UsersEntity> usuariosLeer = em.createQuery("SELECT u FROM UsersEntity u", UsersEntity.class).getResultList();

            //Creo el json de salida
            jsonSalidaUsers = gson.toJson(usuariosLeer);
        } catch (Exception e) {
            System.out.println("Error al leer los usuarios desde la base de datos");

        } finally {
            em.close();
            emf.close();
        }

        //Devuelvo el json
        return jsonSalidaUsers;
    }
}
