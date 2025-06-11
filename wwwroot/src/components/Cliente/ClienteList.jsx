import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';

const ClienteList = () => {
  const [clientes, setClientes] = useState([]);
  const [cidades, setCidades] = useState([]);
  const [clienteSelecionado, setClienteSelecionado] = useState(null); // Cliente selecionado para visualização
  const [isPopupOpen, setIsPopupOpen] = useState(false); // Controle do popup
  const [sortConfig, setSortConfig] = useState({ key: null, direction: 'asc' });
  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://localhost:8080/clientes')
      .then((response) => response.json())
      .then((data) => setClientes(data))
      .catch((error) => console.error('Erro ao buscar clientes:', error));

    fetch('http://localhost:8080/cidades')
      .then((response) => response.json())
      .then((data) => setCidades(data))
      .catch((error) => console.error('Erro ao buscar cidades:', error));
  }, []);

  const handleDelete = (id) => {
    if (window.confirm('Tem certeza que deseja excluir este cliente?')) {
      fetch(`http://localhost:8080/clientes/${id}`, {
        method: 'DELETE',
      })
        .then(() => {
          setClientes(clientes.filter((cliente) => cliente.id !== id));
        })
        .catch((error) => console.error('Erro ao excluir cliente:', error));
    }
  };

  const getCidadeNome = (cidadeId) => {
    const cidade = cidades.find((c) => c.id === cidadeId);
    return cidade ? cidade.nome : 'Desconhecida';
  };

  const handleSort = (key) => {
    let direction = 'asc';
    if (sortConfig.key === key && sortConfig.direction === 'asc') {
      direction = 'desc';
    }
    setSortConfig({ key, direction });

    const sortedClientes = [...clientes].sort((a, b) => {
      let aValue = a[key];
      let bValue = b[key];

      // Para a coluna "Cidade", usamos o nome da cidade
      if (key === 'cidade') {
        aValue = getCidadeNome(a.cidadeId);
        bValue = getCidadeNome(b.cidadeId);
      }

      // Para a coluna "Ativo", convertemos para valores booleanos
      if (key === 'ativo') {
        aValue = a.ativo ? 1 : 0;
        bValue = b.ativo ? 1 : 0;
      }

      if (aValue < bValue) {
        return direction === 'asc' ? -1 : 1;
      }
      if (aValue > bValue) {
        return direction === 'asc' ? 1 : -1;
      }
      return 0;
    });
    setClientes(sortedClientes);
  };

  const handleView = (cliente) => {
    setClienteSelecionado(cliente);
    setIsPopupOpen(true);
  };

  const handleClosePopup = () => {
    setClienteSelecionado(null);
    setIsPopupOpen(false);
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1 style={{ textAlign: 'center', marginBottom: '20px' }}>Lista de Clientes</h1>
      <div style={{ display: 'flex', justifyContent: 'flex-end', marginBottom: '20px' }}>
        <Link
          to="/clientes/cadastrar"
          style={{
            padding: '10px 20px',
            backgroundColor: '#007BFF',
            color: '#fff',
            textDecoration: 'none',
            borderRadius: '5px',
          }}
        >
          Cadastrar Novo Cliente
        </Link>
      </div>
      <table
        style={{
          width: '100%',
          borderCollapse: 'collapse',
          marginBottom: '20px',
        }}
      >
        <thead>
          <tr style={{ backgroundColor: '#f2f2f2', textAlign: 'left' }}>
            <th
              style={{ padding: '10px', border: '1px solid #ddd', cursor: 'pointer' }}
              onClick={() => handleSort('nome')}
            >
              Nome {sortConfig.key === 'nome' ? (sortConfig.direction === 'asc' ? '↑' : '↓') : ''}
            </th>
            <th
              style={{ padding: '10px', border: '1px solid #ddd', cursor: 'pointer' }}
              onClick={() => handleSort('cnpjCpf')}
            >
              CNPJ/CPF {sortConfig.key === 'cnpjCpf' ? (sortConfig.direction === 'asc' ? '↑' : '↓') : ''}
            </th>
            <th
              style={{ padding: '10px', border: '1px solid #ddd', cursor: 'pointer' }}
              onClick={() => handleSort('cidade')}
            >
              Cidade {sortConfig.key === 'cidade' ? (sortConfig.direction === 'asc' ? '↑' : '↓') : ''}
            </th>
            <th
              style={{ padding: '10px', border: '1px solid #ddd', cursor: 'pointer' }}
              onClick={() => handleSort('ativo')}
            >
              Ativo {sortConfig.key === 'ativo' ? (sortConfig.direction === 'asc' ? '↑' : '↓') : ''}
            </th>
            <th style={{ padding: '10px', border: '1px solid #ddd' }}>Ações</th>
          </tr>
        </thead>
        <tbody>
          {clientes.map((cliente) => (
            <tr key={cliente.id} style={{ borderBottom: '1px solid #ddd' }}>
              <td style={{ padding: '10px', border: '1px solid #ddd' }}>{cliente.nome}</td>
              <td style={{ padding: '10px', border: '1px solid #ddd' }}>{cliente.cnpjCpf}</td>
              <td style={{ padding: '10px', border: '1px solid #ddd' }}>{getCidadeNome(cliente.cidadeId)}</td>
              <td style={{ padding: '10px', border: '1px solid #ddd' }}>
                {cliente.ativo ? 'Sim' : 'Não'}
              </td>
              <td style={{ padding: '10px', border: '1px solid #ddd', display: 'flex', gap: '10px' }}>
                <button
                  onClick={() => handleView(cliente)}
                  style={{
                    padding: '5px 10px',
                    backgroundColor: '#17a2b8',
                    color: '#fff',
                    border: 'none',
                    borderRadius: '3px',
                    cursor: 'pointer',
                  }}
                >
                  Visualizar
                </button>
                <Link
                  to={`/clientes/editar/${cliente.id}`}
                  style={{
                    padding: '5px 10px',
                    backgroundColor: '#28a745',
                    color: '#fff',
                    textDecoration: 'none',
                    borderRadius: '3px',
                  }}
                >
                  Editar
                </Link>
                <button
                  onClick={() => handleDelete(cliente.id)}
                  style={{
                    padding: '5px 10px',
                    backgroundColor: '#dc3545',
                    color: '#fff',
                    border: 'none',
                    borderRadius: '3px',
                    cursor: 'pointer',
                  }}
                >
                  Excluir
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Modal de visualização de cliente */}
      {isPopupOpen && clienteSelecionado && (
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
          onClick={handleClosePopup}
        >
          <div
            style={{
              backgroundColor: '#fff',
              padding: '20px',
              borderRadius: '8px',
              boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
              width: '500px',
              maxHeight: '80%',
              overflowY: 'auto',
              position: 'relative',
            }}
            onClick={(e) => e.stopPropagation()}
          >
            <button
              onClick={handleClosePopup}
              style={{
                position: 'absolute',
                top: '10px',
                right: '10px',
                backgroundColor: 'transparent',
                border: 'none',
                fontSize: '1.5rem',
                cursor: 'pointer',
                color: '#333',
              }}
            >
              &times;
            </button>

            <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Detalhes do Cliente</h2>
            <table
              style={{
                width: '100%',
                borderCollapse: 'collapse',
                marginBottom: '20px',
              }}
            >
              <tbody>
                <tr>
                  <td
                    style={{
                      padding: '10px',
                      border: '1px solid #ddd',
                      fontWeight: 'bold',
                      backgroundColor: '#f2f2f2', // Fundo cinza para a coluna principal
                    }}
                  >
                    Nome
                  </td>
                  <td style={{ padding: '10px', border: '1px solid #ddd' }}>{clienteSelecionado.nome}</td>
                </tr>
                <tr>
                  <td
                    style={{
                      padding: '10px',
                      border: '1px solid #ddd',
                      fontWeight: 'bold',
                      backgroundColor: '#f2f2f2',
                    }}
                  >
                    CNPJ/CPF
                  </td>
                  <td style={{ padding: '10px', border: '1px solid #ddd' }}>{clienteSelecionado.cnpjCpf}</td>
                </tr>
                <tr>
                  <td
                    style={{
                      padding: '10px',
                      border: '1px solid #ddd',
                      fontWeight: 'bold',
                      backgroundColor: '#f2f2f2',
                    }}
                  >
                    Email
                  </td>
                  <td style={{ padding: '10px', border: '1px solid #ddd' }}>{clienteSelecionado.email}</td>
                </tr>
                <tr>
                  <td
                    style={{
                      padding: '10px',
                      border: '1px solid #ddd',
                      fontWeight: 'bold',
                      backgroundColor: '#f2f2f2',
                    }}
                  >
                    Telefone
                  </td>
                  <td style={{ padding: '10px', border: '1px solid #ddd' }}>{clienteSelecionado.telefone}</td>
                </tr>
                <tr>
                  <td
                    style={{
                      padding: '10px',
                      border: '1px solid #ddd',
                      fontWeight: 'bold',
                      backgroundColor: '#f2f2f2',
                    }}
                  >
                    Endereço
                  </td>
                  <td style={{ padding: '10px', border: '1px solid #ddd' }}>{clienteSelecionado.endereco}</td>
                </tr>
                <tr>
                  <td
                    style={{
                      padding: '10px',
                      border: '1px solid #ddd',
                      fontWeight: 'bold',
                      backgroundColor: '#f2f2f2',
                    }}
                  >
                    Bairro
                  </td>
                  <td style={{ padding: '10px', border: '1px solid #ddd' }}>{clienteSelecionado.bairro}</td>
                </tr>
                <tr>
                  <td
                    style={{
                      padding: '10px',
                      border: '1px solid #ddd',
                      fontWeight: 'bold',
                      backgroundColor: '#f2f2f2',
                    }}
                  >
                    CEP
                  </td>
                  <td style={{ padding: '10px', border: '1px solid #ddd' }}>{clienteSelecionado.cep}</td>
                </tr>
                <tr>
                  <td
                    style={{
                      padding: '10px',
                      border: '1px solid #ddd',
                      fontWeight: 'bold',
                      backgroundColor: '#f2f2f2',
                    }}
                  >
                    Cidade
                  </td>
                  <td style={{ padding: '10px', border: '1px solid #ddd' }}>{getCidadeNome(clienteSelecionado.cidadeId)}</td>
                </tr>
                <tr>
                  <td
                    style={{
                      padding: '10px',
                      border: '1px solid #ddd',
                      fontWeight: 'bold',
                      backgroundColor: '#f2f2f2',
                    }}
                  >
                    Ativo
                  </td>
                  <td style={{ padding: '10px', border: '1px solid #ddd' }}>
                    {clienteSelecionado.ativo ? 'Sim' : 'Não'}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};

export default ClienteList;