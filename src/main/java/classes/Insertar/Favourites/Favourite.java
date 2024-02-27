package classes.Insertar.Favourites;

public class Favourite {
    private int IdProject;
    private int IdUser;

    public Favourite(int idProject, int idUser) {
        IdProject = idProject;
        IdUser = idUser;
    }

    public Favourite() {
    }

    public int getIdProject() {
        return IdProject;
    }

    public void setIdProject(int idProject) {
        this.IdProject = idProject;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        this.IdUser = idUser;
    }
}
