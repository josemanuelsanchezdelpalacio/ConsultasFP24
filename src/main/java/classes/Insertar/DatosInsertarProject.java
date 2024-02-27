package classes.Insertar;

import classes.Proyecto;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.sql.Date;
import java.util.ArrayList;

public class DatosInsertarProject {
    private ArrayList<Proyecto> proyectos;

    public DatosInsertarProject() {
    }

    public DatosInsertarProject(ArrayList<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(ArrayList<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
}
