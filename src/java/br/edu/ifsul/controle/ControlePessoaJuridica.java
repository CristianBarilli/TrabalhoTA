/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaJuridicaDAO;
import br.edu.ifsul.modelo.PessoaJuridica;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Devel
 */
@ManagedBean(name = "controlePessoaJuridica")
@SessionScoped
public class ControlePessoaJuridica implements Serializable{
    
    @EJB
    private PessoaJuridicaDAO dao;
    private PessoaJuridica objeto;

    public ControlePessoaJuridica() {
    }

    public String listar(){
        return "/privado/pessoajuridica/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new PessoaJuridica();
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
    
    public PessoaJuridicaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaJuridicaDAO dao) {
        this.dao = dao;
    }

    public PessoaJuridica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaJuridica objeto) {
        this.objeto = objeto;
    }
    
}