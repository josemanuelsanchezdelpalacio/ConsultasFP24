package ConsultasBD.LeerDatos;

import classes.Insertar.Entities.DatosInsertarEntity;
import classes.Insertar.Entities.Entity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LeerEntidades {

    public static void leerEntidades() {
        ArrayList<Entity> entidadesLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertEntity.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                entidadesLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarEntity>>() {
                }.getType());

                for (Entity dato : entidadesLeer) {
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
