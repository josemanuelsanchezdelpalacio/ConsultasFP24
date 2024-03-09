package classes.Leer;

public class DatosLeerFamily {

    private String FamilyName;

    public DatosLeerFamily() {
    }

    public DatosLeerFamily(String familyName) {

        this.FamilyName = familyName;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        this.FamilyName = familyName;
    }
}
