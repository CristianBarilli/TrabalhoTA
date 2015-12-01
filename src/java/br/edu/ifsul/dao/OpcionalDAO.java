/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Opcional;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Devel
 */
@Stateful
public class OpcionalDAO<T> extends GenericDAO<Opcional> implements Serializable {
    
    public OpcionalDAO(){
        super();
        //definir a classe persistente
        super.setPersistentClass(Opcional.class);
        //definir a lista de ordenações
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        //definir a ordem atual
        super.setCurrentOrder(super.getListOrder().get(1));
        //inicializar o filtro
        super.setFilter("");
        //inicializar o conversor
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));

    }
    @Override
    public Opcional getObjectById(Integer id) throws Exception{
        Opcional obj =  super.getEm().find(Opcional.class, id);
        obj.getVeiculos().size();
        return obj;
    }
}
