/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Veiculo;
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
public class VeiculoDAO implements Serializable{
    
    @PersistenceContext(unitName = "TA-2015-Trabalho-E2PU")
    private EntityManager em;
    private List<Veiculo> listarTodos;

    public VeiculoDAO() {
    }
    
    public void persist(Veiculo objeto) throws Exception{
        em.persist(objeto);
    }
    
    public void merge(Veiculo objeto) throws Exception{
        em.merge(objeto);
    }
    
    public void remove(Veiculo objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Veiculo getObjectById(Integer id) throws Exception{
        Veiculo obj = em.find(Veiculo.class, id);
        obj.getOpcionais().size();
        return obj;
        
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Veiculo> getListarTodos() {
        return em.createQuery("from Veiculo order by nome").getResultList();
    }

    public void setListarTodos(List<Veiculo> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
    
    
    
}
