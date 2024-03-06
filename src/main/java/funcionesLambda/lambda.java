package funcionesLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import conexiones.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class lambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        // Obtener la lista de entidades desde la base de datos
        String bodyContent = obtenerEntidadesDesdeBaseDeDatos();

        String output = String.format("{ \"message\": \"Entidades desde la base de datos:\", \"listaEntidades\": %s }", bodyContent);

        return response
                .withStatusCode(200)
                .withBody(output);
    }

    public static String obtenerEntidadesDesdeBaseDeDatos() {
        try (Connection conexion = ConexionMySQL.conectar("FP24MJO")) {
            List<Entity> listEntidades = new ArrayList<>();

            // Obtener datos de la tabla Entity
            try (PreparedStatement ps = conexion.prepareStatement("SELECT * FROM Entity");
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Entity entidad = new Entity();
                    entidad.setEntityName(rs.getString("entityName"));
                    entidad.setEntityCode(rs.getString("entityCode"));
                    entidad.setWeb(rs.getString("web"));
                    entidad.setEmail(rs.getString("email"));

                    listEntidades.add(entidad);
                }
            }

            // Crear el objeto Entities y asignar la lista de entidades
            Entities entidades = new Entities();
            entidades.setEntidades(listEntidades);

            // Convertir el objeto Entities a JSON
            Gson gson = new Gson();
            return gson.toJson(entidades);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
