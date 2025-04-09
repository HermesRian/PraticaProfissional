package com.cantina.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class NotaFiscal {
    private Long id;
    private Date dataEmissao;
    private BigDecimal valorTotal; // Alterado para BigDecimal
    private CondicaoPagamento condicaoPagamento;
    private FormaPagamento formaPagamento;
    private Fornecedor fornecedor;
    private String numero;
    private String serie;
    private String chaveAcesso;
    private Cliente cliente;
    private Transportadora transportadora;
    private Veiculo veiculo;
    private ModalidadeNfe modalidade;
    private Boolean cancelada;
    private List<ItemNotaFiscal> itensNotaFiscal;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public CondicaoPagamento getCondicaoPagamento() {
        return condicaoPagamento;
    }

    public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ModalidadeNfe getModalidade() {
        return modalidade;
    }

    public void setModalidade(ModalidadeNfe modalidade) {
        this.modalidade = modalidade;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public List<ItemNotaFiscal> getItensNotaFiscal() {
        return itensNotaFiscal;
    }

    public void setItensNotaFiscal(List<ItemNotaFiscal> itensNotaFiscal) {
        this.itensNotaFiscal = itensNotaFiscal;
    }
}