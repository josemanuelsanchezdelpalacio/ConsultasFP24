package classes.Modificar;

public class DatosModificarUser {
    private int idUser;
    private int idEntity;
    private String userName;
    private String surname;
    private String email;
    private String linkedin;

    public DatosModificarUser(int idUser, int idEntity, String userName, String surname, String email, String linkedin) {
        this.idUser = idUser;
        this.idEntity = idEntity;
        this.userName = userName;
        this.surname = surname;
        this.email = email;
        this.linkedin = linkedin;
    }

    public DatosModificarUser() {


    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}


