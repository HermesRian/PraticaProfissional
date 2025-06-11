import React from 'react';
import FornecedorList from '../Fornecedor/FornecedorList';
import FornecedorForm from '../Fornecedor/FornecedorForm';
import { Routes, Route } from 'react-router-dom';

const Fornecedores = () => {
  return (
    <Routes>
      <Route path="/" element={<FornecedorList />} />
      <Route path="/cadastrar" element={<FornecedorForm />} />
      <Route path="/editar/:id" element={<FornecedorForm />} />
    </Routes>
  );
};

export default Fornecedores;