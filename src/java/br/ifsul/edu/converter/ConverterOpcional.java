/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.converter;

import br.edu.ifsul.modelo.Opcional;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Devel
 */
@FacesConverter(value = "converterOpcional")
public class ConverterOpcional implements Converter{
    
    @PersistenceContext(unitName = "TA-2015-Trabalho-E2PU")
    private EntityManager em;

    //converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null){
            return null;
        }
        return em.find(Opcional.class, Integer.parseInt(string));
    }
    //converte do objeto para tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Opcional obj = (Opcional) o;
        return obj.getId().toString();
    }
}