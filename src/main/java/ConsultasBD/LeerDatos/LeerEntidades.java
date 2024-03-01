package ConsultasBD.LeerDatos;

import classes.Insertar.Entities.DatosInsertarEntity;
import classes.Insertar.Entities.Entity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.EntityEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerEntidades {

    public static void leerEntidades() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            List<EntityEntity> entidadesLeer = em.createQuery("SELECT e FROM EntityEntity e", EntityEntity.class).getResultList();

            for (EntityEntity entidad : entidadesLeer) {
                System.out.println("EntityName: " + entidad.getEntityName());
                System.out.println("EntityCode: " + entidad.getEntityCode());
                System.out.println("Web: " + entidad.getWeb());
                System.out.println("Email: " + entidad.getEmail());
            }
        } catch (Exception e) {
            System.out.println("Error al leer las entidades desde la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
