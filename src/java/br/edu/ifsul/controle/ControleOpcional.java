/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.OpcionalDAO;
import br.edu.ifsul.dao.VeiculoDAO;
import br.edu.ifsul.modelo.Opcional;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Devel
 */
@ManagedBean(name = "controleOpcional")
@SessionScoped
public class ControleOpcional implements Serializable{
    
    @EJB
    private OpcionalDAO<Opcional> dao;
    private Opcional objeto;
    @EJB
    private VeiculoDAO daoVeiculo;
    public ControleOpcional() {
    }

    public String listar(){
        return "/privado/opcional/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Opcional();
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
    
    public OpcionalDAO getDao() {
        return dao;
    }

    public void setDao(OpcionalDAO dao) {
        this.dao = dao;
    }

    public Opcional getObjeto() {
        return objeto;
    }

    public void setObjeto(Opcional objeto) {
        this.objeto = objeto;
    }

    public VeiculoDAO getDaoVeiculo() {
        return daoVeiculo;
    }

    public void setDaoVeiculo(VeiculoDAO daoVeiculo) {
        this.daoVeiculo = daoVeiculo;
    }
    
}