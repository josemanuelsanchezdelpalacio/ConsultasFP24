package ConsultasBD.LeerDatos;

import classes.Insertar.DatosInsertarEntity;
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

public class LeerEntidades {

    public static void leerEntidades() {
        ArrayList<DatosInsertarEntity> entidadesLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertEntity.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                entidadesLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarEntity>>() {
                }.getType());

                for (DatosInsertarEntity dato : entidadesLeer) {
                    System.out.println("EntityName: " + dato.getEntityName());
                    System.out.println("EntityCode: " + dato.getEntityCode());
                    System.out.println("Web: " + dato.getWeb());
                    System.out.println("Email: " + dato.getEmail());
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}
