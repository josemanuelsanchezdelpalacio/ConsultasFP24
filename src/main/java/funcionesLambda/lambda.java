package funcionesLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import entities.EntityEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.*;

public class lambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        //obtengo la lista de entidades
        List<EntityEntity> entidadesLeer = obtenerEntidadesDesdeBaseDeDatos();

        //convierto la lista de entidades a JSON usando Gson
        Gson gson = new Gson();
        String bodyContent = gson.toJson(entidadesLeer);

        String output = String.format("{ \"message\": \"Entidades desde la base de datos:\", \"listaEntidades\": %s }", bodyContent);

        return response
                .withStatusCode(200)
                .withBody(output);
    }

    private List<EntityEntity> obtenerEntidadesDesdeBaseDeDatos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT e FROM EntityEntity e", EntityEntity.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al leer las entidades desde la base de datos");
            return Collections.emptyList();
        } finally {
            em.close();
            emf.close();
        }
    }
}
