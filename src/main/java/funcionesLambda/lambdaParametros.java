import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametrizedLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        //obtengo los parametros desde la solicitud
        String parametro1 = input.getQueryStringParameters().get("parametro1");
        String parametro2 = input.getQueryStringParameters().get("parametro2");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        //obtengo la lista de entidades con los parametros
        String bodyContent = obtenerEntidadesDesdeBaseDeDatos(parametro1, parametro2);

        String output = String.format("{ \"message\": \"Entidades:\", \"listaEntidades\": %s }", bodyContent);

        return response
                .withStatusCode(200)
                .withBody(output);
    }

    public static String obtenerEntidadesDesdeBaseDeDatos(String parametro1, String parametro2) {
        try (Connection conexion = ConexionMySQL.conectar("FP24MJO")) {
            List<Entity> listEntidades = new ArrayList<>();

            try (PreparedStatement ps = conexion.prepareStatement("SELECT * FROM ENTITY WHERE EntityName = ? AND EntityCode = ?");
                 ResultSet rs = ps.executeQuery()) {

                //configuro los valores de los parámetros en la consulta
                ps.setString(1, parametro1);
                ps.setString(2, parametro2);

                while (rs.next()) {
                    Entity entidad = new Entity();
                    entidad.setEntityName(rs.getString("EntityName"));
                    entidad.setEntityCode(rs.getString("EntityCode"));
                    entidad.setWeb(rs.getString("Web"));
                    entidad.setEmail(rs.getString("Email"));

                    listEntidades.add(entidad);
                }
            }

            //creo el objeto Entities y asigno la lista de entidades
            Entities entidades = new Entities();
            entidades.setEntidades(listEntidades);
            Gson gson = new Gson();
            return gson.toJson(entidades);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

