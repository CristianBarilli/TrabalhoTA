/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Devel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa implements Serializable{
    @CPF(message = "O CPF deve ser válido")
    @NotBlank(message = "O CPF deve ser informado")
    @Length(max = 14, message = "O CPF não deve possuir mais de {max} caracteres")
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;
    
    @NotBlank(message = "O RG deve ser informado")
    @Length(max = 10, message = "O RG não deve possuir mais de {max} caracteres")
    @Column(name = "rg", length = 10, nullable = false)
    private String rg;
    
    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome não deve possuir mais de {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @NotNull(message = "A data de nascimento deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento", nullable = false)
    private Calendar data_nasc;
    
    @NotNull(message = "O sexo deve ser informado")
    @Column(name = "sexo", nullable = false)
    private String sexo;

    public PessoaFisica() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Calendar getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Calendar data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
