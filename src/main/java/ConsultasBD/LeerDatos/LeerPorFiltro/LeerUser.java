package ConsultasBD.LeerDatos.LeerPorFiltro;

import classes.Leer.DatosLeerUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.nio.file.Path;
import java.util.List;

import static libs.FicheroEscribible.leerFichero;

public class LeerUser {

    public static String filtrarUser(){

        //Obtengo los datos del json con los entities a buscar
        Path p = Path.of("src/main/resources/jsonLeer/leerUser.json");
        String textoJsonUser = leerFichero(p);
        Gson gson = new GsonBuilder().create();
        DatosLeerUser[] listaLeerUser = gson.fromJson(textoJsonUser, DatosLeerUser[].class);
        String jsonSalidaUser = "";

        //Creo el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();


        for(DatosLeerUser datosLeerUser: listaLeerUser){
            int idUser = datosLeerUser.getId();
            String userName = datosLeerUser.getUserName();
            String surname = datosLeerUser.getSurname();

            //Busco los centros por su nombre
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<UsersEntity> cq = cb.createQuery(UsersEntity.class);
            Root<UsersEntity> root = cq.from(UsersEntity.class);
            cq.select(root).where(cb.equal(root.get("id"), idUser), cb.and(cb.equal(root.get("userName"), userName), cb.equal(root.get("surname"), surname)));

            //Obtengo la lista de resultados
            List<UsersEntity> listaUserEntity = em.createQuery(cq).getResultList();
            if(!listaUserEntity.isEmpty()){
                System.out.println("User encontrado: ");
                for(UsersEntity user : listaUserEntity) {

                    System.out.println("Id de usuario: " + user.getId());
                    System.out.println("Id del centro: " + user.getIdEntity());
                    System.out.println("Nombre del usuario: " + user.getUserName());
                    System.out.println("Apellido del usuario: " + user.getSurname());
                    System.out.println("Email del usuario: " + user.getEmail());
                    System.out.println("LinkedIn del usuario: " + user.getLinkedIn() + "\n");

                }
                jsonSalidaUser = gson.toJson(listaUserEntity);
            }else{
                System.out.println("El usuario no existe.");
            }
        }
        return textoJsonUser;
    }
}
