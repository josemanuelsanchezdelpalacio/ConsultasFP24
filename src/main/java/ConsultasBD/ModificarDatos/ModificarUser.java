package ConsultasBD.ModificarDatos;

import classes.Modificar.DatosModificarUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conexiones.ConexionMySQL;
import entities.EntityEntity;
import entities.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static libs.FicheroEscribible.leerFichero;

public class ModificarUser {
    public static void modificar() {
        Path p = Path.of("src/main/resources/jsonModificar/modifUser.json");
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
            int idUser = datos.getIdUser();
            UsersEntity usersEntity = em.find(UsersEntity.class, idUser);
            if (usersEntity != null) {
                usersEntity.setUserName(datos.getUserName());
                usersEntity.setSurname(datos.getSurname());
                usersEntity.setEmail(datos.getEmail());
                usersEntity.setLinkedIn(datos.getLinkedin());

                em.merge(usersEntity);
                em.getTransaction().commit();
                System.out.println("Entity modificada con ID " + idUser);
            } else {
                System.out.println("No se encontró ninguna entidad con ID: " + idUser);
            }
            em.close();
            emf.close();
        }
    }
}
