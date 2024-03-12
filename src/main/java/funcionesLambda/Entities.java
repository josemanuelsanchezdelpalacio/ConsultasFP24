package funcionesLambda;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entities {

    @XmlElement(name = "entidades")
    private List<Entity> entidades;

    public List<Entity> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<Entity> entidades) {
        this.entidades = entidades;
    }
}