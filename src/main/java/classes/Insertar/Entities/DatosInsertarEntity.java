package classes.Insertar.Entities;

import java.util.ArrayList;

public class DatosInsertarEntity {
    private ArrayList<Entity> entities;

    public DatosInsertarEntity(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public DatosInsertarEntity() {
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
