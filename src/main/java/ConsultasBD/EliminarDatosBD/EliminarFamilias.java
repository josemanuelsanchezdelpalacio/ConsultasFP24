package ConsultasBD.EliminarDatosBD;

import Singleton.EmfSingleton;
import classes.Eliminar.DatosEliminarFamilias;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.FamilyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EliminarFamilias {
    public static ArrayList<DatosEliminarFamilias> familiasEliminiar = new ArrayList<>();
    public static void eliminarFamilia() {
        Path p = Path.of("src/main/resources/jsonEliminar/eliminarFamilias.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                familiasEliminiar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosEliminarFamilias>>() {
                }.getType());
                EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                for (DatosEliminarFamilias datos : familiasEliminiar) {
                    Query query = em.createQuery("select f from FamilyEntity f where f.familyCode = :code");
                    query.setParameter("code", datos.getFamilyCode());
                    List<FamilyEntity> result = query.getResultList();
                    if (result.isEmpty()) {
                        System.out.println("La familia " + datos.getFamilyName() + " con código " + datos.getFamilyCode() + " no existe");
                    } else {
                        em.remove(result.get(0));
                        System.out.println("La familia " + datos.getFamilyName() + " con código " + datos.getFamilyCode() + " ha sido eliminado correctamente");
                    }
                }
                em.getTransaction().commit();
                em.close();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }

        }
    }
}


