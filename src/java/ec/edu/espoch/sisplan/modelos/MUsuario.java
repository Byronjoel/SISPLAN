/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.modelos;

import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.accesodatos.Parametro;
import ec.edu.espoch.sisplan.clases.CRol;
import ec.edu.espoch.sisplan.clases.CUsuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class MUsuario {

    public static boolean insertarUsuario(CUsuario usuario) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamUsusario = new ArrayList<>();
            java.sql.Date sqlDate = new java.sql.Date(usuario.getFechaNacimiento().getTime());
            java.sql.Timestamp sqlDate1 = new java.sql.Timestamp(usuario.fechaRegistro().getTime());
            String sql = "SELECT softlab.fn_insert_tusuario(?,?,?,?,?,?,?,?,?,?)";
            lstParamUsusario.add(new Parametro(1, usuario.getCedula()));
            lstParamUsusario.add(new Parametro(2, usuario.getNombres()));
            lstParamUsusario.add(new Parametro(3, usuario.getApellidos()));
            lstParamUsusario.add(new Parametro(4, usuario.getCorreo()));
            lstParamUsusario.add(new Parametro(5, sqlDate));
            lstParamUsusario.add(new Parametro(6, sqlDate1));
            lstParamUsusario.add(new Parametro(7, usuario.getSexo()));
            lstParamUsusario.add(new Parametro(8, usuario.getTelefono()));
            lstParamUsusario.add(new Parametro(9, usuario.getClave()));
            lstParamUsusario.add(new Parametro(10, usuario.getObjRol().getCodigo()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamUsusario);
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

    public static boolean registrarUsuario(CUsuario usuario, String token) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamUsusario = new ArrayList<>();
            java.sql.Date sqlDate = new java.sql.Date(usuario.getFechaNacimiento().getTime());
            java.sql.Timestamp sqlDate1 = new java.sql.Timestamp(usuario.fechaRegistro().getTime());
            String sql = "SELECT softlab.fn_register_tusuario(?,?,?,?,?,?,?,?,?,?,?)";
            lstParamUsusario.add(new Parametro(1, usuario.getCedula()));
            lstParamUsusario.add(new Parametro(2, usuario.getNombres()));
            lstParamUsusario.add(new Parametro(3, usuario.getApellidos()));
            lstParamUsusario.add(new Parametro(4, usuario.getCorreo()));
            lstParamUsusario.add(new Parametro(5, sqlDate));
            lstParamUsusario.add(new Parametro(6, sqlDate1));
            lstParamUsusario.add(new Parametro(7, usuario.getSexo()));
            lstParamUsusario.add(new Parametro(8, usuario.getTelefono()));
            lstParamUsusario.add(new Parametro(9, usuario.getClave()));
            lstParamUsusario.add(new Parametro(10, usuario.getObjRol().getCodigo()));
            lstParamUsusario.add(new Parametro(11, token));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamUsusario);
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

    public static List<CUsuario> cargarUsuarios() throws Exception {
        List<CUsuario> lstUsuarios = new ArrayList<>();
        try {
            String sql = "select * from softlab.fn_select_tusuario()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                CUsuario usuario = new CUsuario();
                usuario.setCodigo(rs.getLong(0));
                usuario.setCedula(rs.getString(1));
                usuario.setNombres(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setCorreo(rs.getString(4));
                usuario.setFechaNacimiento(rs.getDate(5));
                usuario.setFechaRegistro(rs.getTimeStamp(6));
                usuario.setSexo(rs.getString(7));
                usuario.setTelefono(rs.getString(8));
                //usuario.setClave(rs.getString(9));
                CRol objRol = MRol.cargarRolPorCodigo(rs.getInt(10));
                usuario.setObjRol(objRol);

                lstUsuarios.add(usuario);
            }
            rs = null;
        } catch (Exception e) {
            lstUsuarios.clear();
            throw e;
        }
        return lstUsuarios;
    }

    /**
     * funcion que elimina cuenta de usuario si no se a activado dentro de las
     * 24horas
     *
     * @throws Exception
     */
    public static void eliminarCuenta() throws Exception {
        try {
            String sql = "SELECT softlab.fn_delete_account_token()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public static Boolean activarCuenta(String token) throws Exception {
        boolean respuesta = false;
        try {
            eliminarCuenta();//elimina cuentas sin activar dentros de las 24h.
            ArrayList<Parametro> lstParamUsusario = new ArrayList<>();
            String sql = "SELECT softlab.fn_account_activation(?)";
            lstParamUsusario.add(new Parametro(1, token));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamUsusario);
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

    public static boolean actualizarUsuario(CUsuario usuario) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamUsusario = new ArrayList<>();
            String sql = "SELECT softlab.fn_update_tusuario(?,?,?,?,?,?,?,?,?,?)";
            java.sql.Date sqlDate = new java.sql.Date(usuario.getFechaNacimiento().getTime());
            lstParamUsusario.add(new Parametro(1, usuario.getCodigo()));
            lstParamUsusario.add(new Parametro(2, usuario.getCedula()));
            lstParamUsusario.add(new Parametro(3, usuario.getNombres()));
            lstParamUsusario.add(new Parametro(4, usuario.getApellidos()));
            lstParamUsusario.add(new Parametro(5, usuario.getCorreo()));
            lstParamUsusario.add(new Parametro(6, sqlDate));
            lstParamUsusario.add(new Parametro(7, usuario.getSexo()));
            lstParamUsusario.add(new Parametro(8, usuario.getTelefono()));
            lstParamUsusario.add(new Parametro(9, usuario.getClave()));
            lstParamUsusario.add(new Parametro(10, usuario.getObjRol().getCodigo()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamUsusario);
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

    public static boolean eliminarUsuario(Long codigoPersona) throws Exception {
        boolean respuesta = false;
        try {
            String sql = "select softlab.fn_delete_tusuario(?)";
            ArrayList<Parametro> lstParam = new ArrayList<>();
            lstParam.add(new Parametro(1, codigoPersona));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParam);
            while (rs.next()) {
                respuesta = rs.getBoolean(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }
}
