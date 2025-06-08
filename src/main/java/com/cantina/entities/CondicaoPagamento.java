package com.cantina.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class CondicaoPagamento {
    private Long id;
    private String descricao;
    private Integer dias;
    private Integer parcelas;
    private Boolean ativo;
    private Double jurosPercentual;
    private Double multaPercentual;
    private Double descontoPercentual;

    @JsonManagedReference
    private List<ParcelaCondicaoPagamento> parcelasCondicao;

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

    public Double getJurosPercentual() {

        return jurosPercentual;
    }

    public void setJurosPercentual(Double jurosPercentual) {

        this.jurosPercentual = jurosPercentual;
    }

    public Double getMultaPercentual() {

        return multaPercentual;
    }

    public void setMultaPercentual(Double multaPercentual) {

        this.multaPercentual = multaPercentual;
    }

    public Double getDescontoPercentual() {

        return descontoPercentual;
    }

    public void setDescontoPercentual(Double descontoPercentual) {

        this.descontoPercentual = descontoPercentual;
    }

    public List<ParcelaCondicaoPagamento> getParcelasCondicao() {

        return parcelasCondicao;
    }

    public void setParcelasCondicao(List<ParcelaCondicaoPagamento> parcelasCondicao) {

        this.parcelasCondicao = parcelasCondicao;
    }
}