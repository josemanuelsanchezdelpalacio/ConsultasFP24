package classes.Insertar.Families;

public class Family {
    private String FamilyCode;
    private String FamilyName;

    public Family() {
    }

    public Family(String familyCode, String familyName) {
        this.FamilyCode = familyCode;
        this.FamilyName = familyName;
    }

    public String getFamilyCode() {
        return FamilyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.FamilyCode = familyCode;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        this.FamilyName = familyName;
    }
}
