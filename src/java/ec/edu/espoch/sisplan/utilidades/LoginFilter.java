/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.utilidades;

import ec.edu.espoch.sisplan.controladores.LoginBean;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ©foqc
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"*.xhtml"})
public class LoginFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public LoginFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginFilter:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginFilter:DoAfterProcessing");
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // Obtengo el bean que representa el usuario desde el scope sesión
        LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");
        boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + "/faces" + ResourceHandler.RESOURCE_IDENTIFIER);
        //Proceso la URL que está requiriendo el cliente
        String urlStr = req.getRequestURL().toString().toLowerCase();
        boolean noProteger = noProteger(urlStr);
        //Si no requiere protección(true) continúo normalmente.
        if (noProteger) {
            chain.doFilter(request, response);
        } else {
            //El usuario no está logueado
            if ((loginBean == null || !loginBean.estaLogeado())) {
                res.sendRedirect(req.getContextPath() + "/index.xhtml");
            } else {
                //El recurso requiere protección, pero el usuario ya está logueado.                
                if (tienePermiso(urlStr, loginBean)) {
                    chain.doFilter(request, response);
                } else {
                    try {
                        redireccionarApaginas(urlStr, loginBean, res, req);
                    } catch (Exception ex) {
                        Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    private String roles(LoginBean loginBean) {//modificar aqui 
        String rol = loginBean.getObjUsuario().getRol();//getObjUsuario().getObjRol().getCodigo();///
        switch (rol) {
            case "ADM":
                return "ADM";

            case "DOC":
                return "DOC";//Docente
            case "EST":
                return "EST";//ESTUDIANTE

            default:
                return "Ninguno";
        }
    }

    private Boolean tienePermiso(String urlStr, LoginBean loginBean) {
        Boolean respuesta = false;
        try {
            String rol = roles(loginBean);
            if (rol.equals("Ninguno")) {
                rol = loginBean.getObjUsuario().getObjRol().getNombre();
            }
            //si esta logeado, el usuario quiere acceder a sus paginas permitidas?
            if ((urlStr.contains("_adminbod") && rol.equals("ADM"))
                    || (urlStr.contains("_docente") && rol.equals("DOC")) || (urlStr.contains("_estudiante") && rol.equals("EST")) || (urlStr.contains("invitado") && rol.equals("Invitado"))) {
                System.out.println("permission to: " + rol);
                respuesta = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }

    private void redireccionarApaginas(String urlStr, LoginBean loginBean, HttpServletResponse res, HttpServletRequest req) throws Exception {

        try {
            String rol = roles(loginBean);
            if (rol.equals("Ninguno")) {
                rol = loginBean.getObjUsuario().getObjRol().getNombre();
            }
            if (rol.equals("ADM") && (urlStr.contains("_estudiante") || urlStr.contains("_docente"))) {
                System.out.println("redirect to: " + rol);
                res.sendRedirect(req.getContextPath() + "/_adminBod/indexBod.xhtml");
            } else {

                if (rol.equals("DOC") && (urlStr.contains("_adminbod")  || urlStr.contains("_estudiante") )) {
                    System.out.println("redirect to: " + rol);
                    res.sendRedirect(req.getContextPath() + "/_docente/indexDocente.xhtml");
                } else {
                    if (rol.equals("EST") && (urlStr.contains("_adminbod")  || urlStr.contains("_docente") )) {
                        System.out.println("redirect to: " + rol);
                        res.sendRedirect(req.getContextPath() + "/_estudiante/indexEstudiante.xhtml");
                    }

                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean noProteger(String urlStr) {

        /*
         * Este es un buen lugar para colocar y programar todos los patrones que
         * creamos convenientes para determinar cuales de los recursos no
         * requieren protección. Sin duda que habría que crear un mecanismo tal
         * que se obtengan de un archivo de configuración o algo que no requiera
         * compilación.
         */
        //URL's permitidas sin sesion
        Boolean protegido = false;
        String[] paginas = {"login.xhtml", "index.xhtml", "activate.xhtml", "registrar.xhtml", "javax.faces.resource"};

        for (String pagina : paginas) {
            if (urlStr.contains(pagina)) {
                protegido = true;
                break;
            }
        }

        return protegido;
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("LoginFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginFilter()");
        }
        StringBuilder sb = new StringBuilder("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
