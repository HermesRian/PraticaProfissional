package com.cantina.controllers;

import com.cantina.entities.FuncaoFuncionario;
import com.cantina.services.FuncaoFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcoes-funcionario")
public class FuncaoFuncionarioController {

    @Autowired
    private FuncaoFuncionarioService funcaoFuncionarioService;

    @GetMapping
    public List<FuncaoFuncionario> listarTodos() {
        return funcaoFuncionarioService.listarTodos();
    }

    @GetMapping("/ativos")
    public List<FuncaoFuncionario> listarAtivos() {
        return funcaoFuncionarioService.listarAtivos();
    }

    @GetMapping("/{id}")
    public FuncaoFuncionario buscarPorId(@PathVariable Long id) {
        return funcaoFuncionarioService.buscarPorId(id);
    }

    @PostMapping
    public FuncaoFuncionario salvar(@RequestBody FuncaoFuncionario funcaoFuncionario) {
        return funcaoFuncionarioService.salvar(funcaoFuncionario);
    }

    @PutMapping("/{id}")
    public FuncaoFuncionario atualizar(@PathVariable Long id, @RequestBody FuncaoFuncionario funcaoFuncionario) {
        return funcaoFuncionarioService.atualizar(id, funcaoFuncionario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        funcaoFuncionarioService.excluir(id);
    }
}
