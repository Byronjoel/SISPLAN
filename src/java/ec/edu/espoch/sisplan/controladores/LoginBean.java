package ec.edu.espoch.sisplan.controladores;

import WSCarrera.ArrayOfMateria;
import WSCarrera.InfoCarrera;
import WSCarrera.Materia;
import WSGeneral.Facultad;
import WSGeneral.InfoGeneral;
import WSGeneral.Periodo;
import WSSeguridad.ArrayOfRolCarrera;
import WSSeguridad.RolCarrera;
import WSSeguridad.Seguridad;
import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.accesodatos.Parametro;
import ec.edu.espoch.sisplan.clases.CClase;
import ec.edu.espoch.sisplan.clases.CHorario;
import ec.edu.espoch.sisplan.clases.CMateria;
import ec.edu.espoch.sisplan.clases.CProgreso;
import ec.edu.espoch.sisplan.clases.CRol;
import ec.edu.espoch.sisplan.clases.CUnidad;
import ec.edu.espoch.sisplan.clases.CUsuario;
import ec.edu.espoch.sisplan.modelos.MLogin;
import ec.edu.espoch.sisplan.modelos.MUsuario;
import ec.edu.espoch.sisplan.utilidades.Util;
import ec.edu.espoch.sisplan.utilidades.UtilString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/academico.espoch.edu.ec/OAS_Interop/Seguridad.asmx.wsdl")
    private Seguridad service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/academico.espoch.edu.ec/OAS_Interop/InfoGeneral.asmx.wsdl")
    private InfoGeneral service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/academico.espoch.edu.ec/OAS_Interop/InfoCarrera.asmx.wsdl")
    private InfoCarrera service;

    private static final long serialVersionUID = -2152389656664659476L;
    private CUsuario objUsuario;
    private boolean logeado = false;
    private List<SelectItem> selectOneItems;
    private List<SelectItem> selectOneItemsU;
    private List<SelectItem> selectOneItemsU1;
    private List<SelectItem> selectOneItemsH;
    private List<SelectItem> selectOneItemsP;
    private List<SelectItem> selectOneIteml;

    public boolean estaLogeado() {
        return logeado;
    }

    public LoginBean() {
        this.objUsuario = new CUsuario();
    }

    public CUsuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(CUsuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    @PostConstruct
    public void reinit() {
        CRol objTipo = new CRol();
        this.objUsuario.setObjRol(objTipo);
    }
///login el servicio 

    public List<SelectItem> getSelectItemsMenu() {
        try {
            //cargar el periodo actual
            //  Periodo p = getPeriodoActual();
            this.selectOneItems = new ArrayList<>();

            String sql = "SELECT *FROM sisplan.fn_select_matComboBox(?)";
            ArrayList<Parametro> lstParamDatosUsuario = new ArrayList<>();
            lstParamDatosUsuario.add(new Parametro(1, objUsuario.getCedula()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUsuario);
            while (rs.next()) {
                CMateria objMat = new CMateria();
                objMat.setIdcarrera(rs.getInt(0));
                objMat.setCodigomateria(rs.getString(1));
                objMat.setNombremateria(rs.getString(2));
//cambiar
                SelectItem selectItem2 = new SelectItem(objMat.getIdcarrera(), objMat.getNombremateria());
                //SelectItem selectItem2 = new SelectItem(objMat.getNombremateria()+ objMat.getIdcarrera());
                this.selectOneItems.add(selectItem2);

            }//cargar desde bd
//
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        return this.selectOneItems;
    }
//cargar comboBox unidad

    public List<SelectItem> getSelectItemsUnidad() {
        try {

            this.selectOneItemsU = new ArrayList<>();

            String sql1 = "SELECT *FROM sisplan.fn_select_unidad(?)";
            ArrayList<Parametro> lstParamDatosUnidad = new ArrayList<>();
            lstParamDatosUnidad.add(new Parametro(1, objUsuario.getCedula()));

            ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1, lstParamDatosUnidad);
            while (rs1.next()) {
                CUnidad objUni = new CUnidad();
                objUni.setUnidadcodigo(rs1.getInt(0));
                objUni.setUnidaddescripcion(rs1.getString(1));
                objUni.setMateriaid(rs1.getInt(2));

                SelectItem selectItem2 = new SelectItem(objUni.getUnidadcodigo(), objUni.getUnidaddescripcion());
                this.selectOneItemsU.add(selectItem2);

            }//cargar desde bd
//
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        return this.selectOneItemsU;
    }
//cargar comboBox unidad

    public List<SelectItem> getSelectOneItemsU1() {
        return selectOneItemsU1;
    }

    public void setSelectOneItemsU1(List<SelectItem> selectOneItemsU1) {
        this.selectOneItemsU1 = selectOneItemsU1;
    }

    public List<SelectItem> getSelectItemsUnidadByMateria(int idMateria) {
        try {

            this.selectOneItemsU1 = new ArrayList<>();

            String sql = "select * from sisplan.fn_select_tema_by_materia(?)";

            ArrayList<Parametro> lstParamDatosUnidad = new ArrayList<>();

            lstParamDatosUnidad.add(new Parametro(1, idMateria));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUnidad);
            while (rs.next()) {
                CClase reserva = new CClase();
                reserva.setIdTema(rs.getInt(0));
                reserva.setTematema(rs.getString(1));

                SelectItem selectItem2 = new SelectItem(reserva.getIdTema(), reserva.getTematema());
                this.selectOneItemsU1.add(selectItem2);

            }//cargar desde bd
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        return this.selectOneItemsU1;
    }

//comboBox horario
//comBox Peridoo
    //logeo
    public void login(ActionEvent actionEvent) {
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            FacesMessage msg = null;
            //comprobacion para el registro de los datos del docente
//logeo
            if (autenticarUsuarioCarrera(objUsuario.getCedula(), objUsuario.getClave()) != null) {
                if (MLogin.comprobacionRegistro(objUsuario) == false) {

                    MLogin.insertarRoles(objUsuario);//insertar rol 

                    if (MLogin.insertarDatosUsuario(objUsuario)) {
                        this.objUsuario = MLogin.datosUsuario(objUsuario);//saco datos del usuario
                        if (objUsuario.getRol().equals("DOC")) {
                            //insertar materias metodo 
                            MLogin.guardarMaterias(objUsuario);
                        } else {
                            MLogin.guardarMateriasEstudiante(objUsuario);
                        }

                        logeado = true;
                        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", UtilString.devolverCadena(objUsuario.getNombres(), objUsuario.getApellidos()));

                        Util.addSuccessMessage("Datos insertados!");
                    } else {
                        Util.addErrorMessage("Datos no insertados!");
                    }
                } else {
                    this.objUsuario = MLogin.datosUsuario(objUsuario);//saco datos del usuario
                    logeado = true;
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", UtilString.devolverCadena(objUsuario.getNombres(), objUsuario.getApellidos()));

                }

            } else {
                if (MLogin.datosUsuario(objUsuario) != null) {

                    this.objUsuario = MLogin.datosUsuario(objUsuario);//saco datos del usuario
                    logeado = true;
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", UtilString.devolverCadena(objUsuario.getNombres(), objUsuario.getApellidos()));

                } else {
                    logeado = false;
                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                            "Credenciales no válidas");

                }

            }

            FacesContext.getCurrentInstance()
                    .addMessage(null, msg);
            context.addCallbackParam(
                    "estaLogeado", logeado);
            if (logeado) {
                redireccionarPaginas(context);
            }
        } catch (Exception e) {

            Util.addErrorMessageAndDetail("Error ", e.getMessage());
        }
    }

    public void updateSession(CUsuario usuarioSession) {
        try {
            if (MUsuario.actualizarUsuario(usuarioSession)) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
                LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
                loginBean.setObjUsuario(MLogin.datosUsuario(objUsuario));
                Util.addSuccessMessageAndDetail("Aviso", "Sus datos se han actualizado correctamente.");
            } else {
                Util.addErrorMessageAndDetail("Error ", "Sus datos no se han actualizado, por favor inténtelo de nuevo.");
            }
        } catch (Exception e) {
            Util.addErrorMessageAndDetail("Error update session ", e.getMessage());
        }
    }

    private void redireccionarPaginas(RequestContext context) {
        switch (this.objUsuario.getRol()) { //(this.objUsuario.getCodigo2()) {
            case "ADM":
                context.addCallbackParam("view", "_adminBod/indexBod.xhtml");
                break;
            case "DOC"://Docente EST
                context.addCallbackParam("view", "_docente/indexDocente.xhtml");
                break;
            case "EST"://DOC
                context.addCallbackParam("view", "_estudiante/indexEstudiante.xhtml");
                break;

            default:
                context.addCallbackParam("view", "index.xhtml");
        }
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        logeado = false;
    }

    private ArrayOfMateria getMateriasDocente(java.lang.String codCarrera, java.lang.String cedula, java.lang.String codPeriodo) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        WSCarrera.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMateriasDocente(codCarrera, cedula, codPeriodo);
    }

    private ArrayOfMateria getMateriasEstudiante(java.lang.String codCarrera, java.lang.String cedula, java.lang.String codPeriodo) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        WSCarrera.InfoCarreraSoap port = service.getInfoCarreraSoap();
        return port.getMateriasEstudiante(codCarrera, cedula, codPeriodo);
    }

    private Periodo getPeriodoActual() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        WSGeneral.InfoGeneralSoap port = service_1.getInfoGeneralSoap();
        return port.getPeriodoActual();
    }

    private ArrayOfRolCarrera autenticarUsuarioCarrera(java.lang.String login, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        WSSeguridad.SeguridadSoap port = service_2.getSeguridadSoap();
        return port.autenticarUsuarioCarrera(login, password);
    }
}
