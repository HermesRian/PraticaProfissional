package com.cantina.controllers;

import com.cantina.entities.Pais;
import com.cantina.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public ResponseEntity<List<Pais>> listarTodos() {
        List<Pais> paises = paisService.listarTodos();
        return ResponseEntity.ok(paises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> buscarPorId(@PathVariable Long id) {
        Pais pais = paisService.buscarPorId(id);
        if (pais != null) {
            return ResponseEntity.ok(pais);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pais> salvar(@RequestBody Pais pais) {
        Pais novoPais = paisService.salvar(pais);
        return ResponseEntity.ok(novoPais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> atualizar(@PathVariable Long id, @RequestBody Pais pais) {
        Pais paisAtualizado = paisService.atualizar(id, pais);
        if (paisAtualizado != null) {
            return ResponseEntity.ok(paisAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        paisService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}