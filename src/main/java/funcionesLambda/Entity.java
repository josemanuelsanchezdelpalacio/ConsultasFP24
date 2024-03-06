package funcionesLambda;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity {

    @XmlElement
    private String entityName;

    @XmlElement
    private String entityCode;

    @XmlElement
    private String web;

    @XmlElement
    private String email;

    public Entity(String entityName, String entityCode, String web, String email) {
        this.entityName = entityName;
        this.entityCode = entityCode;
        this.web = web;
        this.email = email;
    }

    public Entity() {

    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
