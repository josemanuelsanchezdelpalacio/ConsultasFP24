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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lambdaParametros implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        Map<String, String> queryStringParameters = input.getQueryStringParameters();
        String entityName = queryStringParameters.get("entityName");
        String entityCode = queryStringParameters.get("entityCode");
        String bodyContent = obtenerEntidadesDesdeBaseDeDatos(entityName, entityCode);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withHeaders(headers)
                .withBody(bodyContent);
    }

    public static String obtenerEntidadesDesdeBaseDeDatos(String entityName, String entityCode) {
        List<Entity> listEntidades = new ArrayList<>();
        try (Connection conexion = ConexionMySQL.conectar("FP24MJO")) {
            String query = "SELECT * FROM ENTITY WHERE EntityName = ? AND EntityCode = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, entityName);
                ps.setString(2, entityCode);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Entity entidad = new Entity();
                        entidad.setEntityName(rs.getString("EntityName"));
                        entidad.setEntityCode(rs.getString("EntityCode"));
                        entidad.setWeb(rs.getString("Web"));
                        entidad.setEmail(rs.getString("Email"));
                        listEntidades.add(entidad);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Entities entidades = new Entities();
        entidades.setEntidades(listEntidades);
        Gson gson = new Gson();
        return gson.toJson(entidades);
    }
}
