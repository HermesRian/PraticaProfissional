package com.cantina.controllers;

import com.cantina.entities.NotaFiscal;
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
    public ResponseEntity<List<NotaFiscal>> listarTodos() {
        List<NotaFiscal> notasFiscais = notaFiscalService.listarTodos();
        return ResponseEntity.ok(notasFiscais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscal> buscarPorId(@PathVariable Long id) {
        NotaFiscal notaFiscal = notaFiscalService.buscarPorId(id);
        return ResponseEntity.ok(notaFiscal);
    }

    @PostMapping
    public ResponseEntity<NotaFiscal> salvar(@RequestBody NotaFiscal notaFiscal) {
        NotaFiscal novaNotaFiscal = notaFiscalService.salvar(notaFiscal);
        return ResponseEntity.ok(novaNotaFiscal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        notaFiscalService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}