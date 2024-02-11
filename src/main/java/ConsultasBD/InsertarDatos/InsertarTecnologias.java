package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.DatosInsertarEntity;
import classes.Insertar.DatosInsertarTechnology;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.EntityEntity;
import entities.TechnologyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsertarTecnologias {

    public static ArrayList<DatosInsertarTechnology> tecnologiasInsertar = new ArrayList<>();

    public static void insertarTecnologias() {
        Path p = Path.of("src/main/resources/jsonTablas/insertTechnology.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                tecnologiasInsertar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarTechnology>>() {
                }.getType());

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosInsertarTechnology datos : tecnologiasInsertar) {
                    if (!existeTecnologia(em, datos.getTag())) {
                        TechnologyEntity technologyEntity = new TechnologyEntity();
                        technologyEntity.setTag(datos.getTag());
                        technologyEntity.setTechName(datos.getTechName());

                        em.persist(technologyEntity);
                        System.out.println("Tecnología con tag " + datos.getTag() + " ha sido insertada correctamente");
                    } else {
                        System.out.println("Tecnología con tag " + datos.getTag() + " ya existe, no se insertará.");
                    }
                }

                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }

    private static boolean existeTecnologia(EntityManager em, String tag) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TechnologyEntity t WHERE t.tag = :tag");
        query.setParameter("tag", tag);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
