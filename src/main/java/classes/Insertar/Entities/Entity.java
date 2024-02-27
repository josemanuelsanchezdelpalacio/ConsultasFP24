package classes.Insertar.Entities;

public class Entity {

    private String entityName;
    private String entityCode;
    private String web;
    private String email;

    public Entity() {
    }

    public Entity(String entityName, String entityCode, String web, String email) {
        this.entityName = entityName;
        this.entityCode = entityCode;
        this.web = web;
        this.email = email;
    }


    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
