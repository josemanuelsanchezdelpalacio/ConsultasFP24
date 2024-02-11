package ConsultasBD.LeerDatos;

import classes.Insertar.DatosInsertarImplement;
import classes.Insertar.DatosInsertarProject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import conexiones.ConexionMySQL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;

public class LeerProyectos {

    public static void leerProyectos() {
        ArrayList<DatosInsertarProject> proyectosLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertProject.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                proyectosLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarProject>>() {
                }.getType());

                for (DatosInsertarProject dato : proyectosLeer) {
                    System.out.println("IdProject: " + dato.getTitle());
                    System.out.println("IdTechnology: " + dato.getLogo());
                    System.out.println("Web: " + dato.getWeb());
                    System.out.println("ProjectDescription: " + dato.getProjectDescription());
                    System.out.println("State: " + dato.getState());
                    System.out.println("InitDate: " + dato.getInitDate());
                    System.out.println("EndDate: " + dato.getEndDate());
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}