/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.modelos;

import WSCarrera.ArrayOfMateriaPensum;
import WSCarrera.MateriaPensum;
import WSGeneral.ArrayOfEscuela;
import WSGeneral.ArrayOfFacultad;
import WSGeneral.ArrayOfUnidadAcademica;
import WSGeneral.Carrera;
import WSGeneral.Escuela;
import WSGeneral.Facultad;
import WSGeneral.UnidadAcademica;
import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.accesodatos.Parametro;
import ec.edu.espoch.sisplan.utilidades.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Mfacultad {

    public static boolean insertarFacultades() throws Exception {

        boolean respuesta = false;

        String escuelaCodigo = "";
        try {
            //sacar codigo del rol
            //guardar facultades
            ArrayOfFacultad facul = getFacultadesTotales();
            String nombre = "";
            List<Facultad> fac = facul.getFacultad();
            for (Facultad fac1 : fac) {
                // nombre = fac1.getNombre();               
                //System.out.println(nombre);
                String sql = "SELECT sisplan.fn_insert_facultad(?,?)";
                ArrayList<Parametro> lstParamDatosUsuario = new ArrayList<>();
                lstParamDatosUsuario.add(new Parametro(1, fac1.getCodigo()));
                lstParamDatosUsuario.add(new Parametro(2, fac1.getNombre()));
                ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUsuario);
                while (rs.next()) {
                    if (rs.getBoolean(0)) {
                        respuesta = true;
                    }
                }
            }
            //mensaje
            Util.addSuccessMessage("Datos insertados!");

        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }

    //cargar facultades
    public static boolean insertarEscuela() throws Exception {
        boolean respuesta = false;
        List<Facultad> lstFacultad = new ArrayList<>();
        //ArrayOfEscuela esc = getTodasEscuelas();//modificar escuelas totales
        ArrayOfEscuela esc = getTodasEscuelasTotales();//modificar escuelas totales
        
        List<Escuela> es = esc.getEscuela();
        try {
            String sql = "SELECT *FROM sisplan.fn_select_facultad()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                Facultad facul = new Facultad();
                facul.setCodigoint(rs.getInt(0));
                facul.setCodigo(rs.getString(1));
                //facul.setNombre(rs.getString(2));
                //CMaterialBodega material = new CMaterialBodega();
                //  material.setCodigo(rs.getLong(0));
                for (Escuela es1 : es) {
                    if (es1.getCodFacultad().equals(facul.getCodigo())) {
                        //inicio insertar escuelas
                        String sql1 = "SELECT sisplan.fn_insert_escuela(?,?,?)";//insert escuela
                        ArrayList<Parametro> lstParamEsc = new ArrayList<>();
                        lstParamEsc.add(new Parametro(1, es1.getCodigo()));
                        lstParamEsc.add(new Parametro(2, es1.getNombre()));
                        lstParamEsc.add(new Parametro(3, facul.getCodigoint()));

                        ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1, lstParamEsc);
                        while (rs1.next()) {
                            if (rs1.getBoolean(0)) {
                                respuesta = true;
                            }
                        }

                        // fin insertar
                    }

                }
            }

        } catch (Exception e) {
            lstFacultad.clear();
            throw e;
        }
        return respuesta;

    }

    //insertar carrera
    public static boolean insertarCarrera() throws Exception {
        boolean respuesta = false;
        //ArrayOfUnidadAcademica ua = getTodasCarreras();
        ArrayOfUnidadAcademica ua = getTodasCarrerasTotales();
        List<UnidadAcademica> ua1 = ua.getUnidadAcademica();

        try {
            String sql = "SELECT *FROM sisplan.fn_select_escuela()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                Escuela esc = new Escuela();
                esc.setCodigoint(rs.getInt(0));
                esc.setCodigo(rs.getString(1));
                //facul.setNombre(rs.getString(2));
                //CMaterialBodega material = new CMaterialBodega();
                //  material.setCodigo(rs.getLong(0));

                for (UnidadAcademica ua2 : ua1) {
                    if (ua2.getCodEscuela().equals(esc.getCodigo())) {
                        //inicio insertar escuelas
                        String sql1 = "SELECT sisplan.fn_insert_carrera(?,?,?)";//insert carrera
                        ArrayList<Parametro> lstParamEsc = new ArrayList<>();
                        lstParamEsc.add(new Parametro(1, ua2.getCodigo()));
                        lstParamEsc.add(new Parametro(2, ua2.getNombre()));
                        lstParamEsc.add(new Parametro(3, esc.getCodigoint()));

                        ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1, lstParamEsc);
                        while (rs1.next()) {
                            if (rs1.getBoolean(0)) {
                                respuesta = true;
                            }
                        }

                        // fin insertar
                    }

                }
            }

        } catch (Exception e) {
            //lstFacultad.clear();
            throw e;
        }
        return respuesta;

    }

    // insertar Materias
    public static boolean insertarMaterias() throws Exception {
        boolean respuesta = false;

        try {
            String sql = "SELECT *FROM sisplan.fn_select_carrera()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                Carrera esc = new Carrera();
                esc.setCodigoint(rs.getInt(0));
                esc.setCodigo(rs.getString(1));

                 if (getMallaCurricularPensumVigenteSinDescripcion(esc.getCodigo()) != null) {
                ArrayOfMateriaPensum mp = getMallaCurricularPensumVigenteSinDescripcion(esc.getCodigo());
                List<MateriaPensum> mp1 = mp.getMateriaPensum();
               
                    for (MateriaPensum mp2 : mp1) {

                        //inicio insertar escuelas
                        String sql1 = "SELECT sisplan.fn_insert_materias(?,?,?,?,?,?,?)";//insert carrera
                        ArrayList<Parametro> lstParamEsc = new ArrayList<>();
                        lstParamEsc.add(new Parametro(1, mp2.getCodMateria()));
                        lstParamEsc.add(new Parametro(2, mp2.getMateria()));
                        lstParamEsc.add(new Parametro(3, mp2.getNivel()));
                        lstParamEsc.add(new Parametro(4, mp2.getArea()));
                        lstParamEsc.add(new Parametro(5, mp2.getHorasPracticas() + mp2.getHorasTeoricas()));
                        lstParamEsc.add(new Parametro(6, mp2.getCreditos()));
                        lstParamEsc.add(new Parametro(7, esc.getCodigoint()));

                        ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1, lstParamEsc);
                        while (rs1.next()) {
                            if (rs1.getBoolean(0)) {
                                respuesta = true;
                            }
                        } // fin insertar
                    }
                }
            }

        } catch (Exception e) {
            //lstFacultad.clear();
            throw e;
        }
        return respuesta;

    }
    
    private static ArrayOfFacultad getFacultadesTotales() {
        WSGeneral.InfoGeneral service = new WSGeneral.InfoGeneral();
        WSGeneral.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getFacultadesTotales();
    }

    private static ArrayOfEscuela getTodasEscuelas() {
        WSGeneral.InfoGeneral service = new WSGeneral.InfoGeneral();
        WSGeneral.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasEscuelas();
    }

    private static ArrayOfUnidadAcademica getTodasCarreras() {
        WSGeneral.InfoGeneral service = new WSGeneral.InfoGeneral();
        WSGeneral.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasCarreras();
    }

    private static ArrayOfMateriaPensum getMallaCurricularPensumVigenteSinDescripcion(java.lang.String strCodCarrera) {
        WSCarrera.InfoCarrera service = new WSCarrera.InfoCarrera();
        WSCarrera.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMallaCurricularPensumVigenteSinDescripcion(strCodCarrera);
    }

    private static ArrayOfEscuela getTodasEscuelasTotales() {
        WSGeneral.InfoGeneral service = new WSGeneral.InfoGeneral();
        WSGeneral.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasEscuelasTotales();
    }

    private static ArrayOfUnidadAcademica getTodasCarrerasTotales() {
        WSGeneral.InfoGeneral service = new WSGeneral.InfoGeneral();
        WSGeneral.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getTodasCarrerasTotales();
    }

}
