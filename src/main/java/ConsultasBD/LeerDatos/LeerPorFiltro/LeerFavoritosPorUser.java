package ConsultasBD.LeerDatos.LeerPorFiltro;

import classes.Leer.DatosLeerEntity;
import classes.Leer.DatosLeerFavoritos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.EntityEntity;
import entities.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.nio.file.Path;
import java.util.List;

import static libs.FicheroEscribible.leerFichero;

public class LeerFavoritosPorUser {

    public static String filtrarFavoritos(){

        //Obtengo los datos del json con los favoritos a buscar
        Path p = Path.of("src/main/resources/jsonLeer/leerFavoritosUser.json");
        String textoJsonEntities = leerFichero(p);
        Gson gson = new GsonBuilder().create();
        DatosLeerFavoritos[] listaLeerFav = gson.fromJson(textoJsonEntities, DatosLeerFavoritos[].class);
        String jsonSalidaFavoritos = "";

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();


        for(DatosLeerFavoritos datosLeerFav: listaLeerFav){
            int idUser = datosLeerFav.getIdUser();

            //Busco los favoritos por el id de usuario
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<FavouriteEntity> cq = cb.createQuery(FavouriteEntity.class);
            Root<FavouriteEntity> root = cq.from(FavouriteEntity.class);
            cq.select(root).where(cb.equal(root.get("idUser"), idUser));

            //Obtengo la lista de resultados
            List<FavouriteEntity> listaFavouritesEntity = em.createQuery(cq).getResultList();
            if(!listaFavouritesEntity.isEmpty()){
                System.out.println("Favoritos encontrados: ");
                for(FavouriteEntity fav : listaFavouritesEntity) {

                    System.out.println("Id de favorito: " + fav.getId());
                    System.out.println("Id del proyecto: " + fav.getIdProject());
                    System.out.println("Id del usuario: " + fav.getIdUser() + "\n");

                }

                //Creo el json de salida
                jsonSalidaFavoritos = gson.toJson(listaFavouritesEntity);
            }else{
                System.out.println("El usuario no tiene favoritos.");
            }


        }
        //Devuelvo el json
        return jsonSalidaFavoritos;
    }

}
