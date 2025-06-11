import React, { useState } from 'react';
import PaisModal from '../Pais/PaisModal'; // Modal de seleção de países

const EstadoFormModal = ({ onClose }) => {
  const [estado, setEstado] = useState({
    nome: '',
    uf: '',
    paisId: '',
    paisNome: 'Selecione um País', // Nome do país selecionado
  });

  const [isPaisModalOpen, setIsPaisModalOpen] = useState(false); // Controle do modal de seleção de países

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEstado({ ...estado, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/estados', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(estado),
    })
      .then(() => {
        onClose(); // Fechar o modal após o cadastro
      })
      .catch((error) => console.error('Erro ao cadastrar estado:', error));
  };

  const handleOpenPaisModal = () => {
    setIsPaisModalOpen(true);
  };

  const handleClosePaisModal = () => {
    setIsPaisModalOpen(false);
  };

  const handlePaisSelecionado = (pais) => {
    setEstado({ ...estado, paisId: pais.id, paisNome: pais.nome });
    setIsPaisModalOpen(false);
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
      <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Cadastrar Novo Estado</h2>
      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
        <input
          type="text"
          name="nome"
          value={estado.nome}
          onChange={handleChange}
          placeholder="Nome do Estado"
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
          name="uf"
          value={estado.uf}
          onChange={handleChange}
          placeholder="UF"
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
            value={estado.paisNome}
            disabled
            style={{
              flex: 1,
              padding: '10px',
              border: '1px solid #ccc',
              borderRadius: '4px',
              fontSize: '1rem',
            //  backgroundColor: '#f2f2f2',
              color: '#555',
            }}
          />
          <button
            type="button"
            onClick={handleOpenPaisModal}
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

      {/* Modal de seleção de países */}
      {isPaisModalOpen && (
        <PaisModal
          onClose={handleClosePaisModal}
          onPaisSelecionado={handlePaisSelecionado}
        />
      )}
    </div>
  );
};

export default EstadoFormModal;