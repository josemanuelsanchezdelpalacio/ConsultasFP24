package ConsultasBD.LeerDatos.LeerTablaCompleta;

import classes.Insertar.Colaboraciones.Colaboracion;
import classes.Insertar.Colaboraciones.DatosInsertarCollaboration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import conexiones.ConexionMySQL;
import entities.CollaborationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeerColaboraciones {

    public static String leerColaboraciones() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        String jsonSalidaProjects = "";
        Gson gson = new Gson();

        try {
            List<CollaborationEntity> colaboracionesLeer = em.createQuery("SELECT c FROM CollaborationEntity c", CollaborationEntity.class).getResultList();

            for (CollaborationEntity colaboracion : colaboracionesLeer) {
                System.out.println("IdProyecto: " + colaboracion.getIdProject());
                System.out.println("IdUser: " + colaboracion.getIdUser());
                System.out.println("IdFamily: " + colaboracion.getIdFamily());
                System.out.println("IsManager: " + colaboracion.getManager());
            }
            jsonSalidaProjects = gson.toJson(colaboracionesLeer);
        } catch (Exception e) {
            System.out.println("Error al leer las colaboraciones desde la base de datos");

        } finally {
            em.close();
            emf.close();
        }
        return jsonSalidaProjects;
    }
}
