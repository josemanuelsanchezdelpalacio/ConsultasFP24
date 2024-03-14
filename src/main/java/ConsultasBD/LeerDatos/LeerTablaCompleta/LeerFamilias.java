package ConsultasBD.LeerDatos.LeerTablaCompleta;

import classes.Insertar.Families.Family;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.FamilyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerFamilias {

    public static String leerFamilias() {

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        String jsonSalidaFamilias = "";
        Gson gson = new Gson();

        try {
            //Obtengo la lista de familias
            List<FamilyEntity> familiasLeer = em.createQuery("SELECT f FROM FamilyEntity f", FamilyEntity.class).getResultList();

            //Creo el json de salida
            jsonSalidaFamilias = gson.toJson(familiasLeer);
        } catch (Exception e) {
            System.out.println("Error al leer las familias desde la base de datos");

        } finally {
            em.close();
            emf.close();
        }

        //Devuelvo el json
        return jsonSalidaFamilias;
    }
}