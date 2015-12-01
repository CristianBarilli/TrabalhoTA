/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Veiculo;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Devel
 */
@Stateful
public class VeiculoDAO<T> extends GenericDAO<Veiculo> implements Serializable {
    
    public VeiculoDAO(){
        super();
        //definir a classe persistente
        super.setPersistentClass(Veiculo.class);
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
    public Veiculo getObjectById(Integer id) throws Exception{
        Veiculo obj =  super.getEm().find(Veiculo.class, id);
        obj.getOpcionais().size();
        return obj;
    }
}
