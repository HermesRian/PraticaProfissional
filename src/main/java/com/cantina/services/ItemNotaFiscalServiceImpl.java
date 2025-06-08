package com.cantina.services;

import com.cantina.database.ItemNotaFiscalDAO;
import com.cantina.entities.ItemNotaFiscal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemNotaFiscalServiceImpl implements ItemNotaFiscalService {

    private final ItemNotaFiscalDAO itemNotaFiscalDAO = new ItemNotaFiscalDAO();

    @Override
    public List<ItemNotaFiscal> listarTodos() {

        return itemNotaFiscalDAO.listarTodos();
    }

    @Override
    public ItemNotaFiscal salvar(ItemNotaFiscal itemNotaFiscal) {
        itemNotaFiscalDAO.salvar(itemNotaFiscal);
        return itemNotaFiscal;
    }

    @Override
    public ItemNotaFiscal buscarPorId(Long id) {
        return itemNotaFiscalDAO.buscarPorId(id);
    }

    @Override
    public ItemNotaFiscal atualizar(Long id, ItemNotaFiscal itemNotaFiscal) {
        ItemNotaFiscal itemExistente = itemNotaFiscalDAO.buscarPorId(id);
        if (itemExistente == null) {
            throw new IllegalArgumentException("Item Nota Fiscal com o ID " + id + " não encontrado.");
        }
        itemNotaFiscal.setId(id);
        itemNotaFiscalDAO.atualizar(itemNotaFiscal);
        return itemNotaFiscal;
    }


    @Override
    public void excluir(Long id) {

        itemNotaFiscalDAO.excluir(id);
    }
}