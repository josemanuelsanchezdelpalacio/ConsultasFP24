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

public class lambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(headers);

        //obtengo proyectos desde la base de datos
        String bodyContent = obtenerProyectosDesdeBaseDeDatos();

        String output = String.format("{ \"message\": \"Proyectos:\", \"listaProyectos\": %s }", bodyContent);

        return response.withStatusCode(200).withBody(output);
    }

    public static String obtenerProyectosDesdeBaseDeDatos() {
        try (Connection conexion = ConexionMySQL.conectar("FP24MJO")) {
            List<Project> listaProyectos = new ArrayList<>();

            //consulto los datos de la tabla Project
            try (PreparedStatement ps = conexion.prepareStatement("SELECT * FROM PROJECT");
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Project proyecto = new Project();
                    proyecto.setTitle(rs.getString("Title"));
                    proyecto.setLogo(rs.getBytes("Logo"));
                    proyecto.setWeb(rs.getString("Web"));
                    proyecto.setProjectDescription(rs.getString("ProjectDescription"));
                    proyecto.setState(rs.getString("State"));
                    proyecto.setInitDate(rs.getDate("InitDate"));
                    proyecto.setEndDate(rs.getDate("EndDate"));

                    listaProyectos.add(proyecto);
                }
            }

            Projects proyectos = new Projects();
            proyectos.setProyectos(listaProyectos);

            //paso los datos a GSON
            Gson gson = new Gson();
            return gson.toJson(proyectos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
