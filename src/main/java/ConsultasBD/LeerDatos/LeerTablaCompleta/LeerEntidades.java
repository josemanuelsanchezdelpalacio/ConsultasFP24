package ConsultasBD.LeerDatos.LeerTablaCompleta;

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

    public static String leerEntidades() {

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        String jsonSalidaEntidades = "";
        Gson gson = new Gson();

        try {
            //Obtengo la lista de entidades
            List<EntityEntity> entidadesLeer = em.createQuery("SELECT e FROM EntityEntity e", EntityEntity.class).getResultList();

            //Creo el json de salida
            jsonSalidaEntidades = gson.toJson(entidadesLeer);
        } catch (Exception e) {
            System.out.println("Error al leer las entidades desde la base de datos");

        } finally {
            em.close();
            emf.close();
        }

        //Devuelvo el json
        return jsonSalidaEntidades;
    }
}
