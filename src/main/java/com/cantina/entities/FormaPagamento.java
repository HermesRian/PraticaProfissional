package com.cantina.entities;

import java.util.List;

public class FormaPagamento {
    private Long id;
    private String descricao;
    private String codigo;
    private String tipo;
    private Boolean ativo;
   // private List<NotaFiscal> notasFiscais;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

//    public List<NotaFiscal> getNotasFiscais() {
//
//        return notasFiscais;
//    }
//
//    public void setNotasFiscais(List<NotaFiscal> notasFiscais) {
//
//        this.notasFiscais = notasFiscais;
//    }
    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public String getCodigo() {

        return codigo;
    }

    public void setCodigo(String codigo) {

        this.codigo = codigo;
    }

    public String getTipo() {

        return tipo;
    }

    public void setTipo(String tipo) {

        this.tipo = tipo;
    }

    public Boolean getAtivo() {

        return ativo;
    }

    public void setAtivo(Boolean ativo) {

        this.ativo = ativo;
    }
}