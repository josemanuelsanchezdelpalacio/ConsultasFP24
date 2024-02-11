package ConsultasBD.LeerDatos;

import classes.Insertar.DatosInsertarCollaboration;
import classes.Insertar.DatosInsertarEntity;
import classes.Insertar.DatosInsertarFamily;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LeerFamilias {

    public static void leerFamilias() {
        ArrayList<DatosInsertarFamily> familiasLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertFamily.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                familiasLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarFamily>>() {
                }.getType());

                for (DatosInsertarFamily dato : familiasLeer) {
                    System.out.println("FamilyCode: " + dato.getFamilyCode());
                    System.out.println("FamilyName: " + dato.getFamilyName());
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}