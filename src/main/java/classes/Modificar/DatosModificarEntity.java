package classes.Modificar;

public class DatosModificarEntity {
    private int idEntity;
    private String web;
    private String email;

    public DatosModificarEntity(int idEntity, String web, String email) {
        this.idEntity = idEntity;
        this.web = web;
        this.email = email;
    }

    public DatosModificarEntity() {


    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
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
