package com.cantina.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Funcionario {
    private Long id;
    private String nome;
    private String cargo;
    private BigDecimal salario;
    private String email;
    private String telefone;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Long cidadeId;
    private Boolean ativo;
    private Date dataAdmissao;
    private Date dataDemissao;
    private String apelido;
    private Timestamp dataCriacao;
    private Timestamp dataAlteracao;
    private String rgInscricaoEstadual;
    private String cnh;
    private Date dataValidadeCnh;
    private Integer sexo;
    private String observacao;
    private Integer estadoCivil;
    private Integer isBrasileiro;
    private Integer nacionalidade;
    private Date dataNascimento;
    private Long funcaoFuncionarioId;
    private String cpfCnpj;

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

    public String getCargo() {

        return cargo;
    }

    public void setCargo(String cargo) {

        this.cargo = cargo;
    }

    public BigDecimal getSalario() {

        return salario;
    }

    public void setSalario(BigDecimal salario) {

        this.salario = salario;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getTelefone() {

        return telefone;
    }

    public void setTelefone(String telefone) {

        this.telefone = telefone;
    }

    public String getEndereco() {

        return endereco;
    }

    public void setEndereco(String endereco) {

        this.endereco = endereco;
    }

    public String getNumero() {

        return numero;
    }

    public void setNumero(String numero) {

        this.numero = numero;
    }

    public String getComplemento() {

        return complemento;
    }

    public void setComplemento(String complemento) {

        this.complemento = complemento;
    }

    public String getBairro() {

        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Timestamp getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Timestamp dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getRgInscricaoEstadual() {
        return rgInscricaoEstadual;
    }

    public void setRgInscricaoEstadual(String rgInscricaoEstadual) {
        this.rgInscricaoEstadual = rgInscricaoEstadual;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Date getDataValidadeCnh() {
        return dataValidadeCnh;
    }

    public void setDataValidadeCnh(Date dataValidadeCnh) {
        this.dataValidadeCnh = dataValidadeCnh;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getEstadoCivil() {

        return estadoCivil;
    }

    public void setEstadoCivil(Integer estadoCivil) {

        this.estadoCivil = estadoCivil;
    }

    public Integer getIsBrasileiro() {

        return isBrasileiro;
    }

    public void setIsBrasileiro(Integer isBrasileiro) {

        this.isBrasileiro = isBrasileiro;
    }

    public Integer getNacionalidade() {

        return nacionalidade;
    }

    public void setNacionalidade(Integer nacionalidade) {

        this.nacionalidade = nacionalidade;
    }

    public Date getDataNascimento() {

        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {

        this.dataNascimento = dataNascimento;
    }

    public Long getFuncaoFuncionarioId() {

        return funcaoFuncionarioId;
    }

    public void setFuncaoFuncionarioId(Long funcaoFuncionarioId) {

        this.funcaoFuncionarioId = funcaoFuncionarioId;
    }

    public String getCpfCnpj() {

        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {

        this.cpfCnpj = cpfCnpj;
    }
}