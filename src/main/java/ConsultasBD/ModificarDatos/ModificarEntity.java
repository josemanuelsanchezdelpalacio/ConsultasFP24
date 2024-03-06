package ConsultasBD.ModificarDatos;

import classes.Modificar.DatosModificarEntity;
import com.google.gson.Gson;
import conexiones.ConexionMySQL;
import entities.EntityEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static libs.FicheroEscribible.leerFichero;

public class ModificarEntity {
    public static void modificar() {
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
              //HABRA QUE ENSEÑAR LOS CENTROS Y QUE ELIGA EL ID EN LA APP
              int idEntity = datos.getIdEntity();
              EntityEntity entityEntity = em.find(EntityEntity.class, idEntity);
              if(entityEntity!=null){
                  entityEntity.setWeb(datos.getWeb());
                  entityEntity.setEmail(datos.getEmail());
                  em.merge(entityEntity);
                  em.getTransaction().commit();
                  System.out.println("Entity modificada con ID " + idEntity);
              }else{
                  System.out.println("No se encontró ninguna entidad con ID: " + idEntity);
              }
              em.close();
              emf.close();
          }
    }
}
