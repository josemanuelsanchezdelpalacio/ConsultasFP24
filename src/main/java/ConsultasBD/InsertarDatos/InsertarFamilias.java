package ConsultasBD.InsertarDatos;

import Singleton.EmfSingleton;
import classes.Insertar.DatosInsertarFamily;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import conexiones.ConexionMySQL;
import entities.FamilyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.FicheroEscribible;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertarFamilias {

    public static ArrayList<DatosInsertarFamily> familiasInsertar = new ArrayList<>();

    public static void insertarFamilias() {
        Path p = Path.of("src/main/resources/jsonTablas/insertFamily.json");

        if (FicheroEscribible.ficheroLegible(p)) {
            try {
                Gson gson = new Gson();
                familiasInsertar = gson.fromJson(new FileReader(p.toFile()), new TypeToken<ArrayList<DatosInsertarFamily>>() {
                }.getType());

                try (Connection con = ConexionMySQL.conectar("FP24MJO")) {
                    for (DatosInsertarFamily datos : familiasInsertar) {
                        if (datos.getFamilyName() != null) {
                            if (!existeFamilia(con, datos.getFamilyCode())) {
                                String query = "INSERT INTO FAMILY (FamilyCode, FamilyName) VALUES (?, ?)";
                                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                                    pstmt.setString(1, datos.getFamilyCode());
                                    pstmt.setString(2, datos.getFamilyName());
                                    pstmt.executeUpdate();
                                    System.out.println("Familia con código " + datos.getFamilyCode() + " ha sido insertada correctamente");
                                }
                            } else {
                                System.out.println("Familia con código " + datos.getFamilyCode() + " ya existe, no se insertará.");
                            }
                        } else {
                            System.out.println("FamilyName no puede ser null. No se insertará la familia con código " + datos.getFamilyCode());
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error al insertar datos en la tabla FAMILY");
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los datos del fichero");
            }
        }
    }

    // Método auxiliar para verificar si ya existe una familia con el mismo FamilyCode
    private static boolean existeFamilia(Connection con, String familyCode) {
        String query = "SELECT COUNT(*) FROM FAMILY WHERE FamilyCode = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, familyCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
