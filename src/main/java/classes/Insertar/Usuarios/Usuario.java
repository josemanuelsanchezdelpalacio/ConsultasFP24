package classes.Insertar.Usuarios;

public class Usuario {
    private int IdEntity;
    private String Login;
    private String UserName;
    private String Surname;
    private String Email;
    private String LinkedIn;

    public int getIdEntity() {
        return IdEntity;
    }

    public void setIdEntity(int idEntity) {
        this.IdEntity = idEntity;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        this.Login = login;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getLinkedIn() {
        return LinkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.LinkedIn = linkedIn;
    }

}
