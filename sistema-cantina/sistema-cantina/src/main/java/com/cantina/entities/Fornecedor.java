package com.cantina.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;

//    @OneToMany(mappedBy = "fornecedor")
//    private List<NotaFiscal> notasFiscais;

    // Getters e Setters
}