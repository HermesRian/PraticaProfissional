import React, { useState, useEffect } from 'react';
import PaisFormModal from './PaisFormModal'; // Modal de cadastro de país

const PaisModal = ({ onClose, onPaisSelecionado }) => {
  const [paises, setPaises] = useState([]);
  const [isCadastroModalOpen, setIsCadastroModalOpen] = useState(false);

  useEffect(() => {
    fetch('http://localhost:8080/paises')
      .then((response) => response.json())
      .then((data) => setPaises(data))
      .catch((error) => console.error('Erro ao buscar países:', error));
  }, []);

  const handleOpenCadastroModal = () => {
    setIsCadastroModalOpen(true);
  };

  const handleCloseCadastroModal = () => {
    setIsCadastroModalOpen(false);
    fetch('http://localhost:8080/paises') // Atualizar lista de países após cadastro
      .then((response) => response.json())
      .then((data) => setPaises(data))
      .catch((error) => console.error('Erro ao atualizar países:', error));
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
          <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Selecione um País</h2>
          <ul style={{ listStyle: 'none', padding: '0', marginBottom: '20px' }}>
            {paises.map((pais) => (
              <li
                key={pais.id}
                style={{
                  padding: '10px',
                  borderBottom: '1px solid #ddd',
                  cursor: 'pointer',
                }}
                onClick={() => onPaisSelecionado(pais)}
              >
                {pais.nome}
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
            Cadastrar Novo País
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

      {/* Modal de cadastro de país */}
      {isCadastroModalOpen && (
        <div
          style={{
            position: 'fixed',
            top: '0',
            left: '0',
            width: '100%',
            height: '100%',
          //  backgroundColor: 'rgba(0, 0, 0, 0.5)',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            zIndex: '1100', // Nível de sobreposição maior que o modal de listagem
          }}
        >
          <PaisFormModal onClose={handleCloseCadastroModal} />
        </div>
      )}
    </>
  );
};

export default PaisModal;