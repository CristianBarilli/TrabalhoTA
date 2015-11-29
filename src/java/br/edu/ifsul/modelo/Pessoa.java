/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Devel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public class Pessoa implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotBlank(message = "O telefone deve ser informado")
    @Length(max = 14, message = "O telefone não deve possuir mais de {max} caracteres")
    @Column(name = "telefone", length = 14, nullable = false)
    private String telefone;
    
    @NotBlank(message = "O endereco deve ser informado")
    @Length(max = 100, message = "O endereco não deve possuir mais de {max} caracteres")
    @Column(name = "endereco", length = 100, nullable = false)
    private String endereco;
    
    @NotBlank(message = "O bairro deve ser informado")
    @Length(max = 30, message = "O bairro não deve possuir mais de {max} caracteres")
    @Column(name = "bairro", length = 30, nullable = false)
    private String bairro;
    
    @NotBlank(message = "O cep deve ser informado")
    @Length(max = 9, message = "O cep não deve possuir mais de {max} caracteres")
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;
    
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email deve ser informado")
    @Length(max = 50, message = "O email não deve possuir mais de {max} caracteres")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    public Pessoa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
