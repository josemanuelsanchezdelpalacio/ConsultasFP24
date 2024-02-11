package classes;

import java.util.ArrayList;
import java.util.List;

public class DatosNewProjects {
    public ArrayList<DatosNewProject> projects = new ArrayList<>();

    public DatosNewProjects(ArrayList<DatosNewProject> projects) {
        this.projects = projects;
    }

    public DatosNewProjects() {

    }

    public ArrayList<DatosNewProject> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<DatosNewProject> projects) {
        this.projects = projects;
    }
}
