import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import CidadeModal from '../Cidade/CidadeModal';
import CondicaoPagamentoModal from '../CondicaoPagamento/CondicaoPagamentoModal';

// Importações do Material-UI
import {
  Box,
  Typography,
  TextField,
  Button,
  Grid,
  Switch,
  FormControlLabel,
  FormControl,
  FormLabel,
  RadioGroup,
  Radio,
  Select,
  MenuItem,
  InputLabel,
  Paper,
  Divider,
  Alert,
  IconButton
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';

// Funções auxiliares para conversão de valores
const converterEstadoCivilParaNumero = (estadoCivil) => {
  switch (estadoCivil) {
    case 'SOLTEIRO': return 0;
    case 'CASADO': return 1;
    case 'DIVORCIADO': return 2;
    case 'VIUVO': return 3;
    case 'UNIAO_ESTAVEL': return 4;
    case 'SEPARADO': return 5;
    case 'OUTRO': return 6;
    default: return null; // Quando não há valor selecionado
  }
};

const converterNumeroParaEstadoCivil = (numero) => {
  if (numero === null || numero === undefined) return '';
  
  switch (Number(numero)) {
    case 0: return 'SOLTEIRO';
    case 1: return 'CASADO';
    case 2: return 'DIVORCIADO';
    case 3: return 'VIUVO';
    case 4: return 'UNIAO_ESTAVEL';
    case 5: return 'SEPARADO';
    case 6: return 'OUTRO';
    default: return '';
  }
};

const ClienteForm = () => {
  const [cliente, setCliente] = useState({
    nome: '',
    cnpjCpf: '',
    endereco: '',
    numero: '',
    complemento: '',
    bairro: '',
    cep: '',
    cidadeId: '',
    cidadeNome: 'Selecione uma Cidade',
    telefone: '',
    email: '',
    ativo: true,
    // Novos campos
    apelido: '',
    limiteCredito: '',
    limiteCredito2: '',
    nacionalidade: '',
    rgInscricaoEstadual: '',
    dataNascimento: '',
    estadoCivil: '',
    tipo: 'FISICA', // Padrão: pessoa física
    sexo: 'M', // Padrão: masculino
    condicaoPagamentoId: '',
    condicaoPagamentoDescricao: 'Selecione uma Condição de Pagamento',
    observacao: '',
  });
  const [isCidadeModalOpen, setIsCidadeModalOpen] = useState(false); // Controle do modal de seleção de cidades
  const [isCondicaoPagamentoModalOpen, setIsCondicaoPagamentoModalOpen] = useState(false); // Controle do modal de seleção de condições de pagamento
  const [errorMessage, setErrorMessage] = useState(''); // Estado para armazenar a mensagem de erro
  const navigate = useNavigate();
  const { id } = useParams();  useEffect(() => {
    if (id) {
      fetch(`http://localhost:8080/clientes/${id}`)
        .then((response) => response.json())
        .then((data) => {
          // Convertendo os valores numéricos recebidos do backend para strings
          const tipoFormatado = typeof data.tipo === 'number' 
            ? (data.tipo === 0 ? 'FISICA' : 'JURIDICA') 
            : (data.tipo || 'FISICA');
            
          const sexoFormatado = typeof data.sexo === 'number' 
            ? (data.sexo === 0 ? 'M' : 'F') 
            : (data.sexo || 'M');
            
          setCliente({
            ...data,
            cidadeNome: data.cidadeNome || 'Selecione uma Cidade',
            condicaoPagamentoDescricao: data.condicaoPagamentoDescricao || 'Selecione uma Condição de Pagamento',
            tipo: tipoFormatado,
            sexo: sexoFormatado,
          });
        })
        .catch((error) => console.error('Erro ao buscar cliente:', error));
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setCliente({ ...cliente, [name]: type === 'checkbox' ? checked : value });
  };
  const handleSubmit = (e) => {
    e.preventDefault();

    // Validando campos obrigatórios
    if (!cliente.cidadeId) {
      setErrorMessage('Por favor, selecione uma cidade.');
      return;
    }    // Formatando os dados para corresponder ao modelo esperado pelo backend
    const clienteFormatado = {
      ...cliente,
      // Convertendo valores numéricos
      limiteCredito: cliente.limiteCredito ? parseFloat(cliente.limiteCredito) : null,
      limiteCredito2: cliente.limiteCredito2 ? parseFloat(cliente.limiteCredito2) : null,
      // Convertendo tipo para Integer (conforme backend)
      tipo: cliente.tipo === 'FISICA' ? 0 : 1, // 0 = FISICA, 1 = JURIDICA
      // Sexo é uma String no backend, então enviamos diretamente
      sexo: cliente.sexo, // 'M' ou 'F'
      // Estado civil é uma String no backend, então enviamos diretamente
      estadoCivil: cliente.estadoCivil,
      // Garantindo que os campos obrigatórios não estejam vazios
      dataNascimento: cliente.dataNascimento || null,
    };

    console.log('Dados enviados:', clienteFormatado);

    const method = id ? 'PUT' : 'POST';
    const url = id ? `http://localhost:8080/clientes/${id}` : 'http://localhost:8080/clientes';

    fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(clienteFormatado),
    })
      .then((response) => {
        if (!response.ok) {
          console.error('Erro na resposta:', response.status, response.statusText);
          
          return response.text().then(text => {
            let error;
            try {
              // Tenta converter a resposta para JSON para extrair a mensagem de erro
              const errorObj = JSON.parse(text);
              error = errorObj.erro || errorObj.message || 'Erro desconhecido ao salvar cliente';
              console.error('Resposta do servidor:', errorObj);
            } catch (e) {
              // Se não for um JSON válido, usa o texto puro
              error = text || 'Erro ao salvar cliente';
              console.error('Resposta do servidor (texto):', text);
            }
            throw new Error(error);
          });
        }
        return response.json();
      })
      .then(() => {
        navigate('/clientes');
      })
      .catch((error) => {
        console.error('Erro capturado:', error);
        setErrorMessage(error.message);
      });
  };

  const handleCancel = () => {
    navigate('/clientes');
  };

  const handleOpenCidadeModal = () => {
    setIsCidadeModalOpen(true);
  };

  const handleCloseCidadeModal = () => {
    setIsCidadeModalOpen(false);
  };
  const handleCidadeSelecionada = (cidade) => {
    setCliente({
      ...cliente,
      cidadeId: cidade.id,
      cidadeNome: cidade.nome,
      cidadeEstado: cidade.estadoNome,
      cidadeEstadoPais: cidade.estadoPaisNome,
    });
    setIsCidadeModalOpen(false);
  };
  
  const handleOpenCondicaoPagamentoModal = () => {
    setIsCondicaoPagamentoModalOpen(true);
  };

  const handleCloseCondicaoPagamentoModal = () => {
    setIsCondicaoPagamentoModalOpen(false);
  };

  const handleCondicaoPagamentoSelecionada = (condicao) => {
    setCliente({
      ...cliente,
      condicaoPagamentoId: condicao.id,
      condicaoPagamentoDescricao: condicao.descricao,
    });
    setIsCondicaoPagamentoModalOpen(false);
  };
  return (
    <Box sx={{ padding: 3, bgcolor: '#f8f9fa' }}>
      <Typography 
        variant="h5" 
        component="h1" 
        align="center" 
        gutterBottom 
        sx={{ mb: 3, color: '#333' }}
      >
        {id ? 'Editar Cliente' : 'Cadastrar Novo Cliente'}
      </Typography>
      <Paper 
        component="form"
        onSubmit={handleSubmit}
        elevation={1}
        sx={{
          maxWidth: 750,
          mx: 'auto',
          p: 3,
          borderRadius: 2
        }}
      >        {/* Seção 1: Informações Gerais */}
        <Box sx={{ mb: 2 }}>
          <Typography
            variant="subtitle1"
            component="h2"
            sx={{
              fontWeight: 'bold',
              mb: 1,
              color: '#444',
              borderBottom: '2px solid #1976d2',
              pb: 0.5,
              width: 'fit-content'
            }}
          >
            Informações Gerais
          </Typography>
          
          <Grid container spacing={2} alignItems="center">
            <Grid item xs={9}>
              <TextField
                fullWidth
                required
                size="small"
                label="Nome"
                name="nome"
                value={cliente.nome}
                onChange={handleChange}
                placeholder="Nome completo"
                variant="outlined"
              />
            </Grid>
            <Grid item xs={3} sx={{ display: 'flex', justifyContent: 'center' }}>
              <FormControlLabel
                control={
                  <Switch
                    checked={cliente.ativo}
                    onChange={handleChange}
                    name="ativo"
                    color="primary"
                  />
                }
                label="Ativo"
              />
            </Grid>
          </Grid>
        </Box>        {/* Seção 2: Endereço */}
        <Box sx={{ mb: 2 }}>
          <Typography
            variant="subtitle1"
            component="h2"
            sx={{
              fontWeight: 'bold',
              mb: 1,
              color: '#444',
              borderBottom: '2px solid #1976d2',
              pb: 0.5,
              width: 'fit-content'
            }}
          >
            Endereço
          </Typography>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                fullWidth
                required
                size="small"
                label="Endereço"
                name="endereco"
                value={cliente.endereco}
                onChange={handleChange}
                placeholder="Rua, Avenida, etc."
                variant="outlined"
              />
            </Grid>            <Grid item xs={2}>
              <TextField
                fullWidth
                required
                size="small"
                label="Número"
                name="numero"
                value={cliente.numero}
                onChange={handleChange}
                placeholder="Nº"
                variant="outlined"
              />
            </Grid>
            <Grid item xs={5}>
              <TextField
                fullWidth
                size="small"
                label="Complemento"
                name="complemento"
                value={cliente.complemento}
                onChange={handleChange}
                placeholder="Apto, Bloco, Casa"
                variant="outlined"
              />
            </Grid>
            <Grid item xs={5}>
              <TextField
                fullWidth
                required
                size="small"
                label="Bairro"
                name="bairro"
                value={cliente.bairro}
                onChange={handleChange}
                placeholder="Bairro"
                variant="outlined"
              />
            </Grid>            <Grid item xs={3}>
              <TextField
                fullWidth
                required
                size="small"
                label="CEP"
                name="cep"
                value={cliente.cep}
                onChange={handleChange}
                placeholder="00000-000"
                variant="outlined"
              />
            </Grid>
            <Grid item xs={9}>
              <FormControl fullWidth variant="outlined" size="small">
                <InputLabel>Cidade *</InputLabel>
                <TextField
                  value={cliente.cidadeNome}
                  disabled
                  fullWidth
                  size="small"
                  sx={{
                    backgroundColor: '#f8f9fa'
                  }}
                  InputProps={{
                    endAdornment: (
                      <IconButton 
                        onClick={handleOpenCidadeModal}
                        size="small"
                        color="primary"
                      >
                        <SearchIcon />
                      </IconButton>
                    )
                  }}
                />
              </FormControl>
            </Grid>
          </Grid>
        </Box>        {/* Seção 3: Contato */}
        <Box sx={{ mb: 2 }}>
          <Typography
            variant="subtitle1"
            component="h2"
            sx={{
              fontWeight: 'bold',
              mb: 1,
              color: '#444',
              borderBottom: '2px solid #1976d2',
              pb: 0.5,
              width: 'fit-content'
            }}
          >
            Contato
          </Typography>
          <Grid container spacing={2}>
            <Grid item xs={5}>
              <TextField
                fullWidth
                required
                size="small"
                label="Telefone"
                name="telefone"
                value={cliente.telefone}
                onChange={handleChange}
                placeholder="(00) 00000-0000"
                variant="outlined"
              />
            </Grid>
            <Grid item xs={7}>
              <TextField
                fullWidth
                required
                type="email"
                size="small"
                label="Email"
                name="email"
                value={cliente.email}
                onChange={handleChange}
                placeholder="exemplo@email.com"
                variant="outlined"
              />
            </Grid>
          </Grid>
        </Box>

        {/* Seção 4: Documento */}
        <fieldset style={{ border: 'none', padding: '0', marginBottom: '0' }}>
          <legend style={{ 
            fontSize: '1.0rem', 
            fontWeight: 'bold', 
            marginBottom: '6px', 
            color: '#444',
            borderBottom: '2px solid #007BFF',
            paddingBottom: '4px',
            width: 'fit-content'
          }}>
            Documento
          </legend>
          <div style={{ display: 'grid', gridTemplateColumns: 'repeat(12, 1fr)', gap: '10px' }}>
            <div style={{ gridColumn: 'span 4' }}> {/* Tipo de Pessoa */}
              <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                Tipo de Pessoa <span style={{ color: 'red' }}>*</span>
              </label>
              <div style={{ display: 'flex', gap: '15px', marginTop: '4px' }}>
                <label style={{ display: 'flex', alignItems: 'center', fontSize: '0.85rem' }}>
                  <input
                    type="radio"
                    name="tipo"
                    value="FISICA"
                    checked={cliente.tipo === 'FISICA'}
                    onChange={handleChange}
                    style={{ marginRight: '5px' }}
                  />
                  Pessoa Física
                </label>
                <label style={{ display: 'flex', alignItems: 'center', fontSize: '0.85rem' }}>
                  <input
                    type="radio"
                    name="tipo"
                    value="JURIDICA"
                    checked={cliente.tipo === 'JURIDICA'}
                    onChange={handleChange}
                    style={{ marginRight: '5px' }}
                  />
                  Pessoa Jurídica
                </label>
              </div>
            </div>
            <div style={{ gridColumn: 'span 4' }}> {/* CNPJ/CPF */}
              <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                {cliente.tipo === 'FISICA' ? 'CPF' : 'CNPJ'} <span style={{ color: 'red' }}>*</span>
              </label>
              <input
                type="text"
                name="cnpjCpf"
                value={cliente.cnpjCpf}
                onChange={handleChange}
                placeholder={cliente.tipo === 'FISICA' ? "000.000.000-00" : "00.000.000/0000-00"}
                required
                style={{
                  width: '100%',
                  padding: '7px 9px',
                  borderRadius: '4px',
                  border: '1px solid #ccc',
                  fontSize: '0.85rem',
                }}
              />
            </div>
            <div style={{ gridColumn: 'span 4' }}> {/* RG/Inscrição Estadual */}
              <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                {cliente.tipo === 'FISICA' ? 'RG' : 'Inscrição Estadual'}
              </label>
              <input
                type="text"
                name="rgInscricaoEstadual"
                value={cliente.rgInscricaoEstadual}
                onChange={handleChange}
                placeholder={cliente.tipo === 'FISICA' ? "00.000.000-0" : "Inscrição Estadual"}
                style={{
                  width: '100%',
                  padding: '7px 9px',
                  borderRadius: '4px',
                  border: '1px solid #ccc',
                  fontSize: '0.85rem',
                }}
              />
            </div>
          </div>
        </fieldset>

        {/* Seção 5: Informações Adicionais */}
        <fieldset style={{ border: 'none', padding: '0', marginBottom: '0' }}>
          <legend style={{ 
            fontSize: '1.0rem', 
            fontWeight: 'bold', 
            marginBottom: '6px', 
            color: '#444',
            borderBottom: '2px solid #007BFF',
            paddingBottom: '4px',
            width: 'fit-content'
          }}>
            Informações Adicionais
          </legend>
          <div style={{ display: 'grid', gridTemplateColumns: 'repeat(12, 1fr)', gap: '10px' }}>
            <div style={{ gridColumn: 'span 6' }}> {/* Apelido */}
              <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                {cliente.tipo === 'FISICA' ? 'Apelido' : 'Nome Fantasia'}
              </label>
              <input
                type="text"
                name="apelido"
                value={cliente.apelido}
                onChange={handleChange}
                placeholder={cliente.tipo === 'FISICA' ? "Apelido" : "Nome Fantasia"}
                style={{
                  width: '100%',
                  padding: '7px 9px',
                  borderRadius: '4px',
                  border: '1px solid #ccc',
                  fontSize: '0.85rem',
                }}
              />
            </div>
            <div style={{ gridColumn: 'span 6' }}> {/* Nacionalidade */}
              <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                Nacionalidade
              </label>
              <input
                type="text"
                name="nacionalidade"
                value={cliente.nacionalidade}
                onChange={handleChange}
                placeholder="Nacionalidade"
                style={{
                  width: '100%',
                  padding: '7px 9px',
                  borderRadius: '4px',
                  border: '1px solid #ccc',
                  fontSize: '0.85rem',
                }}
              />
            </div>

            {cliente.tipo === 'FISICA' && (
              <>
                <div style={{ gridColumn: 'span 3' }}> {/* Data de Nascimento */}
                  <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                    Data de Nascimento
                  </label>                  <input
                    type="date"
                    name="dataNascimento"
                    value={cliente.dataNascimento ? cliente.dataNascimento.split('T')[0] : ''}
                    onChange={handleChange}
                    style={{
                      width: '100%',
                      padding: '7px 9px',
                      borderRadius: '4px',
                      border: '1px solid #ccc',
                      fontSize: '0.85rem',
                    }}
                  />
                </div>
                <div style={{ gridColumn: 'span 3' }}> {/* Estado Civil */}
                  <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                    Estado Civil
                  </label>
                  <select
                    name="estadoCivil"
                    value={cliente.estadoCivil}
                    onChange={handleChange}
                    style={{
                      width: '100%',
                      padding: '7px 9px',
                      borderRadius: '4px',
                      border: '1px solid #ccc',
                      fontSize: '0.85rem',
                      backgroundColor: '#fff',
                    }}
                  >
                    <option value="">Selecione...</option>
                    <option value="SOLTEIRO">Solteiro(a)</option>
                    <option value="CASADO">Casado(a)</option>
                    <option value="DIVORCIADO">Divorciado(a)</option>
                    <option value="VIUVO">Viúvo(a)</option>
                    <option value="UNIAO_ESTAVEL">União Estável</option>
                    <option value="SEPARADO">Separado(a)</option>
                    <option value="OUTRO">Outro</option>
                  </select>
                </div>
                <div style={{ gridColumn: 'span 3' }}> {/* Sexo */}
                  <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                    Sexo
                  </label>
                  <div style={{ display: 'flex', gap: '15px', marginTop: '4px' }}>
                    <label style={{ display: 'flex', alignItems: 'center', fontSize: '0.85rem' }}>
                      <input
                        type="radio"
                        name="sexo"
                        value="M"
                        checked={cliente.sexo === 'M'}
                        onChange={handleChange}
                        style={{ marginRight: '5px' }}
                      />
                      Masculino
                    </label>
                    <label style={{ display: 'flex', alignItems: 'center', fontSize: '0.85rem' }}>
                      <input
                        type="radio"
                        name="sexo"
                        value="F"
                        checked={cliente.sexo === 'F'}
                        onChange={handleChange}
                        style={{ marginRight: '5px' }}
                      />
                      Feminino
                    </label>
                  </div>
                </div>
              </>
            )}
            
            <div style={{ gridColumn: cliente.tipo === 'FISICA' ? 'span 3' : 'span 6' }}> {/* Limite Crédito */}
              <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                Limite de Crédito (R$)
              </label>
              <input
                type="number"
                name="limiteCredito"
                value={cliente.limiteCredito}
                onChange={handleChange}
                placeholder="0,00"
                step="0.01"
                min="0"
                style={{
                  width: '100%',
                  padding: '7px 9px',
                  borderRadius: '4px',
                  border: '1px solid #ccc',
                  fontSize: '0.85rem',
                }}
              />
            </div>
            <div style={{ gridColumn: cliente.tipo === 'FISICA' ? 'span 3' : 'span 6' }}> {/* Limite Crédito 2 */}
              <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                Limite de Crédito 2 (R$)
              </label>
              <input
                type="number"
                name="limiteCredito2"
                value={cliente.limiteCredito2}
                onChange={handleChange}
                placeholder="0,00"
                step="0.01"
                min="0"
                style={{
                  width: '100%',
                  padding: '7px 9px',
                  borderRadius: '4px',
                  border: '1px solid #ccc',
                  fontSize: '0.85rem',
                }}
              />
            </div>
            
            <div style={{ gridColumn: 'span 12' }}> {/* Condição de Pagamento */}
              <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                Condição de Pagamento
              </label>
              <div style={{ display: 'flex', gap: '8px' }}>
                <input
                  type="text"
                  value={cliente.condicaoPagamentoDescricao}
                  readOnly
                  style={{
                    flex: 1,
                    padding: '7px 9px',
                    borderRadius: '4px',
                    border: '1px solid #ccc',
                    backgroundColor: '#f8f9fa',
                    fontSize: '0.85rem',
                    cursor: 'default',
                  }}
                />
                <button
                  type="button"
                  onClick={handleOpenCondicaoPagamentoModal}
                  style={{
                    padding: '7px 12px',
                    backgroundColor: '#6c757d',
                    color: '#fff',
                    border: 'none',
                    borderRadius: '4px',
                    fontSize: '0.85rem',
                    cursor: 'pointer',
                  }}
                >
                  Selecionar
                </button>
              </div>
            </div>
            
            <div style={{ gridColumn: 'span 12' }}> {/* Observações */}
              <label style={{ display: 'block', marginBottom: '3px', fontSize: '0.85rem', color: '#333' }}>
                Observações
              </label>
              <textarea
                name="observacao"
                value={cliente.observacao}
                onChange={handleChange}
                placeholder="Informações adicionais sobre o cliente"
                rows="2"
                style={{
                  width: '100%',
                  padding: '7px 9px',
                  borderRadius: '4px',
                  border: '1px solid #ccc',
                  fontSize: '0.85rem',
                  resize: 'vertical',
                }}
              />
            </div>          </div>
        </fieldset>        {/* Mensagem de erro */}
        {errorMessage && (
          <Alert 
            severity="error" 
            sx={{ mb: 2, mt: 2 }}
          >
            {errorMessage}
          </Alert>
        )}

        {/* Botões */}
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'flex-end',
            gap: 1,
            mt: 2,
            pt: 2,
            borderTop: '1px solid #eee',
          }}
        >
          <Button
            variant="outlined"
            onClick={handleCancel}
            color="inherit"
          >
            Cancelar
          </Button>
          <Button
            variant="contained"
            type="submit"
            color="primary"
          >
            Salvar
          </Button>
        </Box>
      </Paper>

      {/* Modal de seleção de cidades */}
      {isCidadeModalOpen && (
        <CidadeModal
          onClose={handleCloseCidadeModal}
          onCidadeSelecionada={handleCidadeSelecionada}
        />
      )}
      
      {/* Modal de seleção de condições de pagamento */}
      {isCondicaoPagamentoModalOpen && (
        <CondicaoPagamentoModal
          onClose={handleCloseCondicaoPagamentoModal}
          onCondicaoSelecionada={handleCondicaoPagamentoSelecionada}
        />
      )}
    </Box>
  );
};

export default ClienteForm;