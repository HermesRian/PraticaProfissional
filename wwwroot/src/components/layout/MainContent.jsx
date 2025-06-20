import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';

import Dashboard from '../Dashboard/Dashboard';

import ClienteList from '../Cliente/ClienteListMUI';
import ClienteForm from '../Cliente/ClienteFormMUI';

import FornecedorList from '../Fornecedor/FornecedorList';
import FornecedorForm from '../Fornecedor/FornecedorForm';

import FuncionarioList from '../Funcionario/FuncionarioList';
import FuncionarioForm from '../Funcionario/FuncionarioForm';

import CondicaoPagamentoList from '../CondicaoPagamento/CondicaoPagamentoList';
import CondicaoPagamentoForm from '../CondicaoPagamento/CondicaoPagamentoForm';

import FormaPagamentoList from '../FormaPagamento/FormaPagamentoList';
import FormaPagamentoForm from '../FormaPagamento/FormaPagamentoForm';

import ProdutoList from '../Produto/ProdutoList';
import ProdutoForm from '../Produto/ProdutoForm';

import CidadeList from '../Cidade/CidadeList';
import CidadeForm from '../Cidade/CidadeForm';

import PaisList from '../Pais/PaisList';
import PaisForm from '../Pais/PaisForm';

import EstadoList from '../Estado/EstadoList';
import EstadoForm from '../Estado/EstadoForm';
import EstadoModal from '../Estado/EstadoModal';
import EstadoFormModal from '../Estado/EstadoFormModal';

const MainContent = () => {
  return (
    <main>      
    <Routes>
        {/* Rota padrão - redireciona para dashboard */}
        <Route path="/" element={<Navigate to="/dashboard" replace />} />
        
        {/* Dashboard */}
        <Route path="/dashboard" element={<Dashboard />} />

        {/* Clientes */}
        <Route path="/clientes" element={<ClienteList />} />
        <Route path="/clientes/cadastrar" element={<ClienteForm />} />
        <Route path="/clientes/editar/:id" element={<ClienteForm />} />

        <Route path="/fornecedores" element={<FornecedorList />} />
        <Route path="/fornecedores/cadastrar" element={<FornecedorForm />} />
        <Route path="/fornecedores/editar/:id" element={<FornecedorForm />} />

        <Route path="/funcionarios" element={<FuncionarioList />} />
        <Route path="/funcionarios/cadastrar" element={<FuncionarioForm />} />
        <Route path="/funcionarios/editar/:id" element={<FuncionarioForm />} />

        <Route path="/formas-pagamento" element={<FormaPagamentoList />} />
        <Route path="/formas-pagamento/cadastrar" element={<FormaPagamentoForm />} />
        <Route path="/formas-pagamento/editar/:id" element={<FormaPagamentoForm />} />

        <Route path="/condicoes-pagamento" element={<CondicaoPagamentoList />} />
        <Route path="/condicoes-pagamento/cadastrar" element={<CondicaoPagamentoForm />} />
        <Route path="/condicoes-pagamento/editar/:id" element={<CondicaoPagamentoForm />} />

        <Route path="/produtos" element={<ProdutoList />} />
        <Route path="/produtos/cadastrar" element={<ProdutoForm />} />
        <Route path="/produtos/editar/:id" element={<ProdutoForm />} />

        <Route path="/cidades" element={<CidadeList />} />
        <Route path="/cidades/cadastrar" element={<CidadeForm />} />
        <Route path="/cidades/editar/:id" element={<CidadeForm />} />

        <Route path="/paises" element={<PaisList />} />
        <Route path="/paises/cadastrar" element={<PaisForm />} />
        <Route path="/paises/editar/:id" element={<PaisForm />} />

        <Route path="/estados" element={<EstadoList />} />
        <Route path="/estados/cadastrar" element={<EstadoForm />} />
        <Route path="/estados/editar/:id" element={<EstadoForm />} />
      </Routes>
    </main>
  );
};


export default MainContent;