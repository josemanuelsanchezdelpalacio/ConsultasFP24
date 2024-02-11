package DTO;

import classes.DatosNewProject;
import classes.DatosNewProjects;
import com.google.gson.Gson;
import entities.EntityEntity;
import entities.ProjectEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.sql.Date;

import jakarta.persistence.Persistence;

public class leerJson {

    public static DatosNewProjects datos = new DatosNewProjects();

    public static void leer() {
        Path p = Path.of("src/main/resources/infoPersona.json");
        //leo el archivo JSON
        String textoJsonEmpleados = leerFichero(p);

        //creo un objeto Gson para convertir el JSON a objetos Java
        Gson gson = new Gson();

        //convierto el JSON a un array de objetos DatosNewProject
        DatosNewProject[] datosProjects = gson.fromJson(textoJsonEmpleados, DatosNewProject[].class);



        //itero sobre los objetos DatosNewProject
        for (DatosNewProject newProject : datosProjects) {
            DatosNewProject datosProyecto1 = new DatosNewProject(newProject.getTitle(), newProject.getLogo(), newProject.getDescripcion(), newProject.getWeb(), newProject.getInitDate(), newProject.getEndDate(), newProject.getState(), newProject.getIDUser(), newProject.isManager(), newProject.getIDFamily());
            //creo un nuevo objeto ProjectEntity con los set de DatosNewProject
            datos.projects.add(datosProyecto1);
        }
    }

    public static String leerFichero(Path p) {
        StringBuilder texto = new StringBuilder();
        if (Files.exists(p) && !Files.isDirectory(p)) {
            try {
                for (String s : Files.readAllLines(p)) {
                    texto.append(s);
                }
            } catch (FileNotFoundException e) {
                System.out.println("No existe");
            } catch (MalformedInputException e) {
                System.out.println("Comprueba que la codificación del archivo sea UTF-8");
            } catch (NoSuchFileException e) {
                System.out.println("El archivo no existe");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto.toString();
    }
}
