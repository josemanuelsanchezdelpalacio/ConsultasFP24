package ConsultasBD.LeerDatos;

import classes.Insertar.DatosInsertarCollaboration;
import classes.Insertar.DatosInsertarEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LeerColaboraciones {

    public static void leerColaboraciones() {
        ArrayList<DatosInsertarCollaboration> colaboracionesLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertCollaboration.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                colaboracionesLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarCollaboration>>() {
                }.getType());

                for (DatosInsertarCollaboration dato : colaboracionesLeer) {
                    System.out.println("IdProyecto: " + dato.getIdProject());
                    System.out.println("IdUser: " + dato.getIdUser());
                    System.out.println("IdFamily: " + dato.getIdFamily());
                    System.out.println("IsManger: " + dato.isManager());
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}
