package classes;

import java.util.Date;

public class DatosNewProject {
    private String Title;
    private int Logo;
    private String Descripcion;
    private String Web;
    private Date InitDate;
    private Date EndDate;
    private String State;
    private int IDUser;
    private boolean IsManager;
    private int IDFamily;

    public DatosNewProject(String title, int logo, String descripcion, String web, Date initDate, Date endDate, String state, int IDUser, boolean isManager, int IDFamily) {
        Title = title;
        Logo = logo;
        Descripcion = descripcion;
        Web = web;
        InitDate = initDate;
        EndDate = endDate;
        State = state;
        this.IDUser = IDUser;
        IsManager = isManager;
        this.IDFamily = IDFamily;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getLogo() {
        return Logo;
    }

    public void setLogo(int logo) {
        Logo = logo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getWeb() {
        return Web;
    }

    public void setWeb(String web) {
        Web = web;
    }

    public Date getInitDate() {
        return InitDate;
    }

    public void setInitDate(Date initDate) {
        InitDate = initDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public boolean isManager() {
        return IsManager;
    }

    public void setManager(boolean manager) {
        IsManager = manager;
    }

    public int getIDFamily() {
        return IDFamily;
    }

    public void setIDFamily(int IDFamily) {
        this.IDFamily = IDFamily;
    }
}
