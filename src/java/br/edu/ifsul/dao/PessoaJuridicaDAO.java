/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaJuridica;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Devel
 */
@Stateful
public class PessoaJuridicaDAO<T> extends GenericDAO<PessoaJuridica> implements Serializable {
    
    public PessoaJuridicaDAO(){
        super();
        //definir a classe persistente
        super.setPersistentClass(PessoaJuridica.class);
        //definir a lista de ordenações
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("razao_social", "Razão Social", "like"));
        //definir a ordem atual
        super.setCurrentOrder(super.getListOrder().get(1));
        //inicializar o filtro
        super.setFilter("");
        //inicializar o conversor
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
        
    }
}
