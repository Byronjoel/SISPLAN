/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.controladores;

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import ec.edu.espoch.sisplan.cipher.CAes;

import ec.edu.espoch.sisplan.modelos.MUsuario;
import ec.edu.espoch.sisplan.utilidades.Util;
import ec.edu.espoch.sisplan.utilidades.UtilString;
import ec.edu.espoch.sisplan.utilidades.utilDates;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class RegisterBean {

    @Resource(name = "mail/myMailSession")
    private Session mailSession;

    /**
     * Creates a new instance of EmailBean
     */
    public RegisterBean() {

    }

    public Date fechaActual() {
        return new Date();
    }

}
