package ConsultasBD.LeerDatos;

import classes.Insertar.Proyecto.DatosInsertarProject;
import classes.Insertar.Proyecto.Proyecto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LeerProyectos {

    public static void leerProyectos() {
        ArrayList<Proyecto> proyectosLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertProject.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                proyectosLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarProject>>() {
                }.getType());

                for (Proyecto dato : proyectosLeer) {
                    System.out.println("IdProject: " + dato.getTitle());
                    System.out.println("IdTechnology: " + dato.getLogo());
                    System.out.println("Web: " + dato.getWeb());
                    System.out.println("ProjectDescription: " + dato.getDescripcion());
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