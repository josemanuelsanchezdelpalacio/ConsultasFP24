package classes.Leer.Projects;

import classes.Leer.Projects.DatosLeerProjects;

import java.util.ArrayList;

public class ListaLeerProjects {
    private ArrayList<DatosLeerProjects> projects;

    public ListaLeerProjects() {
    }

    public ListaLeerProjects(ArrayList<DatosLeerProjects> projects) {
        this.projects = projects;
    }

    public ArrayList<DatosLeerProjects> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<DatosLeerProjects> projects) {
        this.projects = projects;
    }
}
