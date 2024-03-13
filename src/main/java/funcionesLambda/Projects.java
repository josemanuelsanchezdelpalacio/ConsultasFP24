package funcionesLambda;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projects {

    @XmlElement(name = "proyectos")
    private List<Project> proyectos;

    public List<Project> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Project> proyectos) {
        this.proyectos = proyectos;
    }
}
