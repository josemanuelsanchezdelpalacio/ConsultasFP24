package classes.Leer;

public class DatosLeerUser {
    private int id;
    private String userName;
    private String surname;

    public DatosLeerUser(int id, String userName, String surname) {
        this.id = id;
        this.userName = userName;
        this.surname = surname;
    }

    public DatosLeerUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
