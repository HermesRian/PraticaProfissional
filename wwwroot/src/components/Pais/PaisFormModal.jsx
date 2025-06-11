import React, { useState } from 'react';

const PaisFormModal = ({ onClose }) => {
  const [pais, setPais] = useState({
    nome: '',
    sigla: '',
    codigo: '', // Novo campo para o código do país
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPais({ ...pais, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/paises', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(pais),
    })
      .then(() => {
        onClose(); // Fechar o modal após o cadastro
      })
      .catch((error) => console.error('Erro ao cadastrar país:', error));
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
      <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Cadastrar Novo País</h2>
      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
        <input
          type="text"
          name="nome"
          value={pais.nome}
          onChange={handleChange}
          placeholder="Nome do País"
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
          name="sigla"
          value={pais.sigla}
          onChange={handleChange}
          placeholder="Sigla (Ex: BR)"
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
          name="codigo"
          value={pais.codigo}
          onChange={handleChange}
          placeholder="Código do País (Ex: 55)"
          required
          style={{
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            fontSize: '1rem',
          }}
        />
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
    </div>
  );
};

export default PaisFormModal;