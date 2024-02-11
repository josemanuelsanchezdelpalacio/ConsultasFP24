import CSVtoXML.LeerCSV_Centros;
import CSVtoXML.LeerCSV_Proyectos;
import ConsultasBD.InsertarDatos.*;
import ConsultasBD.LeerDatos.*;
import ConsultasBD.ModificarDatos.ModificarEntity;
import ConsultasBD.ModificarDatos.ModificarProject;
import ConsultasBD.ModificarDatos.ModificarUser;
import ConsultasXQuery.ConsultaCentros;
import ConsultasXQuery.ConsultaFamilias;
import ConsultasXQuery.ConsultaProyectos;
import DatosXMLaBD.InsertarCentros;
import DatosXMLaBD.InsertarFamilias;
import DatosXMLaBD.InsertarProyectos;
import ConsultasBD.EliminarDatosBD.EliminarCentros;
import ConsultasBD.EliminarDatosBD.EliminarFamilias;
import ConsultasBD.EliminarDatosBD.EliminarProject;
import ManejoDB.CreacionTablas;
import XML_a_ExistDB.SubirArchivosExistDB;
import libs.Leer;

public class Main {

    public static void main(String[] args) {
        boolean salir = false;
        int opcion;

        do {
            System.out.println("1. Consultas eXistDB");
            System.out.println("2. Consultas, Leer, Modificar, Insertar, Eliminar");
            System.out.println("0. Salir");

            opcion = Leer.pedirEntero("Introduce una opcion: ");

            switch (opcion) {
                case 1 -> menuExistDB();
                case 2 -> menuConsultas();
                case 0 -> {
                    System.out.println("Salir");
                    salir = true;
                }
                default -> System.out.println("Opción no válida");
            }

        } while (!salir);
    }

    private static void menuExistDB() {
        int opcion;

        do {
            System.out.println("1. Crear tablas");
            System.out.println("2. Transformar de csv a xml");
            System.out.println("3. Subir archivos XML a la base de datos eXist");
            System.out.println("4. Exportar datos XQuery a XML");
            System.out.println("5. Insertar datos de XML a BD");
            System.out.println("0. Volver al menu principal");

            opcion = Leer.pedirEntero("Introduce una opcion: ");

            switch (opcion) {
                case 1 -> {
                    //creación de las tablas
                    CreacionTablas.crear();
                    System.out.println("Tablas creadas");
                }
                case 2 -> {
                    //leer proyectos desde CSV y guardar en XML
                    LeerCSV_Centros.leerCentros();
                    LeerCSV_Proyectos.leerProyectos();
                }
                case 3 -> {
                    //subir archivos XML a la base de datos eXist
                    SubirArchivosExistDB.subirArchivos();
                }
                case 4 -> {
                    //consultas eXist-DB y creación de archivos XML
                    ConsultaCentros.listarCentros();
                    ConsultaProyectos.listarProyectos();
                    ConsultaFamilias.listarFamilias();
                }
                case 5 -> {
                    //insertar los datos a las tablas
                    InsertarCentros.insertar();
                    InsertarFamilias.insertar();
                    InsertarProyectos.insertar();
                }
            }
        } while (opcion != 0);
    }

    private static void menuConsultas() {
        int opcion;

        do {
            System.out.println("1. Leer Entidades");
            System.out.println("2. Leer Colaboraciones");
            System.out.println("3. Leer Familias");
            System.out.println("4. Leer Favoritos");
            System.out.println("5. Leer Implementaciones");
            System.out.println("6. Leer Proyectos");
            System.out.println("7. Leer Tecnologías");
            System.out.println("8. Leer Usuarios");
            System.out.println("9. Insertar Familias");
            System.out.println("10. Insertar Proyectos");
            System.out.println("11. Insertar Entidades");
            System.out.println("12. Insertar Usuarios");
            System.out.println("13. Insertar Tecnologías");
            System.out.println("14. Insertar Favoritos");
            System.out.println("15. Insertar Implementaciones");
            System.out.println("16. Insertar Colaboraciones");
            System.out.println("17. Eliminar Centro");
            System.out.println("18. Eliminar Familia");
            System.out.println("19. Eliminar Proyecto");
            System.out.println("20. Modificar entidades");
            System.out.println("21. Modificar proyectos");
            System.out.println("22. Modificar usuarios");
            System.out.println("23. Leer JSON");
            System.out.println("24. Subir Proyectos");
            System.out.println("0. Volver al menú principal");

            opcion = Leer.pedirEntero("Introduce una opcion: ");

            switch (opcion) {
                case 1 -> LeerEntidades.leerEntidades();
                case 2 -> LeerColaboraciones.leerColaboraciones();
                case 3 -> LeerFamilias.leerFamilias();
                case 4 -> LeerFavoritos.leerFavoritos();
                case 5 -> LeerImplementaciones.leerImplementaciones();
                case 6 -> LeerProyectos.leerProyectos();
                case 7 -> LeerTecnologias.leerTecnologias();
                case 8 -> LeerUsuarios.leerUsuarios();

                case 9 -> ConsultasBD.InsertarDatos.InsertarFamilias.insertarFamilias();
                case 10 -> ConsultasBD.InsertarDatos.InsertarProyectos.insertarProyectos();
                case 11 -> InsertarEntidades.insertarEntidades();
                case 12 -> InsertarUsuarios.insertarUsuarios();
                case 13 -> InsertarTecnologias.insertarTecnologias();
                case 14 -> InsertarFavoritos.insertarFavoritos();
                case 15 -> InsertarImplementaciones.insertarImplementaciones();
                case 16 -> InsertarColaboraciones.insertarColaboraciones();

                case 17 -> EliminarCentros.eliminarCentro();
                case 18 -> EliminarFamilias.eliminarFamilia();
                case 19 -> EliminarProject.eliminarProject();

                case 20 -> ModificarEntity.modificar();
                case 21 -> ModificarProject.modificar();
                case 22 -> ModificarUser.modificar();

                case 23 -> DTO.leerJson.leer();
                case 24 -> EntityaBD.ProjectsBD.subirProyectos();
                case 0 -> System.out.println("Volviendo al menu principal");
                default -> System.out.println("Opcion no válida");
            }

        } while (opcion != 0);
    }
}
