import React, { useState, useEffect } from 'react';
import CidadeFormModal from './CidadeFormModal'; // Modal de cadastro de cidade

const CidadeModal = ({ onClose, onCidadeSelecionada }) => {
  const [cidades, setCidades] = useState([]);
  const [isCadastroModalOpen, setIsCadastroModalOpen] = useState(false);

  useEffect(() => {
    fetch('http://localhost:8080/cidades')
      .then((response) => response.json())
      .then((data) => setCidades(data))
      .catch((error) => console.error('Erro ao buscar cidades:', error));
  }, []);

  const handleOpenCadastroModal = () => {
    setIsCadastroModalOpen(true);
  };

  const handleCloseCadastroModal = () => {
    setIsCadastroModalOpen(false);
    fetch('http://localhost:8080/cidades') // Atualizar lista de cidades após cadastro
      .then((response) => response.json())
      .then((data) => setCidades(data))
      .catch((error) => console.error('Erro ao atualizar cidades:', error));
  };

  return (
    <>
      <div
        style={{
          position: 'fixed',
          top: '0',
          left: '0',
          width: '100%',
          height: '100%',
          backgroundColor: 'rgba(0, 0, 0, 0.5)',
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          zIndex: '1000',
        }}
      >
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
          <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Selecione uma Cidade</h2>
          <ul style={{ listStyle: 'none', padding: '0', marginBottom: '20px' }}>
            {cidades.map((cidade) => (
              <li
                key={cidade.id}
                style={{
                  padding: '10px',
                  borderBottom: '1px solid #ddd',
                  cursor: 'pointer',
                }}
                onClick={() => onCidadeSelecionada(cidade)}
              >
                {cidade.nome}
              </li>
            ))}
          </ul>
          <button
            onClick={handleOpenCadastroModal}
            style={{
              display: 'block',
              margin: '0 auto',
              padding: '10px 20px',
              backgroundColor: '#28a745',
              color: '#fff',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer',
            }}
          >
            Cadastrar Nova Cidade
          </button>
          <button
            onClick={onClose}
            style={{
              display: 'block',
              margin: '10px auto 0',
              padding: '10px 20px',
              backgroundColor: '#6c757d',
              color: '#fff',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer',
            }}
          >
            Fechar
          </button>
        </div>
      </div>

      {/* Modal de cadastro de cidade */}
      {isCadastroModalOpen && (
        <div
          style={{
            position: 'fixed',
            top: '0',
            left: '0',
            width: '100%',
            height: '100%',
            backgroundColor: 'rgba(0, 0, 0, 0.5)',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            zIndex: '1100', // Nível de sobreposição maior que o modal de listagem
          }}
        >
          <CidadeFormModal onClose={handleCloseCadastroModal} />
        </div>
      )}
    </>
  );
};

export default CidadeModal;