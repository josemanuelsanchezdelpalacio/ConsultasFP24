package ConsultasBD.ModificarDatos;

import classes.Modificar.DatosModificarProject;
import classes.Modificar.DatosModificarUser;
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

public class ModificarUser {
    public static void modificar() {
        try (Connection conexion = ConexionMySQL.conectar("FP24MJO")) {
            Path p = Path.of("src/main/resources/modifUser.json");
            //leo el archivo JSON
            String textoJsonEmpleados = leerFichero(p);
            //creo un objeto Gson para convertir el JSON a objetos Java
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            //Creo los entitymanager para que se modificen los datos en las tablas
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            //convierto el JSON a un array de objetos DatosModificarEntity
            DatosModificarUser[] datosModificarUsers = gson.fromJson(textoJsonEmpleados, DatosModificarUser[].class);
            //itero sobre los objetos DatosNewProject
            for (DatosModificarUser datos : datosModificarUsers) {
                Boolean nombreValido = false;
                PreparedStatement ps2 = conexion.prepareStatement("SELECT Id FROM USERS");
                int idUser = datos.getIdUser();
                ResultSet rs3 = ps2.executeQuery();
                while (rs3.next()) {
                    if (idUser == Integer.parseInt(rs3.getString("Id"))) {
                        nombreValido = true;
                    }
                }
                if (nombreValido) {
                    boolean idEntityValido = false;
                    int idEntityModificar = 0;
                    do {
                        idEntityModificar = datos.getIdEntity();
                        PreparedStatement ps4 = conexion.prepareStatement("SELECT Id FROM ENTITY");
                        ResultSet rs5 = ps4.executeQuery();
                        while (rs5.next()) {
                            if (idEntityModificar == rs5.getInt("Id")) {
                                idEntityValido = true;
                            }
                        }
                    } while (!idEntityValido);
                    String userNameModificar = datos.getUserName();
                    String surnameModificar = datos.getSurname();
                    String emailModificar = datos.getEmail();
                    String linkedInModificar = datos.getLinkedin();
                    PreparedStatement ps5 = conexion.prepareStatement("UPDATE USERS SET IdEntity = ?, UserName = ?, Surname = ?, Email = ?, LinkedIn = ? WHERE Id = ?");
                    ps5.setInt(1, idEntityModificar);
                    ps5.setString(2, userNameModificar);
                    ps5.setString(3, surnameModificar);
                    ps5.setString(4, emailModificar);
                    ps5.setString(5, linkedInModificar);
                    ps5.setInt(6, idUser);
                    int registrosCambiados = ps5.executeUpdate();
                    System.out.println("Reistros cambiados: " + registrosCambiados);
                    em.getTransaction().commit();
                } else {
                    System.out.println("El nombre del usuario no se ha encontrado");
                }
            }
            em.close();
            emf.close();
        } catch (SQLException e) {
            System.out.println("Error al listar los datos de la tabla. Pruebe de nuevo. ");
        }
    }
}
