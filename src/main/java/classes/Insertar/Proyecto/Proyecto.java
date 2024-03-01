package classes.Insertar.Proyecto;

import java.util.Date;

public class Proyecto {

    private String Title;
    private int Logo;
    private String Web;
    private String Descripcion;
    private String State;
    private Date InitDate;
    private Date EndDate;
    private int IDUser;
    private boolean IsManager;
    private int IDFamily;


    public Proyecto() {
    }

    public Proyecto(String title, int logo, String web, String descripcion, String state, Date initDate, Date endDate, int idUser, boolean isManager, int idFamily) {
        Title = title;
        Logo = logo;
        Web = web;
        Descripcion = descripcion;
        State = state;
        InitDate = initDate;
        EndDate = endDate;
        IDUser = idUser;
        IsManager = isManager;
        IDFamily = idFamily;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public int getLogo() {
        return Logo;
    }

    public void setLogo(int logo) {
        this.Logo = logo;
    }

    public String getWeb() {
        return Web;
    }

    public void setWeb(String web) {
        this.Web = web;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Date getInitDate() {
        return InitDate;
    }

    public void setInitDate(Date initDate) {
        this.InitDate = initDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        this.EndDate = endDate;
    }

    public int getIdUser() {
        return IDUser;
    }

    public void setIdUser(int idUser) {
        IDUser = idUser;
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
