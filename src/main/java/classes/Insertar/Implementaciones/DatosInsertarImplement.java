package classes.Insertar.Implementaciones;

import java.util.ArrayList;

public class DatosInsertarImplement {
    private ArrayList<Implementacion> implementations;

    public DatosInsertarImplement(ArrayList<Implementacion> implementations) {
        this.implementations = implementations;
    }

    public DatosInsertarImplement() {
    }

    public ArrayList<Implementacion> getImplementations() {
        return implementations;
    }

    public void setImplementations(ArrayList<Implementacion> implementations) {
        this.implementations = implementations;
    }
}
