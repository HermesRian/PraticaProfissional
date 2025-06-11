import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

const ClienteForm = () => {
  const [cliente, setCliente] = useState({
    nome: '',
    cnpjCpf: '',
    endereco: '',
    telefone: '',
    email: ''
  });

  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      fetch(`http://localhost:8080/clientes/${id}`)
        .then((response) => response.json())
        .then((data) => setCliente(data))
        .catch((error) => console.error('Erro ao buscar cliente:', error));
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCliente({ ...cliente, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const method = id ? 'PUT' : 'POST';
    const url = id ? `http://localhost:8080/clientes/${id}` : 'http://localhost:8080/clientes';

    fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(cliente),
    })
      .then((response) => response.json())
      .then(() => {
        setCliente({ nome: '', cnpjCpf: '', endereco: '', telefone: '', email: '' });
        navigate('/clientes');
      })
      .catch((error) => console.error('Erro ao salvar cliente:', error));
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        name="nome"
        value={cliente.nome}
        onChange={handleChange}
        placeholder="Nome"
        required
      />
      <input
        type="text"
        name="cnpjCpf"
        value={cliente.cnpjCpf}
        onChange={handleChange}
        placeholder="CNPJ/CPF"
        required
      />
      <input
        type="text"
        name="endereco"
        value={cliente.endereco}
        onChange={handleChange}
        placeholder="Endereço"
        required
      />
      <input
        type="text"
        name="telefone"
        value={cliente.telefone}
        onChange={handleChange}
        placeholder="Telefone"
        required
      />
      <input
        type="email"
        name="email"
        value={cliente.email}
        onChange={handleChange}
        placeholder="Email"
        required
      />
      <button type="submit">Salvar</button>
    </form>
  );
};

export default ClienteForm;