package com.cantina.entities;

import java.util.List;

public class CondicaoPagamento {
    private Long id;
    private String descricao;
    private Integer dias;
    private Integer parcelas;
    private Boolean ativo;
    private List<NotaFiscal> notasFiscais;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public List<NotaFiscal> getNotasFiscais() {

        return notasFiscais;
    }

    public void setNotasFiscais(List<NotaFiscal> notasFiscais) {
        this.notasFiscais = notasFiscais;
    }

    public Integer getDias() {

        return dias;
    }
    public void setDias(Integer dias) {

        this.dias = dias;
    }
    public Integer getParcelas() {

        return parcelas;
    }
    public void setParcelas(Integer parcelas) {

        this.parcelas = parcelas;
    }
    public Boolean getAtivo() {

        return ativo;
    }
    public void setAtivo(Boolean ativo) {

        this.ativo = ativo;
    }
}