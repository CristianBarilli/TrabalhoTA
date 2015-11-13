/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Montadora;
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
public class MontadoraDAO implements Serializable{
    
    @PersistenceContext(unitName = "TA-2015-Trabalho-E2PU")
    private EntityManager em;
    private List<Montadora> listarTodos;

    public MontadoraDAO() {
    }
    
    public void persist(Montadora objeto) throws Exception{
        em.persist(objeto);
    }
    
    public void merge(Montadora objeto) throws Exception{
        em.merge(objeto);
    }
    
    public void remove(Montadora objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Montadora getObjectById(Integer id) throws Exception{
        return em.find(Montadora.class, id);
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Montadora> getListarTodos() {
        return em.createQuery("from Montadora order by nome").getResultList();
    }

    public void setListarTodos(List<Montadora> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
    
    
    
}
