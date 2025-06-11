import React, { useState } from 'react';
import EstadoModal from '../Estado/EstadoModal'; // Modal de seleção de estados

const CidadeFormModal = ({ onClose }) => {
  const [cidade, setCidade] = useState({
    nome: '',
    codigoIbge: '',
    estadoId: '',
    estadoNome: 'Selecione um Estado', // Nome do estado selecionado
  });

  const [isEstadoModalOpen, setIsEstadoModalOpen] = useState(false); // Controle do modal de seleção de estados

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCidade({ ...cidade, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/cidades', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(cidade),
    })
      .then(() => {
        onClose(); // Fechar o modal após o cadastro
      })
      .catch((error) => console.error('Erro ao cadastrar cidade:', error));
  };

  const handleOpenEstadoModal = () => {
    setIsEstadoModalOpen(true);
  };

  const handleCloseEstadoModal = () => {
    setIsEstadoModalOpen(false);
  };

  const handleEstadoSelecionado = (estado) => {
    setCidade({ ...cidade, estadoId: estado.id, estadoNome: estado.nome });
    setIsEstadoModalOpen(false);
  };

  return (
    <div
      style={{
        backgroundColor: '#fff',
        padding: '20px',
        borderRadius: '8px',
        boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
        width: '400px',
        maxHeight: '80%',
        overflowY: 'auto',
      }}
    >
      <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Cadastrar Nova Cidade</h2>
      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
        <input
          type="text"
          name="nome"
          value={cidade.nome}
          onChange={handleChange}
          placeholder="Nome da Cidade"
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
          name="codigoIbge"
          value={cidade.codigoIbge}
          onChange={handleChange}
          placeholder="Código IBGE"
          required
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
            value={cidade.estadoNome}
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
            onClick={handleOpenEstadoModal}
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
        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
          <button
            type="button"
            onClick={onClose}
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
            Fechar
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

      {/* Modal de seleção de estados */}
      {isEstadoModalOpen && (
        <EstadoModal
          onClose={handleCloseEstadoModal}
          onEstadoSelecionado={handleEstadoSelecionado}
        />
      )}
    </div>
  );
};

export default CidadeFormModal;