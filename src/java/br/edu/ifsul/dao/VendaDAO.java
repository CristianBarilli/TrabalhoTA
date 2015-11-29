/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Venda;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Devel
 */
@Stateless
public class VendaDAO implements Serializable{
    
    @PersistenceContext(unitName = "TA-2015-Trabalho-E2PU")
    private EntityManager em;
    private List<Venda> listarTodos;

    public VendaDAO() {
    }
    
    public void persist(Venda objeto) throws Exception{
        em.persist(objeto);
    }
    
    public void merge(Venda objeto) throws Exception{
        em.merge(objeto);
    }
    
    public void remove(Venda objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Venda getObjectById(Integer id) throws Exception{
        Venda obj = em.find(Venda.class, id);
        obj.getVeiculos().size();
        return obj;
        
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Venda> getListarTodos() {
        return em.createQuery("from Venda order by id").getResultList();
    }

    public void setListarTodos(List<Venda> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
    
    
    
}
