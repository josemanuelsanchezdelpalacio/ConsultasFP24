package ConsultasBD.LeerDatos.LeerPorFiltro;

import classes.Leer.DatosLeerEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.EntityEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.nio.file.Path;
import java.util.List;

import static libs.FicheroEscribible.leerFichero;

public class LeerEntityFiltrado {

    public static String filtrarEntity(){

        //Obtengo los datos del json con los entities a buscar
        Path p = Path.of("src/main/resources/jsonLeer/leerEntities.json");
        String textoJsonEntities = leerFichero(p);
        Gson gson = new GsonBuilder().create();
        DatosLeerEntity[] listaLeerEntities = gson.fromJson(textoJsonEntities, DatosLeerEntity[].class);
        String jsonSalidaEntities = "";

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();


        for(DatosLeerEntity datosLeer: listaLeerEntities){
            String title = datosLeer.getEntityName();

            //Busco los centros por su nombre
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<EntityEntity> cq = cb.createQuery(EntityEntity.class);
            Root<EntityEntity> root = cq.from(EntityEntity.class);
            cq.select(root).where(cb.equal(root.get("entityName"), title));

            //Obtengo la lista de resultados
            List<EntityEntity> listaEntitiesEntity = em.createQuery(cq).getResultList();
            if(!listaEntitiesEntity.isEmpty()){

                //Creo el json de salida
                jsonSalidaEntities = gson.toJson(listaEntitiesEntity);
            }else{
                System.out.println("No se ha encontrado ningún centro.");
            }


        }
        //Devuelvo el json
        return jsonSalidaEntities;
    }
}
