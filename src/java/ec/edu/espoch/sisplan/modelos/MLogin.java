/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.modelos;

import WSCarrera.ArrayOfMateria;
import WSCarrera.Materia;
import WSCarrera.Persona;
import WSGeneral.Periodo;
import WSSeguridad.ArrayOfRolCarrera;
import WSSeguridad.RolCarrera;
import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.accesodatos.Parametro;
import ec.edu.espoch.sisplan.clases.CMateria;
import ec.edu.espoch.sisplan.clases.CRol;
import ec.edu.espoch.sisplan.clases.CUsuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tupac
 */
public class MLogin {

    //comprobacion
    public static Boolean comprobacionRegistro(CUsuario objUsuario) throws Exception {
        Boolean correcto = false;

        String sql = "SELECT * FROM sisplan.fn_Verifico_tusuario(?) ";
        ArrayList<Parametro> lstParam = new ArrayList<>();
        lstParam.add(new Parametro(1, objUsuario.getCedula()));
        // lstParam.add(new Parametro(2, objUsuario.getClave()));
        ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParam);
        while (rs.next()) {
            correcto = rs.getBoolean(0);
        }
        return correcto;
    }

    public static Boolean comprobacion(CUsuario objUsuario) throws Exception {
        Boolean correcto = false;
        try {
            String sql = "SELECT * FROM sisplan.fn_logincorrecto_tusuario(?) ";
            ArrayList<Parametro> lstParam = new ArrayList<>();
            lstParam.add(new Parametro(1, objUsuario.getCedula()));
            // lstParam.add(new Parametro(2, objUsuario.getClave()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParam);
            while (rs.next()) {
                correcto = rs.getBoolean(0);
            }

        } catch (Exception e) {
            throw e;
        }
        return correcto;
    }

    public static boolean insertarDatosUsuario(CUsuario objUsuario) throws Exception {
        boolean respuesta = false;
        String escuelaCodigo = "";
        try {
            //sacar codigo del rol

            ArrayList<Parametro> lstParamDatosUsuario = new ArrayList<>();
            // Periodo periodo=getPeriodoActual();
            ArrayOfRolCarrera carrera = autenticarUsuarioCarrera(objUsuario.getCedula(), objUsuario.getClave());
            List<RolCarrera> rol = carrera.getRolCarrera();
            for (RolCarrera rol1 : rol) {
                escuelaCodigo = rol1.getCodigoCarrera();
            }
            Persona datos = getDatosUsuarioCarrera(escuelaCodigo, objUsuario.getCedula());

            String sql = " SELECT sisplan.fn_insert_datosUsuario(?,?,?,?,?)";

            // lstParamDatosUsuario.add(new Parametro(1, objUsuario.getCodigoint()));
            lstParamDatosUsuario.add(new Parametro(1, datos.getCedula()));
            lstParamDatosUsuario.add(new Parametro(2, datos.getNombres()));
            lstParamDatosUsuario.add(new Parametro(3, datos.getApellidos()));
            lstParamDatosUsuario.add(new Parametro(4, datos.getEmail()));
            lstParamDatosUsuario.add(new Parametro(5, objUsuario.getRol()));

            // lstParamDatosUsuario.add(new Parametro(6, objUsuario.getTelefono()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUsuario);
            while (rs.next()) {
                if (rs.getBoolean(0)) {
                    respuesta = true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }

    //el insertar rol y materias
    public static boolean insertarRoles(CUsuario objUsuario) throws Exception {
        boolean respuesta = false;
        String rolCodigo = "";
        try {

            ArrayList<Parametro> lstParamDatosUsuario = new ArrayList<>();
            // Periodo periodo=getPeriodoActual();
            ArrayOfRolCarrera carrera = autenticarUsuarioCarrera(objUsuario.getCedula(), objUsuario.getClave());
            List<RolCarrera> rol = carrera.getRolCarrera();
            for (RolCarrera rol1 : rol) {
                objUsuario.setRol(rol1.getNombreRol());
            }
            //cambiar
            /*String sql = " SELECT sisplan.fn_insert_rolusuario(?,?)";//funcion nuevainsertar Roles 
             lstParamDatosUsuario.add(new Parametro(1, objUsuario.getRol()));
             lstParamDatosUsuario.add(new Parametro(2, objUsuario.getRol()));

             ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUsuario);
             while (rs.next()) {
             if (rs.getBoolean(0)) {
             respuesta = true;
             }
             }*/
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }

    //aqui poner el servicio 
    public static Boolean loginCorrecto(CUsuario objUsuario) throws Exception {
        Boolean correcto = false;
        try {
            String sql = "SELECT * FROM softlab.fn_logincorrecto_tusuario(?,?) ";
            ArrayList<Parametro> lstParam = new ArrayList<>();
            lstParam.add(new Parametro(1, objUsuario.getCorreo()));
            lstParam.add(new Parametro(2, objUsuario.getClave()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParam);
            while (rs.next()) {
                correcto = rs.getBoolean(0);
            }

        } catch (Exception e) {
            throw e;

        }
        return correcto;
    }

    public static CUsuario datosUsuario(CUsuario objUsuario) throws Exception {

        try {//mostrar datos de la base de datos
//modificado 
            String sql = "SELECT * FROM sisplan.fn_select_user_tusuario(?) ";
            ArrayList<Parametro> lstParam = new ArrayList<>();
            lstParam.add(new Parametro(1, objUsuario.getCedula()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParam);
            while (rs.next()) {
                objUsuario.setCodigoint(rs.getInt(0));
                objUsuario.setCedula(rs.getString(1));
                objUsuario.setNombres(rs.getString(2));
                objUsuario.setApellidos(rs.getString(3));
                objUsuario.setCorreo(rs.getString(4));
                objUsuario.setRol(rs.getString(5));
                //objUsuario.setTelefono(rs.getString(5));

            }

        } catch (Exception e) {
            throw e;
        }
        return objUsuario;
    }
//guardar materias

    public static CUsuario guardarMaterias(CUsuario objUsuario) throws Exception {
        try {//mostrar datos de la base de datos
//modificado 
            boolean respuesta = false;
            

            ArrayOfRolCarrera carrera = getRolUsuarioCarrera(objUsuario.getCedula());
            List<RolCarrera> rol = carrera.getRolCarrera();

            Periodo p = getPeriodoActual();
            
            for (RolCarrera rol1 : rol) {

                ArrayOfMateria mat = getMateriasDocente(rol1.getCodigoCarrera(), objUsuario.getCedula(), p.getCodigo());
                List<Materia> mat1 = mat.getMateria();
                for (Materia mat2 : mat1) {
                    String sql = " SELECT * FROM sisplan.fn_select_materias(?,?)";//funcion nuevainsertar Roles 
                    ArrayList<Parametro> lstParamDatosUsuario = new ArrayList<>();
                    lstParamDatosUsuario.add(new Parametro(1, mat2.getCodigo()));
                    lstParamDatosUsuario.add(new Parametro(2, mat2.getNombre()));

                    ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUsuario);
                    while (rs.next()) {
                        CMateria objMat = new CMateria();
                        objMat.setIdcarrera(rs.getInt(0));
                        objMat.setCodigomateria(rs.getString(1));
                        objMat.setNombremateria(rs.getString(2));

                        String sql1 = "SELECT sisplan.fn_insert_UsuarioMateria(?,?)";//insert escuela
                        ArrayList<Parametro> lstParamEsc = new ArrayList<>();
                        lstParamEsc.add(new Parametro(1, objUsuario.getCodigoint()));
                        lstParamEsc.add(new Parametro(2, objMat.getIdcarrera()));

                        ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1, lstParamEsc);
                        while (rs1.next()) {
                            if (rs1.getBoolean(0)) {
                                respuesta = true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return objUsuario;
    }
//guardar materias estudiante
    
    public static CUsuario guardarMateriasEstudiante(CUsuario objUsuario) throws Exception {
        try {//mostrar datos de la base de datos
//modificado 
            boolean respuesta = false;
            

            ArrayOfRolCarrera carrera = getRolUsuarioCarrera(objUsuario.getCedula());
            List<RolCarrera> rol = carrera.getRolCarrera();

            Periodo p = getPeriodoActual();
            
            for (RolCarrera rol1 : rol) {

                ArrayOfMateria mat = getMateriasEstudiante(rol1.getCodigoCarrera(), objUsuario.getCedula(), p.getCodigo());
                List<Materia> mat1 = mat.getMateria();
                for (Materia mat2 : mat1) {
                    String sql = " SELECT * FROM sisplan.fn_select_materias(?,?)";//funcion nuevainsertar Roles 
                    ArrayList<Parametro> lstParamDatosUsuario = new ArrayList<>();
                    lstParamDatosUsuario.add(new Parametro(1, mat2.getCodigo()));
                    lstParamDatosUsuario.add(new Parametro(2, mat2.getNombre()));

                    ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUsuario);
                    while (rs.next()) {
                        CMateria objMat = new CMateria();
                        objMat.setIdcarrera(rs.getInt(0));
                        objMat.setCodigomateria(rs.getString(1));
                        objMat.setNombremateria(rs.getString(2));

                        String sql1 = "SELECT sisplan.fn_insert_UsuarioMateria(?,?)";//insert escuela
                        ArrayList<Parametro> lstParamEsc = new ArrayList<>();
                        lstParamEsc.add(new Parametro(1, objUsuario.getCodigoint()));
                        lstParamEsc.add(new Parametro(2, objMat.getIdcarrera()));

                        ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1, lstParamEsc);
                        while (rs1.next()) {
                            if (rs1.getBoolean(0)) {
                                respuesta = true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return objUsuario;
    }

    
    private static ArrayOfRolCarrera autenticarUsuarioCarrera(java.lang.String login, java.lang.String password) {
        WSSeguridad.Seguridad service = new WSSeguridad.Seguridad();
        WSSeguridad.SeguridadSoap port = service.getSeguridadSoap();
        return port.autenticarUsuarioCarrera(login, password);
    }

    private static Persona getDatosUsuarioCarrera(java.lang.String codCarrera, java.lang.String cedula) {
        WSCarrera.InfoCarrera service = new WSCarrera.InfoCarrera();
        WSCarrera.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getDatosUsuarioCarrera(codCarrera, cedula);
    }

    private static Periodo getPeriodoActual() {
        WSGeneral.InfoGeneral service = new WSGeneral.InfoGeneral();
        WSGeneral.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getPeriodoActual();
    }

    private static ArrayOfMateria getMateriasDocente(java.lang.String codCarrera, java.lang.String cedula, java.lang.String codPeriodo) {
        WSCarrera.InfoCarrera service = new WSCarrera.InfoCarrera();
        WSCarrera.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMateriasDocente(codCarrera, cedula, codPeriodo);
    }

    private static ArrayOfRolCarrera getRolUsuarioCarrera(java.lang.String login) {
        WSSeguridad.Seguridad service = new WSSeguridad.Seguridad();
        WSSeguridad.SeguridadSoap port = service.getSeguridadSoap();
        return port.getRolUsuarioCarrera(login);
    }

    private static ArrayOfMateria getMateriasEstudiante(java.lang.String codCarrera, java.lang.String cedula, java.lang.String codPeriodo) {
        WSCarrera.InfoCarrera service = new WSCarrera.InfoCarrera();
        WSCarrera.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMateriasEstudiante(codCarrera, cedula, codPeriodo);
    }

}
