/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.MeioPagamento;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Devel
 */
@Stateful
public class MeioPagamentoDAO<T> extends GenericDAO<MeioPagamento> implements Serializable {
    
    public MeioPagamentoDAO(){
        super();
        //definir a classe persistente
        super.setPersistentClass(MeioPagamento.class);
        //definir a lista de ordenações
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("descricao", "Descrição", "like"));
        //definir a ordem atual
        super.setCurrentOrder(super.getListOrder().get(1));
        //inicializar o filtro
        super.setFilter("");
        //inicializar o conversor
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
        
    }
}
