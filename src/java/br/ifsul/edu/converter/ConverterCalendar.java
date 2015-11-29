/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Devel
 */
@FacesConverter(value = "converterCalendar")
public class ConverterCalendar implements Converter, Serializable{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        //string para calendar
        Calendar calendar = new GregorianCalendar();
        try {
            Date date = sdf.parse(string);
            calendar.setTime(date);  
            return calendar;
        } catch (Exception e) {
            return null;
        }
        
    }
    //calendar para string
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) { 
        try{
        Calendar dataCalendar = (Calendar) o;
        String dataString = sdf.format(dataCalendar.getTime());
        return dataString;
        } catch (Exception e) {
            return null;
        }
         
    }
}
