package com.cantina.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double preco;
    private Integer quantidadeEstoque;

    @OneToMany(mappedBy = "produto")
    private List<ItemNotaFiscal> itensNotaFiscal;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public List<ItemNotaFiscal> getItensNotaFiscal() {
        return itensNotaFiscal;
    }

    public void setItensNotaFiscal(List<ItemNotaFiscal> itensNotaFiscal) {
        this.itensNotaFiscal = itensNotaFiscal;
    }
}