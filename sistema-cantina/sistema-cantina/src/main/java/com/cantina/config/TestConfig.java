//package com.cantina.config;
//
//import com.cantina.entities.Fornecedor;
//import com.cantina.repositories.FornecedorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TestConfig implements CommandLineRunner {
//
//    @Autowired
//    private FornecedorRepository fornecedorRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // fornecedor de teste
//        Fornecedor fornecedor = new Fornecedor();
//        fornecedor.setNome("Fornecedor Teste");
//        fornecedor.setCnpj("12345678901234");
//        fornecedor.setEndereco("Rua Teste, 123");
//        fornecedor.setTelefone("11999999999");
//
//        fornecedorRepository.save(fornecedor);
//
//        System.out.println("Fornecedores cadastrados:");
//        fornecedorRepository.findAll().forEach(System.out::println);
//    }
//}