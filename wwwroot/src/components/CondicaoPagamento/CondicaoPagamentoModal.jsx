import React, { useState, useEffect } from 'react';

const CondicaoPagamentoModal = ({ onClose, onCondicaoSelecionada }) => {
  const [condicoesPagamento, setCondicoesPagamento] = useState([]);
  const [filtro, setFiltro] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/condicoes-pagamento')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Erro ao buscar condições de pagamento');
        }
        return response.json();
      })
      .then((data) => {
        setCondicoesPagamento(data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, []);

  const handleFiltroChange = (e) => {
    setFiltro(e.target.value.toLowerCase());
  };

  const condicoesFiltradas = condicoesPagamento.filter((condicao) => 
    condicao.descricao.toLowerCase().includes(filtro)
  );

  return (
    <div 
      style={{
        position: 'fixed',
        top: 0,
        left: 0,
        width: '100%',
        height: '100%',
        backgroundColor: 'rgba(0, 0, 0, 0.5)',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        zIndex: 1000,
      }}
    >
      <div 
        style={{
          backgroundColor: 'white',
          borderRadius: '8px',
          padding: '20px',
          width: '80%',
          maxWidth: '700px',
          maxHeight: '80vh',
          overflowY: 'auto',
          boxShadow: '0 0 10px rgba(0, 0, 0, 0.2)',
        }}
      >
        <h2 style={{ marginTop: 0, fontSize: '1.3rem' }}>Selecionar Condição de Pagamento</h2>
        
        <div style={{ marginBottom: '15px' }}>
          <input
            type="text"
            placeholder="Filtrar por descrição..."
            value={filtro}
            onChange={handleFiltroChange}
            style={{
              width: '100%',
              padding: '8px',
              borderRadius: '4px',
              border: '1px solid #ccc',
            }}
          />
        </div>

        {loading ? (
          <p>Carregando condições de pagamento...</p>
        ) : error ? (
          <p style={{ color: 'red' }}>{error}</p>
        ) : (
          <table 
            style={{
              width: '100%',
              borderCollapse: 'collapse',
              fontSize: '0.9rem',
            }}
          >
            <thead>
              <tr style={{ backgroundColor: '#f8f9fa' }}>
                <th style={{ padding: '8px', border: '1px solid #ddd', textAlign: 'left' }}>Descrição</th>
                <th style={{ padding: '8px', border: '1px solid #ddd', textAlign: 'center' }}>Dias</th>
                <th style={{ padding: '8px', border: '1px solid #ddd', textAlign: 'center' }}>Parcelas</th>
                <th style={{ padding: '8px', border: '1px solid #ddd', textAlign: 'center' }}>Ativo</th>
                <th style={{ padding: '8px', border: '1px solid #ddd', textAlign: 'center' }}>Ação</th>
              </tr>
            </thead>
            <tbody>
              {condicoesFiltradas.length > 0 ? (
                condicoesFiltradas.map((condicao) => (
                  <tr key={condicao.id} style={{ borderBottom: '1px solid #ddd' }}>
                    <td style={{ padding: '8px', border: '1px solid #ddd' }}>{condicao.descricao}</td>
                    <td style={{ padding: '8px', border: '1px solid #ddd', textAlign: 'center' }}>{condicao.dias}</td>
                    <td style={{ padding: '8px', border: '1px solid #ddd', textAlign: 'center' }}>
                      {condicao.parcelas?.length || 0}
                    </td>
                    <td style={{ padding: '8px', border: '1px solid #ddd', textAlign: 'center' }}>
                      {condicao.ativo ? 'Sim' : 'Não'}
                    </td>
                    <td style={{ padding: '8px', border: '1px solid #ddd', textAlign: 'center' }}>
                      <button
                        onClick={() => onCondicaoSelecionada(condicao)}
                        style={{
                          backgroundColor: '#007bff',
                          color: 'white',
                          border: 'none',
                          borderRadius: '4px',
                          padding: '5px 8px',
                          cursor: 'pointer',
                          fontSize: '0.85rem',
                        }}
                      >
                        Selecionar
                      </button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="5" style={{ padding: '8px', textAlign: 'center' }}>
                    Nenhuma condição de pagamento encontrada
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        )}

        <div style={{ marginTop: '15px', textAlign: 'right' }}>
          <button
            onClick={onClose}
            style={{
              backgroundColor: '#6c757d',
              color: 'white',
              border: 'none',
              borderRadius: '4px',
              padding: '8px 16px',
              cursor: 'pointer',
            }}
          >
            Fechar
          </button>
        </div>
      </div>
    </div>
  );
};

export default CondicaoPagamentoModal;
