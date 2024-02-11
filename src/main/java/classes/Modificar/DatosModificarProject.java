package classes.Modificar;

import java.sql.Date;

public class DatosModificarProject {
    private int idProject;
    private String title;
    private String web;
    private String descripcion;
    private String estado;
    private Date finFecha;

    public DatosModificarProject(int idProject, String title, String web, String descripcion, String estado, Date finFecha) {
        this.idProject = idProject;
        this.title = title;
        this.web = web;
        this.descripcion = descripcion;
        this.estado = estado;
        this.finFecha = finFecha;
    }

    public DatosModificarProject() {

    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFinFecha() {
        return finFecha;
    }

    public void setFinFecha(Date finFecha) {
        this.finFecha = finFecha;
    }
}


