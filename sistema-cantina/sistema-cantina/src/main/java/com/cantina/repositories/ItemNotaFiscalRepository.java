package com.cantina.repositories;

import com.cantina.entities.ItemNotaFiscal;
import java.util.List;

public interface ItemNotaFiscalRepository {
    void save(ItemNotaFiscal itemNotaFiscal);
    ItemNotaFiscal findById(Long id);
    List<ItemNotaFiscal> findAll();
    void update(ItemNotaFiscal itemNotaFiscal);
    void delete(Long id);
}