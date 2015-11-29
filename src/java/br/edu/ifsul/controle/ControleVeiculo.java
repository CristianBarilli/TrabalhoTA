/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MontadoraDAO;
import br.edu.ifsul.dao.OpcionalDAO;
import br.edu.ifsul.dao.TipoDAO;
import br.edu.ifsul.dao.VeiculoDAO;
import br.edu.ifsul.modelo.Opcional;
import br.edu.ifsul.modelo.Veiculo;
import br.edu.ifsul.util.Util;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Devel
 */
@ManagedBean(name = "controleVeiculo")
@SessionScoped
public class ControleVeiculo implements Serializable{
    
    @EJB
    private VeiculoDAO dao;
    private Veiculo objeto;
    private Opcional opcional;
    @EJB
    private MontadoraDAO daoMontadora;
    @EJB
    private TipoDAO daoTipo;
    @EJB
    private OpcionalDAO daoOpcional;

    public ControleVeiculo() {
    }

    public String listar(){
        return "/privado/veiculo/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Veiculo();
        objeto.setVendido(false);
        return "formulario";
    }
    
    public String salvar(){
        try{
            if(objeto.getId() == null){
                System.out.println("lista"+ objeto.getOpcionais().toString());
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
    
        public void adicionarOpcional(){
        if(!objeto.getOpcionais().contains(opcional)){
            objeto.getOpcionais().add(opcional);
            Util.mensagemInformacao("Operação realizada com sucesso");
        }else{
            Util.mensagemInformacao("O opcional já está contido na lista de desejos");
        }
    }
    
    public void removerOpcional(int index){
        objeto.getOpcionais().remove(index);
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public VeiculoDAO getDao() {
        return dao;
    }

    public void setDao(VeiculoDAO dao) {
        this.dao = dao;
    }

    public Veiculo getObjeto() {
        return objeto;
    }

    public void setObjeto(Veiculo objeto) {
        this.objeto = objeto;
    }

    public MontadoraDAO getDaoMontadora() {
        return daoMontadora;
    }

    public void setDaoMontadora(MontadoraDAO daoMontadora) {
        this.daoMontadora = daoMontadora;
    }

    public TipoDAO getDaoTipo() {
        return daoTipo;
    }

    public void setDaoTipo(TipoDAO daoTipo) {
        this.daoTipo = daoTipo;
    }

    public OpcionalDAO getDaoOpcional() {
        return daoOpcional;
    }

    public void setDaoOpcional(OpcionalDAO daoOpcional) {
        this.daoOpcional = daoOpcional;
    }

    public Opcional getOpcional() {
        return opcional;
    }

    public void setOpcional(Opcional opcional) {
        this.opcional = opcional;
    }
    
}