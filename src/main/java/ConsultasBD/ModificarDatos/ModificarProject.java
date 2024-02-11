package ConsultasBD.ModificarDatos;

import classes.Modificar.DatosModificarEntity;
import classes.Modificar.DatosModificarProject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conexiones.ConexionMySQL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.Leer;

import java.nio.file.Path;
import java.sql.*;

import static DTO.leerJson.leerFichero;

public class ModificarProject {
    public static void modificar() {
        try (Connection conexion = ConexionMySQL.conectar("FP24MJO")) {
            Path p = Path.of("src/main/resources/modifProject.json");
            //leo el archivo JSON
            String textoJsonEmpleados = leerFichero(p);
            //creo un objeto Gson para convertir el JSON a objetos Java
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            //Creo los entitymanager para que se modificen los datos en las tablas
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
//            EntityManager em = emf.createEntityManager();
//            em.getTransaction().begin();
            //convierto el JSON a un array de objetos DatosModificarEntity
            DatosModificarProject[] datosModificarProjects = gson.fromJson(textoJsonEmpleados, DatosModificarProject[].class);
            //itero sobre los objetos DatosNewProject
            for (DatosModificarProject datos : datosModificarProjects) {
                Boolean nombreValido = false;
                PreparedStatement ps2 = conexion.prepareStatement("SELECT Id, Title FROM PROJECT");
                int idProject = datos.getIdProject();
                ResultSet rs3 = ps2.executeQuery();
                while (rs3.next()) {
                    if (idProject == Integer.parseInt(rs3.getString("Id"))) {
                        nombreValido = true;
                    }
                }
                if (nombreValido) {
                    String tituloModificar = datos.getTitle();
                    String webModificar = datos.getWeb();
                    String descripcionModificar = datos.getDescripcion();
                    String estadoModificar = datos.getEstado();
                    Date finFechaModificar = datos.getFinFecha();
                    PreparedStatement ps3 = conexion.prepareStatement("UPDATE PROJECT SET Title = ?, Web = ?, ProjectDescription = ?, State = ?, EndDate = ? WHERE Id = ?");
                    ps3.setString(1, tituloModificar);
                    ps3.setString(2, webModificar);
                    ps3.setString(3, descripcionModificar);
                    ps3.setString(4, estadoModificar);
                    ps3.setDate(5, finFechaModificar);
                    ps3.setInt(6, idProject);
                    int registrosCambiados = ps3.executeUpdate();
                    System.out.println("Reistros cambiados: " + registrosCambiados);
//                    em.getTransaction().commit();
                } else {
                    System.out.println("El nombre del proeycto no se ha encontrado");
                }
            }
//            em.close();
//            emf.close();
        } catch (SQLException e) {
            System.out.println("Error al listar los datos de la tabla. Pruebe de nuevo. ");
        }
    }
}
