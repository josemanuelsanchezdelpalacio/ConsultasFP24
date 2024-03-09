package classes.Leer.Entities;

public class DatosLeerEntity {

    private String entityName;

    public DatosLeerEntity(String entityName) {
        this.entityName = entityName;
    }

    public DatosLeerEntity() {
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
