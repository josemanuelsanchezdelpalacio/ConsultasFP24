package classes.Insertar.Tecnologias;

import java.util.ArrayList;

public class DatosInsertarTechnology {
    private ArrayList<Tecnologia> technologies;

    public DatosInsertarTechnology(ArrayList<Tecnologia> technologies) {
        this.technologies = technologies;
    }

    public DatosInsertarTechnology() {
    }

    public ArrayList<Tecnologia> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(ArrayList<Tecnologia> technologies) {
        this.technologies = technologies;
    }
}
