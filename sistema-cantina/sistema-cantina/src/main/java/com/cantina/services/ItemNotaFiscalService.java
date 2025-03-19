package com.cantina.services;

import com.cantina.entities.ItemNotaFiscal;
import java.util.List;

public interface ItemNotaFiscalService {
    List<ItemNotaFiscal> listarTodos();
    ItemNotaFiscal salvar(ItemNotaFiscal itemNotaFiscal);
    ItemNotaFiscal buscarPorId(Long id);
    void excluir(Long id);
}