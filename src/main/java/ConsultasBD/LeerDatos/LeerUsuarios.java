package ConsultasBD.LeerDatos;

import classes.Insertar.Usuarios.DatosInsertarUser;
import classes.Insertar.Usuarios.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LeerUsuarios {

    public static void leerUsuarios() {
        ArrayList<Usuario> usuariosLeer = new ArrayList<>();
        Path p = Path.of("src/main/resources/jsonTablas/insertUser.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                usuariosLeer = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarUser>>() {
                }.getType());

                for (Usuario dato : usuariosLeer) {
                    System.out.println("IdEntity: " + dato.getIdEntity());
                    System.out.println("IdLogin: " + dato.getLogin());
                    System.out.println("UserName: " + dato.getUserName());
                    System.out.println("Surname: " + dato.getSurname());
                    System.out.println("Email: " + dato.getEmail());
                    System.out.println("LinkedIn: " + dato.getLinkedIn());
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }
}
