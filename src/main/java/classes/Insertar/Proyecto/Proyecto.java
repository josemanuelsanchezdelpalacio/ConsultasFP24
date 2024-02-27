package classes.Insertar.Proyecto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.Date;

public class Proyecto {

    private String Title;
    private int Logo;
    private String Web;
    private String Descripcion;
    private String State;
    private Date InitDate;
    private Date EndDate;


    public Proyecto() {
    }

    public Proyecto(String title, int logo, String web, String Descripcion, String state, Date initDate, Date endDate) {
        this.Title = title;
        this.Logo = logo;
        this.Web = web;
        this.Descripcion = Descripcion;
        this.State = state;
        this.InitDate = initDate;
        this.EndDate = endDate;
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
}
