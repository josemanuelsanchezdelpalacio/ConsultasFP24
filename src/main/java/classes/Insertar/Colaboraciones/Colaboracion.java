package classes.Insertar.Colaboraciones;

public class Colaboracion {
    private int IdProject;
    private int IdUser;
    private int IdFamily;
    private boolean IsManager;

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

    public int getIdFamily() {
        return IdFamily;
    }

    public void setIdFamily(int idFamily) {
        this.IdFamily = idFamily;
    }

    public boolean isManager() {
        return IsManager;
    }

    public void setManager(boolean manager) {
        IsManager = manager;
    }
}
