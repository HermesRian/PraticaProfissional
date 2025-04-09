package com.cantina.entities;

import java.time.LocalDateTime;

public class MovimentacaoNfe {

    private Long id;
    private Long notaFiscalId;
    private LocalDateTime dataMovimentacao;
    private String status;
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNotaFiscalId() {
        return notaFiscalId;
    }

    public void setNotaFiscalId(Long notaFiscalId) {
        this.notaFiscalId = notaFiscalId;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}