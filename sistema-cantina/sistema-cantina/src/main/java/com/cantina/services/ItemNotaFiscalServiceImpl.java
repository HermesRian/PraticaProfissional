package com.cantina.services;

import com.cantina.entities.ItemNotaFiscal;
import com.cantina.repositories.ItemNotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemNotaFiscalServiceImpl implements ItemNotaFiscalService {

    @Autowired
    private ItemNotaFiscalRepository itemNotaFiscalRepository;

    @Override
    public List<ItemNotaFiscal> listarTodos() {
        return itemNotaFiscalRepository.findAll();
    }

    @Override
    public ItemNotaFiscal salvar(ItemNotaFiscal itemNotaFiscal) {
        return itemNotaFiscalRepository.save(itemNotaFiscal);
    }

    @Override
    public ItemNotaFiscal buscarPorId(Long id) {
        Optional<ItemNotaFiscal> itemNotaFiscal = itemNotaFiscalRepository.findById(id);
        return itemNotaFiscal.orElseThrow(() -> new RuntimeException("Item da Nota Fiscal não encontrado"));
    }

    @Override
    public void excluir(Long id) {
        itemNotaFiscalRepository.deleteById(id);
    }
}