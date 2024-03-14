package ConsultasBD.LeerDatos.LeerTablaCompleta;

import classes.Insertar.Implementaciones.DatosInsertarImplement;
import classes.Insertar.Implementaciones.Implementacion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.ImplementEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerImplementaciones {

    public static String leerImplementaciones() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        String jsonSalidaImplementaciones = "";
        Gson gson = new Gson();

        try {
            List<ImplementEntity> implementLeer = em.createQuery("SELECT imp FROM ImplementEntity imp", ImplementEntity.class).getResultList();

            for (ImplementEntity implementacion : implementLeer) {
                System.out.println("IdProject: " + implementacion.getIdProject());
                System.out.println("IdTechnology: " + implementacion.getIdTechnology());
            }
            jsonSalidaImplementaciones = gson.toJson(implementLeer);
        } catch (Exception e) {
            System.out.println("Error al leer las implementaciones desde la base de datos");

        } finally {
            em.close();
            emf.close();
        }
        return jsonSalidaImplementaciones;
    }
}