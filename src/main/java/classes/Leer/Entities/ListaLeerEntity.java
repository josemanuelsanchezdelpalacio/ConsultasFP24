package classes.Leer.Entities;

import java.util.ArrayList;

public class ListaLeerEntity {

    private ArrayList<DatosLeerEntity> entities;

    public ListaLeerEntity() {
    }

    public ListaLeerEntity(ArrayList<DatosLeerEntity> entities) {
        this.entities = entities;
    }

    public ArrayList<DatosLeerEntity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<DatosLeerEntity> entities) {
        this.entities = entities;
    }
}
