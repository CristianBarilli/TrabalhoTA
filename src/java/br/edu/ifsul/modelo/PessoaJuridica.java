/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author Devel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa implements Serializable{
    
    @CNPJ(message = "O CNPJ deve ser válido")
    @NotBlank(message = "O CNPJ deve ser informado")
    @Length(max = 18, message = "O cnpj não deve possuir mais de {max} caracteres")
    @Column(name = "cnpj", length = 18, nullable = false, unique = true)
    private String cnpj;
    
    @NotBlank(message = "O IE deve ser informado")
    @Length(max = 12, message = "O IE não deve possuir mais de {max} caracteres")
    @Column(name = "ie", length = 12, nullable = false, unique = true)
    private String ie;
    
    @NotBlank(message = "A razão social deve ser informada")
    @Length(max = 50, message = "A razão social não deve possuir mais de {max} caracteres")
    @Column(name = "razao_social", length = 50, nullable = false)
    private String razaoSocial;
    
    @NotBlank(message = "O nome fantasia deve ser informado")
    @Length(max = 50, message = "O nome fantasia não deve possuir mais de {max} caracteres")
    @Column(name = "nome_fantasia", length = 50, nullable = false)
    private String nomeFantasia;
    
    @NotBlank(message = "O responsável deve ser informado")
    @Length(max = 50, message = "O responsável não deve possuir mais de {max} caracteres")
    @Column(name = "responsavel", length = 50, nullable = false)
    private String responsavel;

    public PessoaJuridica() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    
    
}
