import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import CidadeModal from '../Cidade/CidadeModal'; // Modal de seleção de cidades

const FuncionarioForm = () => {
  const [funcionario, setFuncionario] = useState({
    nome: '',
    cpf: '',
    cargo: '',
    salario: '',
    email: '',
    telefone: '',
    endereco: '',
    numero: '',
    complemento: '',
    bairro: '',
    cep: '',
    cidadeId: '',
    cidadeNome: 'Selecione uma Cidade', // Nome da cidade selecionada
    ativo: true,
    dataAdmissao: '',
  });

  const [isCidadeModalOpen, setIsCidadeModalOpen] = useState(false); // Controle do modal de seleção de cidades
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      fetch(`http://localhost:8080/funcionarios/${id}`)
        .then((response) => response.json())
        .then((data) => {
          setFuncionario({
            ...data,
            cidadeNome: data.cidadeNome || 'Selecione uma Cidade',
            ativo: data.ativo || false, // Garante que o checkbox seja tratado corretamente
          });
        })
        .catch((error) => console.error('Erro ao buscar funcionário:', error));
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFuncionario({ ...funcionario, [name]: type === 'checkbox' ? checked : value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const method = id ? 'PUT' : 'POST';
    const url = id ? `http://localhost:8080/funcionarios/${id}` : 'http://localhost:8080/funcionarios';

    fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(funcionario),
    })
      .then(() => {
        setFuncionario({
          nome: '',
          cpf: '',
          cargo: '',
          salario: '',
          email: '',
          telefone: '',
          endereco: '',
          numero: '',
          complemento: '',
          bairro: '',
          cep: '',
          cidadeId: '',
          cidadeNome: 'Selecione uma Cidade',
          ativo: true,
          dataAdmissao: '',
        });
        navigate('/funcionarios');
      })
      .catch((error) => console.error('Erro ao salvar funcionário:', error));
  };

  const handleCancel = () => {
    navigate('/funcionarios');
  };

  const handleOpenCidadeModal = () => {
    setIsCidadeModalOpen(true);
  };

  const handleCloseCidadeModal = () => {
    setIsCidadeModalOpen(false);
  };

  const handleCidadeSelecionada = (cidade) => {
    setFuncionario({ ...funcionario, cidadeId: cidade.id, cidadeNome: cidade.nome });
    setIsCidadeModalOpen(false);
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1 style={{ textAlign: 'center', marginBottom: '20px' }}>
        {id ? 'Editar Funcionário' : 'Cadastrar Novo Funcionário'}
      </h1>
      <form
        onSubmit={handleSubmit}
        style={{
          display: 'flex',
          flexDirection: 'column',
          gap: '15px',
          maxWidth: '400px',
          margin: '0 auto',
          padding: '20px',
          border: '1px solid #ccc',
          borderRadius: '8px',
          backgroundColor: '#f9f9f9',
          boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
        }}
      >
        <input
          type="text"
          name="nome"
          value={funcionario.nome}
          onChange={handleChange}
          placeholder="Nome"
          required
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="text"
          name="cpf"
          value={funcionario.cpf}
          onChange={handleChange}
          placeholder="CPF"
          required
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="text"
          name="cargo"
          value={funcionario.cargo}
          onChange={handleChange}
          placeholder="Cargo"
          required
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="number"
          name="salario"
          value={funcionario.salario}
          onChange={handleChange}
          placeholder="Salário"
          required
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="email"
          name="email"
          value={funcionario.email}
          onChange={handleChange}
          placeholder="Email"
          required
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="text"
          name="telefone"
          value={funcionario.telefone}
          onChange={handleChange}
          placeholder="Telefone"
          required
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="text"
          name="endereco"
          value={funcionario.endereco}
          onChange={handleChange}
          placeholder="Endereço"
          required
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="text"
          name="numero"
          value={funcionario.numero}
          onChange={handleChange}
          placeholder="Número"
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="text"
          name="complemento"
          value={funcionario.complemento}
          onChange={handleChange}
          placeholder="Complemento"
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="text"
          name="bairro"
          value={funcionario.bairro}
          onChange={handleChange}
          placeholder="Bairro"
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <input
          type="text"
          name="cep"
          value={funcionario.cep}
          onChange={handleChange}
          placeholder="CEP"
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
          <input
            type="text"
            value={funcionario.cidadeNome}
            disabled
            style={{
              flex: 1,
              padding: '10px',
              border: '1px solid #ccc',
              borderRadius: '4px',
              fontSize: '1rem',
              backgroundColor: '#f2f2f2',
              color: '#555',
            }}
          />
          <button
            type="button"
            onClick={handleOpenCidadeModal}
            style={{
              padding: '10px',
              backgroundColor: '#007BFF',
              color: '#fff',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer',
              fontSize: '1rem',
            }}
          >
            🔍
          </button>
        </div>
        <input
          type="date"
          name="dataAdmissao"
          value={funcionario.dataAdmissao}
          onChange={handleChange}
          placeholder="Data de Admissão"
          required
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
        <label style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
          <span>Ativo:</span>
          <input
            type="checkbox"
            name="ativo"
            checked={funcionario.ativo}
            onChange={handleChange}
            style={{ transform: 'scale(1.2)' }}
          />
        </label>
        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
          <button
            type="button"
            onClick={handleCancel}
            style={{
              padding: '10px',
              backgroundColor: '#6c757d',
              color: '#fff',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer',
              fontSize: '1rem',
            }}
          >
            Cancelar
          </button>
          <button
            type="submit"
            style={{
              padding: '10px',
              backgroundColor: '#007BFF',
              color: '#fff',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer',
              fontSize: '1rem',
            }}
          >
            Salvar
          </button>
        </div>
      </form>

      {/* Modal de seleção de cidades */}
      {isCidadeModalOpen && (
        <CidadeModal
          onClose={handleCloseCidadeModal}
          onCidadeSelecionada={handleCidadeSelecionada}
        />
      )}
    </div>
  );
};

export default FuncionarioForm;