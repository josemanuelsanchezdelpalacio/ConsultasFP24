package ConsultasBD.LeerDatos.LeerPorFiltro;

import classes.Leer.DatosLeerFamily;
import classes.Leer.DatosLeerProjects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.FamilyEntity;
import entities.ProjectEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.nio.file.Path;
import java.util.List;

import static libs.FicheroEscribible.leerFichero;

public class LeerFamilyFiltrada {

    public static String filtrarFamily(){

        //Obtengo los datos del json con la familia a buscar
        Path p = Path.of("src/main/resources/jsonLeer/leerFamily.json");
        String textoJsonProjects = leerFichero(p);
        Gson gson = new GsonBuilder().create();
        DatosLeerFamily[] listaLeerFamilia = gson.fromJson(textoJsonProjects, DatosLeerFamily[].class);
        String jsonSalidaFamily = "";

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        for(DatosLeerFamily family: listaLeerFamilia){
            String nombre = family.getFamilyName();

            //Busco la familia por su nombre
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<FamilyEntity> cq = cb.createQuery(FamilyEntity.class);
            Root<FamilyEntity> root = cq.from(FamilyEntity.class);
            cq.select(root).where(cb.equal(root.get("familyName"), nombre));

            //Obtengo la lista de resultados
            List<FamilyEntity> listaFamilyEntity = em.createQuery(cq).getResultList();
            if(!listaFamilyEntity.isEmpty()){
                System.out.println("Familia encontrada: ");
                for(FamilyEntity familia : listaFamilyEntity) {

                    System.out.println("Id de la familia: " + familia.getId());
                    System.out.println("Código de la familia: " + familia.getFamilyCode());
                    System.out.println("Nombre de la familia: " + familia.getFamilyName());

                }
                jsonSalidaFamily = gson.toJson(listaFamilyEntity);
            }else{
                System.out.println("No se ha encontrado ningúna familia.");
            }
            em.close();
            emf.close();
        }
        return jsonSalidaFamily;
    }
}
