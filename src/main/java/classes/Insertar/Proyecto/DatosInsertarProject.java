package classes.Insertar.Proyecto;

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
