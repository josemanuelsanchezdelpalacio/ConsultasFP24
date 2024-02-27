package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.Entities.DatosInsertarEntity;
import classes.Insertar.Entities.Entity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.EntityEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsertarEntidades {



    public static void insertarEntidades() {
        Path p = Path.of("src/main/resources/jsonTablas/insertEntity.json");
        DatosInsertarEntity entitiesInsertar;
        String txtJson;

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                txtJson = Files.readString(p);
                Gson gson = new Gson();
                entitiesInsertar = gson.fromJson(txtJson, DatosInsertarEntity.class);

                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (Entity datos : entitiesInsertar.getEntities()) {
                    if (!existeEntidadConEntityCode(em, datos.getEntityCode())) {
                        EntityEntity entityEntity = new EntityEntity();
                        entityEntity.setEntityName(datos.getEntityName());
                        entityEntity.setEntityCode(datos.getEntityCode());
                        entityEntity.setWeb(datos.getWeb());
                        entityEntity.setEmail(datos.getEmail());

                        em.persist(entityEntity);
                        System.out.println("Entidad con código " + datos.getEntityCode() + " ha sido insertada correctamente");
                    } else {
                        System.out.println("Entidad con código " + datos.getEntityCode() + " ya existe, no se insertará.");
                    }
                }

                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }

    private static boolean existeEntidadConEntityCode(EntityManager em, String entityCode) {
        Query query = em.createQuery("SELECT COUNT(e) FROM EntityEntity e WHERE e.entityCode = :entityCode");
        query.setParameter("entityCode", entityCode);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}