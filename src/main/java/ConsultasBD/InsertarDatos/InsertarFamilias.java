package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.Families.DatosInsertarFamily;
import classes.Insertar.Families.Family;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import conexiones.ConexionMySQL;
import entities.FamilyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertarFamilias {

    public static void insertarFamilias() {
        Path p = Path.of("src/main/resources/jsonInsertar/insertFamily.json");
        DatosInsertarFamily familiesInsertar;
        String txtJson;

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                txtJson = Files.readString(p);
                Gson gson = new Gson();
                familiesInsertar = gson.fromJson(txtJson, DatosInsertarFamily.class);
                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();


                for (Family datos : familiesInsertar.getFamilies()) {
                    if (!existeFamilia(em, datos.getFamilyName())) {
                        FamilyEntity familyEntity = new FamilyEntity();
                        familyEntity.setFamilyCode(datos.getFamilyCode());
                        familyEntity.setFamilyName(datos.getFamilyName());
                        em.persist(familyEntity);
                        System.out.println("Family con nombre " + datos.getFamilyName() + " ha sido insertado correctamente");
                    } else {
                        System.out.println("Family con nombre " + datos.getFamilyName() + "ya existe.");
                    }
                }
                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    // Método auxiliar para verificar si ya existe una familia con el mismo FamilyCode
    private static boolean existeFamilia(EntityManager em, String familyName) {
        Query query = em.createQuery("SELECT COUNT(f) FROM FamilyEntity f WHERE f.familyName = :familyName");
        query.setParameter("familyName", familyName);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
