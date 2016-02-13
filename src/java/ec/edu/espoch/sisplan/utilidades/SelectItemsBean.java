/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.utilidades;


import ec.edu.espoch.sisplan.modelos.MRol;
import ec.edu.espoch.sisplan.clases.CRol;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;



@ManagedBean
@RequestScoped
public class SelectItemsBean {

    private List<SelectItem> selectOneItems;
    private List<SelectItem> selectOneItemp;
    private List<SelectItem> selectOneItemc;
    private List<SelectItem> selectOneIteml;

}
