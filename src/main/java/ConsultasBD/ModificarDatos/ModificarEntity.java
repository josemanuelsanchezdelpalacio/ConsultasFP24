package ConsultasBD.ModificarDatos;

import classes.DatosNewProject;
import classes.Modificar.DatosModificarEntity;
import com.google.gson.Gson;
import conexiones.ConexionMySQL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libs.Leer;

import java.nio.file.Path;
import java.sql.*;

import static DTO.leerJson.leerFichero;

public class ModificarEntity {
    public static void modificar() {
        try (Connection conexion = ConexionMySQL.conectar("FP24MJO")) {
            try {
                Path p = Path.of("src/main/resources/modifEntity.json");
                //leo el archivo JSON
                String textoJsonEmpleados = leerFichero(p);
                //creo un objeto Gson para convertir el JSON a objetos Java
                Gson gson = new Gson();
                //Creo los entitymanager para que se modificen los datos en las tablas
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                //convierto el JSON a un array de objetos DatosModificarEntity
                DatosModificarEntity[] datosModificarEntities = gson.fromJson(textoJsonEmpleados, DatosModificarEntity[].class);
                //itero sobre los objetos DatosNewProject
                for (DatosModificarEntity datos : datosModificarEntities) {
                    Boolean idValido = false;
                    PreparedStatement ps = conexion.prepareStatement("SELECT Id, EntityName FROM ENTITY");
                    ResultSet rs = ps.executeQuery();
                    //HABRA QUE ENSEÑAR LOS CENTROS Y QUE ELIGA EL ID EN LA APP
                    int idEntity = datos.getIdEntity();
                    ResultSet rs2 = ps.executeQuery();
                    while (rs2.next()) {
                        if (idEntity == Integer.parseInt(rs2.getString("Id"))) {
                            idValido = true;
                        }
                    }
                    if (idValido) {
                        String webModificar = datos.getWeb();
                        String emailModificar = datos.getEmail();
                        PreparedStatement ps3 = conexion.prepareStatement("UPDATE ENTITY SET Web = ?, Email = ? WHERE Id = ?");
                        ps3.setString(1, webModificar);
                        ps3.setString(2, emailModificar);
                        ps3.setInt(3, idEntity);
                        int registrosCambiados = ps3.executeUpdate();
                        System.out.println("Registros cambiados: " + registrosCambiados);
                        em.getTransaction().commit();
                    } else {
                        System.out.println("El nombre de la entidad no se ha encontrado");
                    }
                    em.close();
                    emf.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al listar los datos de la tabla.");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los datos de la tabla. Pruebe de nuevo. ");
        }
    }
}
