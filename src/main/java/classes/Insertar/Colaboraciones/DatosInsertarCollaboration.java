package classes.Insertar.Colaboraciones;

import java.util.ArrayList;

public class DatosInsertarCollaboration {
    private ArrayList<Colaboracion> collaborations;

    public DatosInsertarCollaboration(ArrayList<Colaboracion> collaborations) {
        this.collaborations = collaborations;
    }

    public DatosInsertarCollaboration() {
    }

    public ArrayList<Colaboracion> getCollaborations() {
        return collaborations;
    }

    public void setCollaborations(ArrayList<Colaboracion> collaborations) {
        this.collaborations = collaborations;
    }
}
