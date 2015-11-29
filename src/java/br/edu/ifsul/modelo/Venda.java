/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Devel
 */
@Entity
@Table(name = "venda")
public class Venda implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message = "A data da venda deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento", nullable = false)
    private Calendar data;
    
    @NotNull(message = "O valor deve ser informado")
    @Column(name = "valor", nullable = false)
    @Digits(integer = 10, fraction = 2, message = "Valor inválido")
    @DecimalMin(value = "1.00", message = "Valor da venda inválido")
    private Double valor;
    
    @NotNull(message = "O meio de pagamento deve ser informado")
    @ManyToOne
    @JoinColumn(name = "meio_pagamento", referencedColumnName = "id")
    private MeioPagamento meio_pagamento;
    
    @ManyToMany
    @JoinTable(name = "venda_veiculos",
            joinColumns = 
            @JoinColumn(name = "venda", referencedColumnName = "id"), 
            inverseJoinColumns = 
            @JoinColumn(name = "veiculo", referencedColumnName = "id"))
    private List<Veiculo> veiculos = new ArrayList<>();
    
    @NotNull(message = "O comprador deve ser informado")
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = true)
    private Pessoa comprador;

    public Venda() {
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public MeioPagamento getMeio_pagamento() {
        return meio_pagamento;
    }

    public void setMeio_pagamento(MeioPagamento meio_pagamento) {
        this.meio_pagamento = meio_pagamento;
    }

    public Pessoa getComprador() {
        return comprador;
    }

    public void setComprador(Pessoa comprador) {
        this.comprador = comprador;
    }
    
    
}
