/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Devel
 */
@Entity
@Table(name = "opcional")
public class Opcional implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_opcional", sequenceName = "seq_opcional_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_opcional", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotEmpty(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @NotEmpty(message = "A descrição deve ser informada")
    @Length(max = 50, message = "A descrição não pode ter mais de {max} caracteres")
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;
    
    @NotEmpty(message = "A utilidade deve ser informada")
    @Length(max = 100, message = "A utilidade não pode ter mais de {max} caracteres")
    @Column(name = "utilidade", length = 100, nullable = false)
    private String utilidade;
    
    @ManyToMany
    @JoinTable(name = "veiculos_opcionais",
            joinColumns = 
            @JoinColumn(name = "opcional", referencedColumnName = "id"), 
            inverseJoinColumns = 
            @JoinColumn(name = "veiculo", referencedColumnName = "id"))
    private List<Veiculo> veiculos = new ArrayList<>();

    public Opcional() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUtilidade() {
        return utilidade;
    }

    public void setUtilidade(String utilidade) {
        this.utilidade = utilidade;
    }  

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Opcional other = (Opcional) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
