/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Devel
 */
@Entity
@Table(name = "acesso_usuario")
public class AcessoUsuario implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seq_acesso", sequenceName = "seq_acesso_usuario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso")
    private Integer id;

    @NotNull(message = "A data de acesso deve ser informada")
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;

    @NotNull(message = "O usuário deve ser informado")
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @NotEmpty(message = "O IP de acesso deve ser informado")
    @Length(max = 15, message = "O IP de acesso não pode ter mais que {max} caracteres")
    @Column(name = "ip_acesso", length = 15, nullable = false)
    private String ipAcesso;

    public AcessoUsuario() {
    }

    public AcessoUsuario(String ipAcesso) {
        this.data = Calendar.getInstance();
        this.ipAcesso = ipAcesso;
    }

    @Transient
    public String getDataAcessoFormatada() {
        return new SimpleDateFormat(
                "dd/MM/yyyy HH:mm").format(data.getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIpAcesso() {
        return ipAcesso;
    }

    public void setIpAcesso(String ipAcesso) {
        this.ipAcesso = ipAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AcessoUsuario other = (AcessoUsuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
