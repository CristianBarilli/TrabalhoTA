/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaFisica;
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
public class PessoaFisicaDAO implements Serializable{
    
    @PersistenceContext(unitName = "TA-2015-Trabalho-E2PU")
    private EntityManager em;
    private List<PessoaFisica> listarTodos;

    public PessoaFisicaDAO() {
    }
    
    public void persist(PessoaFisica objeto) throws Exception{
        em.persist(objeto);
    }
    
    public void merge(PessoaFisica objeto) throws Exception{
        em.merge(objeto);
    }
    
    public void remove(PessoaFisica objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public PessoaFisica getObjectById(Integer id) throws Exception{
        return em.find(PessoaFisica.class, id);
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<PessoaFisica> getListarTodos() {
        return em.createQuery("from PessoaFisica order by nome").getResultList();
    }

    public void setListarTodos(List<PessoaFisica> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
    
    
    
}
