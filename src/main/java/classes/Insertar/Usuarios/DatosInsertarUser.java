package classes.Insertar.Usuarios;

import java.util.ArrayList;

public class DatosInsertarUser {
    private ArrayList<Usuario> users;

    public DatosInsertarUser(ArrayList<Usuario> users) {
        this.users = users;
    }

    public DatosInsertarUser() {
    }

    public ArrayList<Usuario> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Usuario> users) {
        this.users = users;
    }
}
