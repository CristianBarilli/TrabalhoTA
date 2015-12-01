/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MeioPagamentoDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.PessoaJuridicaDAO;
import br.edu.ifsul.dao.VeiculoDAO;
import br.edu.ifsul.dao.VendaDAO;
import br.edu.ifsul.modelo.MeioPagamento;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.PessoaJuridica;
import br.edu.ifsul.modelo.Veiculo;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Devel
 */
@ManagedBean(name = "controleVenda")
@SessionScoped
public class ControleVenda implements Serializable{
    
    @EJB
    private VendaDAO<Venda> dao;
    private Venda objeto;
    @EJB
    private VeiculoDAO<Veiculo> daoVeiculo;
    @EJB
    private MeioPagamentoDAO<MeioPagamento> daoMeioPagamento;
    @EJB
    private PessoaFisicaDAO<PessoaFisica> daoPessoaFisica;
    @EJB
    private PessoaJuridicaDAO<PessoaJuridica> daoPessoaJuridica;
    private Veiculo veiculo;

    public ControleVenda() {
    }

    public String listar(){
        return "/privado/venda/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Venda();
        objeto.setValor(0.00);
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
            objeto = (Venda) dao.getObjectById(id);
            return "formulario";
        }catch(Exception e) {
            Util.mensagemInformacao("Erro ao recuperar o objeto: " + e.getMessage());
            return "listar";
        }
    }
    
    public void excluir(Integer id){
        try{
            objeto = (Venda) dao.getObjectById(id);
            for (Veiculo v : objeto.getVeiculos()) {
                v.setVendido(false);
                daoVeiculo.merge(v);
            }
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao remover o objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void adicionarVeiculo() throws Exception{
        if(!objeto.getVeiculos().contains(veiculo)){
           if(!veiculo.getVendido()){
                objeto.getVeiculos().add(veiculo);
                veiculo.setVendido(true);
                Double total = 0.0;
                for (Veiculo v : objeto.getVeiculos()) {
                    total +=v.getPreco();
                }
                objeto.setValor(total);
                daoVeiculo.merge(veiculo);
                Util.mensagemInformacao("Operação realizada com sucesso");
           }else{
               Util.mensagemInformacao("Este carro já possui uma venda associada");
           }
        }else{
            Util.mensagemInformacao("O veículo já está contido na lista de desejos");
        }
    }
    
    public void removerVeiculo(int index) throws Exception{
        veiculo = (Veiculo) daoVeiculo.getObjectById(objeto.getVeiculos().get(index).getId());
        veiculo.setVendido(false);
        daoVeiculo.merge(veiculo);
        Double valor = veiculo.getPreco();
        Double total = objeto.getValor();
        total -= valor;
        objeto.setValor(total);
        objeto.getVeiculos().remove(index);
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public void compradorPF(PessoaFisica obj){
        Pessoa p = (Pessoa) obj;
        objeto.setComprador(p);
    }
    
    public void compradorPJ(PessoaJuridica obj){
        Pessoa p = (Pessoa) obj;
        objeto.setComprador(p);
    }
    
    public VendaDAO getDao() {
        return dao;
    }

    public void setDao(VendaDAO dao) {
        this.dao = dao;
    }

    public Venda getObjeto() {
        return objeto;
    }

    public void setObjeto(Venda objeto) {
        this.objeto = objeto;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public VeiculoDAO getDaoVeiculo() {
        return daoVeiculo;
    }

    public void setDaoVeiculo(VeiculoDAO daoVeiculo) {
        this.daoVeiculo = daoVeiculo;
    }

    public MeioPagamentoDAO getDaoMeioPagamento() {
        return daoMeioPagamento;
    }

    public void setDaoMeioPagamento(MeioPagamentoDAO daoMeioPagamento) {
        this.daoMeioPagamento = daoMeioPagamento;
    }

    public PessoaFisicaDAO getDaoPessoaFisica() {
        return daoPessoaFisica;
    }

    public void setDaoPessoaFisica(PessoaFisicaDAO daoPessoaFisica) {
        this.daoPessoaFisica = daoPessoaFisica;
    }

    public PessoaJuridicaDAO getDaoPessoaJuridica() {
        return daoPessoaJuridica;
    }

    public void setDaoPessoaJuridica(PessoaJuridicaDAO daoPessoaJuridica) {
        this.daoPessoaJuridica = daoPessoaJuridica;
    }
    
}