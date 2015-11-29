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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Devel
 */

@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable{
    
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq_veiculo", sequenceName = "seq_veiculo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_veiculo", strategy = GenerationType.SEQUENCE)    
    private Integer id;
    
    @NotEmpty(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @NotEmpty(message = "O modelo deve ser informado")
    @Length(max = 50, message = "O modelo não pode ter mais de {max} caracteres")
    @Column(name = "modelo", length = 50, nullable = false)
    private String modelo;
    
    @NotNull(message = "O ano deve ser informado")
    @Column(name = "ano", length = 4, nullable = false)
    private Integer ano;
    
    @NotNull(message = "A quilometragem deve ser informada")
    @Column(name = "quilometragem", length = 6, nullable = false)
    @Max(value = 999999, message = "O valor máximo é 999999")
    @Min(value = 0, message = "o valor mínimo é 0")
    private Integer quilometragem;
    
    @NotEmpty(message = "A cor deve ser informada")
    @Length(max = 50, message = "A cor não pode ter mais de {max} caracteres")
    @Column(name = "cor", length = 50, nullable = false)
    private String cor;
    
    @NotEmpty(message = "A placa deve ser informada")
    @Length(max = 8, min = 8, message = "A placa deve ter {max} caracteres")
    @Column(name = "placa", length = 8, nullable = false)
    private String placa;
    
    @NotEmpty(message = "O combustivel deve ser informado")
    @Length(max = 50, message = "O combustivel não pode ter mais de {max} caracteres")
    @Column(name = "combustivel", length = 50, nullable = false)
    private String combustivel;
    
    @NotNull(message = "O preço deve ser informado")
    @Column(name = "preco", nullable = false)
    @Digits(integer = 10, fraction = 2, message = "Preço inválido")
    private Double preco;
    
    private Boolean unico_dono;
    
    private Boolean vendido;
    
    @NotNull(message = "A montadora deve ser informada")
    @ManyToOne
    @JoinColumn(name = "montadora", referencedColumnName = "id")
    private Montadora montadora;
    
    @NotNull(message = "O Tipo deve ser informado")
    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id")
    private Tipo tipo;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "veiculos_opcionais",
            joinColumns = 
            @JoinColumn(name = "veiculo", referencedColumnName = "id"), 
            inverseJoinColumns = 
            @JoinColumn(name = "opcional", referencedColumnName = "id"))
    private List<Opcional> opcionais = new ArrayList<>();
    
    public Veiculo() {
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa.toUpperCase();
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }    

    public Boolean getUnico_dono() {
        return unico_dono;
    }

    public void setUnico_dono(Boolean unico_dono) {
        this.unico_dono = unico_dono;
    }

    public List<Opcional> getOpcionais() {
        return opcionais;
    }

    public void setOpcionais(List<Opcional> opcionais) {
        this.opcionais = opcionais;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return nome;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }
    
}
