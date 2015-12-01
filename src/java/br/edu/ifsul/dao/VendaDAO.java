/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Venda;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Devel
 */
@Stateful
public class VendaDAO<T> extends GenericDAO<Venda> implements Serializable {
    
    public VendaDAO(){
        super();
        //definir a classe persistente
        super.setPersistentClass(Venda.class);
        //definir a lista de ordenações
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("valor", "Valor", "="));
        //definir a ordem atual
        super.setCurrentOrder(super.getListOrder().get(1));
        //inicializar o filtro
        super.setFilter("");
        //inicializar o conversor
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));

    }
    
    @Override
    public Venda getObjectById(Integer id) throws Exception{
        Venda obj =  super.getEm().find(Venda.class, id);
        obj.getVeiculos().size();
        return obj;
    }
}
