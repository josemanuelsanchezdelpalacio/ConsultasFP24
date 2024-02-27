package ConsultasBD.LeerDatos;

import classes.Insertar.Implementaciones.DatosInsertarImplement;
import classes.Insertar.Implementaciones.Implementacion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LeerImplementaciones {

    public static void leerImplementaciones() {
        ArrayList<Implementacion> implementLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertImplement.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                implementLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarImplement>>() {
                }.getType());

                for (Implementacion dato : implementLeer) {
                    System.out.println("IdProject: " + dato.getIdProject());
                    System.out.println("IdTechnology: " + dato.getIdTechnology());
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}