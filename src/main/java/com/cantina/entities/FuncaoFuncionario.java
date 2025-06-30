package com.cantina.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class FuncaoFuncionario {
    private Long id;
    private String descricao;
    private Boolean ativo;
    private Timestamp dataCadastro;
    private Timestamp ultimaModificacao;
    private String nome;
    private Boolean requerCnh;
    private BigDecimal cargaHoraria;
    private String observacao;
    private String userCriacao;
    private String userAtualizacao;

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

    public Boolean getAtivo() {

        return ativo;
    }

    public void setAtivo(Boolean ativo) {

        this.ativo = ativo;
    }

    public Timestamp getDataCadastro() {

        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {

        this.dataCadastro = dataCadastro;
    }

    public Timestamp getUltimaModificacao() {

        return ultimaModificacao;
    }

    public void setUltimaModificacao(Timestamp ultimaModificacao) {

        this.ultimaModificacao = ultimaModificacao;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public Boolean getRequerCnh() {

        return requerCnh;
    }

    public void setRequerCnh(Boolean requerCnh) {

        this.requerCnh = requerCnh;
    }

    public BigDecimal getCargaHoraria() {

        return cargaHoraria;
    }

    public void setCargaHoraria(BigDecimal cargaHoraria) {

        this.cargaHoraria = cargaHoraria;
    }

    public String getObservacao() {

        return observacao;
    }

    public void setObservacao(String observacao) {

        this.observacao = observacao;
    }

    public String getUserCriacao() {

        return userCriacao;
    }

    public void setUserCriacao(String userCriacao) {

        this.userCriacao = userCriacao;
    }

    public String getUserAtualizacao() {

        return userAtualizacao;
    }

    public void setUserAtualizacao(String userAtualizacao) {

        this.userAtualizacao = userAtualizacao;
    }
}
