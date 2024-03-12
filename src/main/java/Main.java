import ConsultasBD.EliminarDatosBD.EliminarCentros;
import ConsultasBD.EliminarDatosBD.EliminarFamilias;
import ConsultasBD.EliminarDatosBD.EliminarProject;
import ConsultasBD.InsertarDatos.*;
import ConsultasBD.LeerDatos.LeerPorFiltro.*;
import ConsultasBD.LeerDatos.LeerTablaCompleta.*;
import ConsultasBD.ModificarDatos.ModificarEntity;
import ConsultasBD.ModificarDatos.ModificarProject;
import ConsultasBD.ModificarDatos.ModificarUser;
import libs.Leer;

public class Main {

    public static void main(String[] args) {
        boolean salir = false;
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
            System.out.println("9. Leer Proyect por filtro");
            System.out.println("10. Leer Entidad por filtro");
            System.out.println("11. Leer Familia por filtro");
            System.out.println("12. Leer Colaboraciónes por proyecto");
            System.out.println("13. Leer Favoritos por usuario");
            System.out.println("14. Leer Usuario");

            System.out.println("18. Insertar Familias");
            System.out.println("19. Insertar Proyectos");
            System.out.println("20. Insertar Entidades");
            System.out.println("21. Insertar Usuarios");
            System.out.println("22. Insertar Tecnologías");
            System.out.println("23. Insertar Favoritos");
            System.out.println("24. Insertar Implementaciones");
            System.out.println("25. Insertar Colaboraciones");
            System.out.println("26. Eliminar Centro");
            System.out.println("27. Eliminar Familia");
            System.out.println("28. Eliminar Proyecto");
            System.out.println("29. Modificar entidades");
            System.out.println("30. Modificar proyectos");
            System.out.println("31. Modificar usuarios");

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
                case 9 -> LeerProyectoFiltrado.filtrarProyecto();
                case 10 -> LeerEntityFiltrado.filtrarEntity();
                case 11 -> LeerFamilyFiltrada.filtrarFamily();
                case 12 -> LeerCollaborationsProject.filtarCollaboratiosPorProject();
                case 13 -> LeerFavoritosPorUser.filtrarFavoritos();
                case 14 -> LeerUser.filtrarUser();
                case 18 -> InsertarFamilias.insertarFamilias();
                case 19 -> InsertarProyectos.insertarProyectos();
                case 20 -> InsertarEntidades.insertarEntidades();
                case 21 -> InsertarUsuarios.insertarUsuarios();
                case 22 -> InsertarTecnologias.insertarTecnologias();
                case 23 -> InsertarFavoritos.insertarFavoritos();
                case 24 -> InsertarImplementaciones.insertarImplementaciones();
                case 25 -> InsertarColaboraciones.insertarColaboraciones();

                case 26 -> EliminarCentros.eliminarCentro();
                case 27 -> EliminarFamilias.eliminarFamilia();
                case 28 -> EliminarProject.eliminarProject();

                case 29 -> ModificarEntity.modificar();
                case 30 -> ModificarProject.modificar();
                case 31 -> ModificarUser.modificar();

                case 0 -> salir = true;
                default -> System.out.println("Opcion no valida");
            }
        } while (!salir);
    }
}
