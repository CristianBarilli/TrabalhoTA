/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MeioPagamentoDAO;
import br.edu.ifsul.modelo.MeioPagamento;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Devel
 */
@ManagedBean(name = "controleMeioPagamento")
@SessionScoped
public class ControleMeioPagamento implements Serializable{
    
    @EJB
    private MeioPagamentoDAO<MeioPagamento> dao;
    private MeioPagamento objeto;

    public ControleMeioPagamento() {
    }

    public String listar(){
        return "/privado/meiopagamento/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new MeioPagamento();
        return "formulario";
    }
    
    public String salvar(){
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
            return "listar";
        } catch( Exception e){
            Util.mensagemInformacao("Erro ao persistir o objeto");
            return "formulario";
        }
    }
    
    public String cancelar(){
        objeto = null;
        return "listar";
    }
    
    public String editar(Integer id){
        try{
            objeto = dao.getObjectById(id);
            return "formulario";
        }catch(Exception e) {
            Util.mensagemInformacao("Erro ao recuperar o objeto: " + e.getMessage());
            return "listar";
    }
    }
    
    public void excluir(Integer id){
        try{
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao remover o objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public MeioPagamentoDAO getDao() {
        return dao;
    }

    public void setDao(MeioPagamentoDAO dao) {
        this.dao = dao;
    }

    public MeioPagamento getObjeto() {
        return objeto;
    }

    public void setObjeto(MeioPagamento objeto) {
        this.objeto = objeto;
    }
    
}