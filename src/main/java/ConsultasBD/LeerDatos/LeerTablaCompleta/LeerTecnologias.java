package ConsultasBD.LeerDatos.LeerTablaCompleta;

import classes.Insertar.Tecnologias.DatosInsertarTechnology;
import classes.Insertar.Tecnologias.Tecnologia;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.TechnologyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerTecnologias {

    public static void leerTecnologias() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            List<TechnologyEntity> tecnologiasLeer = em.createQuery("SELECT t FROM TechnologyEntity t", TechnologyEntity.class).getResultList();

            for (TechnologyEntity tecnologia : tecnologiasLeer) {
                System.out.println("Tag: " + tecnologia.getTag());
                System.out.println("TechName: " + tecnologia.getTechName());
            }
        } catch (Exception e) {
            System.out.println("Error al leer las tecnologías desde la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
