package ConsultasBD.LeerDatos;

import classes.Insertar.DatosInsertarEntity;
import classes.Insertar.DatosInsertarProject;
import classes.Insertar.DatosInsertarTechnology;
import classes.Insertar.DatosInsertarUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LeerTecnologias {

    public static void leerTecnologias() {
        ArrayList<DatosInsertarTechnology> tecnologiasLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertTechnology.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                tecnologiasLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarTechnology>>() {
                }.getType());

                for (DatosInsertarTechnology dato : tecnologiasLeer) {
                    System.out.println("IdProject: " + dato.getTag());
                    System.out.println("IdTechnology: " + dato.getTechName());
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}
