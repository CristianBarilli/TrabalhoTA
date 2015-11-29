/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaJuridica;
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
public class PessoaJuridicaDAO implements Serializable{
    
    @PersistenceContext(unitName = "TA-2015-Trabalho-E2PU")
    private EntityManager em;
    private List<PessoaJuridica> listarTodos;

    public PessoaJuridicaDAO() {
    }
    
    public void persist(PessoaJuridica objeto) throws Exception{
        em.persist(objeto);
    }
    
    public void merge(PessoaJuridica objeto) throws Exception{
        em.merge(objeto);
    }
    
    public void remove(PessoaJuridica objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public PessoaJuridica getObjectById(Integer id) throws Exception{
        return em.find(PessoaJuridica.class, id);
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<PessoaJuridica> getListarTodos() {
        return em.createQuery("from PessoaJuridica order by razao_social").getResultList();
    }

    public void setListarTodos(List<PessoaJuridica> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
    
    
    
}
