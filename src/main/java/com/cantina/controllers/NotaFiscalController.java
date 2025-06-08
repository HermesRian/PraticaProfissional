package com.cantina.controllers;

import com.cantina.entities.Cliente;
import com.cantina.entities.NotaFiscal;
import com.cantina.entities.Produto;
import com.cantina.services.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas-fiscais")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @GetMapping
    public List<NotaFiscal> listarTodos() {

        return notaFiscalService.listarTodos();
    }

    @GetMapping("/{id}")
    public NotaFiscal buscarPorId(@PathVariable Long id) {

        return notaFiscalService.buscarPorId(id);
    }

    @PostMapping
    public NotaFiscal salvar(@RequestBody NotaFiscal notaFiscal) {

        return notaFiscalService.salvar(notaFiscal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaFiscal> atualizar(@PathVariable Long id, @RequestBody NotaFiscal notaFiscal) {
        NotaFiscal notaFiscalAtualizada = notaFiscalService.atualizar(id, notaFiscal);
        return ResponseEntity.ok(notaFiscalAtualizada);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {

        notaFiscalService.excluir(id);
    }
}