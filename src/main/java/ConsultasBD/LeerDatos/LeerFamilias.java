package ConsultasBD.LeerDatos;

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

    public static void leerFamilias() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            List<FamilyEntity> familiasLeer = em.createQuery("SELECT f FROM FamilyEntity f", FamilyEntity.class).getResultList();

            for (FamilyEntity familia : familiasLeer) {
                System.out.println("FamilyCode: " + familia.getFamilyCode());
                System.out.println("FamilyName: " + familia.getFamilyName());
            }
        } catch (Exception e) {
            System.out.println("Error al leer las familias desde la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}