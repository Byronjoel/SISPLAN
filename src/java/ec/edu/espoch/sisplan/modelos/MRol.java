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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class MRol {

    public static boolean insertarRol(CRol tipoUsuario) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamRol = new ArrayList<>();
            String sql = "SELECT softlab.fn_insert_trol(?,?)";
            lstParamRol.add(new Parametro(1, tipoUsuario.getNombre()));
            if (tipoUsuario.getDescripcion().isEmpty()) {
                tipoUsuario.setDescripcion("ninguno");
            }
            lstParamRol.add(new Parametro(2, tipoUsuario.getDescripcion()));
            
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamRol);
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
    
    public static List<CRol> cargarRoles() throws Exception {
        List<CRol> lstRols = new ArrayList<>();
        try {
            String sql = "select * from softlab.fn_select_trol()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                CRol tipoUsuario = new CRol();
                tipoUsuario.setCodigo(rs.getInt(0));
                tipoUsuario.setNombre(rs.getString(1));
                tipoUsuario.setDescripcion(rs.getString(2));
                
                lstRols.add(tipoUsuario);
            }
            rs = null;
        } catch (Exception e) {
            lstRols.clear();
            throw e;
        }
        return lstRols;
    }
    
    public static CRol cargarRolPorCodigo(int codigo) throws Exception {
        CRol objRol = new CRol();
        try {
            ArrayList<Parametro> lstParamRol = new ArrayList<>();
            String sql = "select * from softlab.fn_selectxcodigo_trol(?)";
            lstParamRol.add(new Parametro(1, codigo));
            
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamRol);
            while (rs.next()) {
                objRol.setCodigo(rs.getInt(0));
                objRol.setNombre(rs.getString(1));
                objRol.setDescripcion(rs.getString(2));
            }
            rs = null;
        } catch (Exception e) {
            throw e;
        }
        return objRol;
    }
    
    public static boolean actualizarRol(CRol tipoUsuario) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamRol = new ArrayList<>();
            String sql = "SELECT softlab.fn_update_trol(?,?,?)";
            lstParamRol.add(new Parametro(1, tipoUsuario.getCodigo()));
            lstParamRol.add(new Parametro(2, tipoUsuario.getNombre()));
            if (tipoUsuario.getDescripcion().isEmpty()) {
                tipoUsuario.setDescripcion("ninguno");
            }
            lstParamRol.add(new Parametro(3, tipoUsuario.getDescripcion()));
            
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamRol);
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
    
    public static boolean eliminarRol(int codigoRol) throws Exception {
        boolean respuesta = false;
        try {
            String sql = "select softlab.fn_delete_trol(?)";
            ArrayList<Parametro> lstParam = new ArrayList<>();
            lstParam.add(new Parametro(1, codigoRol));
            
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
