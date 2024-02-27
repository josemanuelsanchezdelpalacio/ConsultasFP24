package classes.Insertar.Favourites;

import java.util.ArrayList;

public class DatosInsertarFavourite {

    private ArrayList<Favourite> favourites;

    public DatosInsertarFavourite(ArrayList<Favourite> favourites) {
        this.favourites = favourites;
    }

    public DatosInsertarFavourite() {
    }

    public ArrayList<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Favourite> favourites) {
        this.favourites = favourites;
    }
}
