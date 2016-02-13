/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueb;

import WSCarrera.ArrayOfEstudiante;
import WSCarrera.ArrayOfMateria;
import WSCarrera.ArrayOfMateriaPensum;
import WSCarrera.ArrayOfNotas;
import WSCarrera.Estudiante;
import WSCarrera.Materia;
import WSCarrera.MateriaPensum;
import WSCarrera.Notas;
import WSGeneral.ArrayOfEscuela;
import WSGeneral.ArrayOfFacultad;
import WSGeneral.ArrayOfUnidadAcademica;
import WSGeneral.Escuela;
import WSGeneral.Facultad;
import WSGeneral.Periodo;
import WSGeneral.UnidadAcademica;
import WSSeguridad.ArrayOfRolCarrera;
import WSSeguridad.RolCarrera;
import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.accesodatos.Parametro;
import ec.edu.espoch.sisplan.clases.CUsuario;
import ec.edu.espoch.sisplan.modelos.MLogin;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Usuario
 */
public class princi {

    private MenuModel model = new DefaultMenuModel();

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    ///model = new DefaultMenuModel();
    public static void main(String arg[]) {
        ArrayOfEstudiante e = getAlumnosMateria("EIS", "9", "A", "P0024", "IS15168");
        List<Estudiante> e1 = e.getEstudiante();

        for (Estudiante e2 : e1) {//facultad
            ArrayOfNotas n = getUltimasNotasEstudianteCarrera("EIS", e2.getCedula());
            List<Notas> n1 = n.getNotas();
            for (Notas n2 : n1) {
                if (n2.getCodMateria().equals("IS15168")) {
                    System.out.println("- " + e2.getApellidos() + "-" + e2.getNombres() + "-" + n2.getMateria() + "- \t "
                            + n2.getAcumulado());

                }
            }
        }

    }

    private static ArrayOfEstudiante getAlumnosMateria(java.lang.String strCodCarrera, java.lang.String strCodNivel, java.lang.String strCodParalelo, java.lang.String strCodPeriodo, java.lang.String strCodMateria) {
        WSCarrera.InfoCarrera service = new WSCarrera.InfoCarrera();
        WSCarrera.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getAlumnosMateria(strCodCarrera, strCodNivel, strCodParalelo, strCodPeriodo, strCodMateria);
    }

    private static ArrayOfNotas getUltimasNotasEstudianteCarrera(java.lang.String strCodCarrera, java.lang.String strCedula) {
        WSCarrera.InfoCarrera service = new WSCarrera.InfoCarrera();
        WSCarrera.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getUltimasNotasEstudianteCarrera(strCodCarrera, strCedula);
    }

}
