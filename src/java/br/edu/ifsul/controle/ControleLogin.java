/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.AcessoUsuario;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Devel
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {
    
    @EJB
    private UsuarioDAO<Usuario> dao;
    private Usuario usuarioLogado;
    private String usuario;
    private String senha;
    
    public ControleLogin() {
        
    }
    
    public String paginaLogin() {
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin() {
        if (dao.login(usuario, senha)) {
            usuarioLogado = dao.localizaPorNomeUsuario(usuario);
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String ip = request.getRemoteAddr();        
            AcessoUsuario acessoUsuario = new AcessoUsuario(request.getRemoteAddr());
            usuarioLogado.adicionarAcesso(acessoUsuario);
            try {
                dao.merge(usuarioLogado);
            } catch (Exception e) {
                Util.mensagemInformacao("Erro ao persistir acesso do usuário: " + e.getMessage());
            }
            Util.mensagemInformacao("Login efetuado com sucesso");
            return "/index";
        } else {
            Util.mensagemInformacao("Usuário ou senha inválidos");
            return "/login";
        }
    }
    
    public String efetuarLogout() {
        usuarioLogado = null;
        Util.mensagemInformacao("Logout efetuado com sucesso");
        return "/index";
    }
    
    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }
    
    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}