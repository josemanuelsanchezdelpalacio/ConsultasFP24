package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.DatosInsertarFamily;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.FamilyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsertarFamilias {

    public static ArrayList<DatosInsertarFamily> familiasInsertar = new ArrayList<>();

    public static void insertarFamilias() {
        Path p = Path.of("src/main/resources/jsonTablas/insertFamily.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                familiasInsertar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarFamily>>() {
                }.getType());

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosInsertarFamily datos : familiasInsertar) {
                    if (!existeFamilia(em, datos.getFamilyCode())) {
                        FamilyEntity familyEntity = new FamilyEntity();
                        familyEntity.setFamilyCode(datos.getFamilyCode());
                        familyEntity.setFamilyName(datos.getFamilyName());

                        em.persist(familyEntity);
                        System.out.println("Familia con código " + datos.getFamilyCode() + " ha sido insertada correctamente");
                    } else {
                        System.out.println("Familia con código " + datos.getFamilyCode() + " ya existe, no se insertará.");
                    }
                }

                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }

    // Método auxiliar para verificar si ya existe una familia con el mismo FamilyCode
    private static boolean existeFamilia(EntityManager em, String familyCode) {
        Query query = em.createQuery("SELECT COUNT(f) FROM FamilyEntity f WHERE f.familyCode = :familyCode");
        query.setParameter("familyCode", familyCode);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
