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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Devel
 */
@Entity
@Table(name = "meio_pagamento")
public class MeioPagamento implements Serializable{
    
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq_meiopagamento", sequenceName = "seq_meiopagamento_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_meiopagamento", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotEmpty(message = "A descrição deve ser informada")
    @Length(max = 50, message = "A descrição não pode ter mais de {max} caracteres")
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;

    public MeioPagamento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final MeioPagamento other = (MeioPagamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
