/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Devel
 */
@Stateful
public class UsuarioDAO<T> extends GenericDAO<Usuario> implements Serializable {

    public UsuarioDAO() {
        super();
        //definir a classe persistente
        super.setPersistentClass(Usuario.class);
        //definir a lista de ordenações
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        //definir a ordem atual
        super.setCurrentOrder(super.getListOrder().get(1));
        //inicializar o filtro
        super.setFilter("");
        //inicializar o conversor
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));

    }

    @Override
    public Usuario getObjectById(Integer id) throws Exception {
        Usuario obj = super.getEm().find(Usuario.class, id);
        obj.getAcessos().size();
        return obj;
    }

    public boolean login(String usuario, String senha) {
        Query query = super.getEm()
                .createQuery(
                        "from Usuario where upper(apelido) = :usuario and "
                        + "upper(senha) = :senha and ativo = true");
        query.setParameter("usuario", usuario.toUpperCase());
        query.setParameter("senha", senha.toUpperCase());
        if (!query.getResultList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario localizaPorNomeUsuario(String usuario) {
        Usuario obj = (Usuario) super.getEm().createQuery("from Usuario where upper(apelido) = :usuario").
                setParameter("usuario", usuario.toUpperCase()).getSingleResult();
        obj.getAcessos().size();
        return obj;
    }

}
