package com.cantina.controllers;

import com.cantina.entities.Cidade;
import com.cantina.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listarTodas() {
        return cidadeService.listarTodas();
    }

    @GetMapping("/{id}")
    public Cidade buscarPorId(@PathVariable Long id) {
        return cidadeService.buscarPorId(id);
    }

    @PostMapping
    public Cidade salvar(@RequestBody Cidade cidade) {
        return cidadeService.salvar(cidade);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        cidadeService.excluir(id);
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        return cidadeService.atualizar(id, cidade);
    }
}