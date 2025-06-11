import React, { useState, useEffect, useRef } from 'react';
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
  IconButton,
  InputAdornment,
  Container,
  Stack,
  Tooltip
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

// Componente de formulário de cliente
const ClienteForm = () => {    const [cliente, setCliente] = useState({
    nome: '',
    cnpjCpf: '',
    endereco: '',
    numero: '',
    complemento: '',
    bairro: '',
    cep: '',
    cidadeId: '',
    cidadeNome: '',
    telefone: '',
    email: '',
    ativo: true,
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
    condicaoPagamentoDescricao: '',
    observacao: '',
    dataCadastro: '',
    ultimaModificacao: '',
  });
  const [isCidadeModalOpen, setIsCidadeModalOpen] = useState(false);
  const [isCondicaoPagamentoModalOpen, setIsCondicaoPagamentoModalOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [fieldErrors, setFieldErrors] = useState({});
  const [showRequiredErrors, setShowRequiredErrors] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();
  useEffect(() => {
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
            cidadeNome: data.cidadeNome || '',
            condicaoPagamentoDescricao: data.condicaoPagamentoDescricao || '',
            tipo: tipoFormatado,
            sexo: sexoFormatado,
            dataCadastro: data.dataCadastro || '',
            ultimaModificacao: data.ultimaModificacao || '',
          });
        })
        .catch((error) => console.error('Erro ao buscar cliente:', error));
    }
  }, [id]);  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    
    // Limpa o erro do campo quando o usuário começar a digitar
    if (fieldErrors[name]) {
      setFieldErrors(prev => {
        const newErrors = { ...prev };
        delete newErrors[name];
        return newErrors;
      });
    }
    
    // Ações especiais para diferentes campos
    if (name === 'tipo') {
      // Limpa o CPF/CNPJ e RG/Inscrição Estadual quando muda o tipo de pessoa
      setCliente({ 
        ...cliente, 
        [name]: value,
        cnpjCpf: '', // Limpa o campo para aplicar a máscara correta
        rgInscricaoEstadual: '', // Limpa o campo de RG/IE também
        sexo: value === 'FISICA' ? cliente.sexo || 'M' : '' // Ajusta o sexo conforme o tipo
      });
    } else {
      setCliente({ ...cliente, [name]: type === 'checkbox' ? checked : value });
    }
  };
  // Funções de máscara
  const formatCPF = (value) => {
    const cleanValue = value.replace(/[^0-9]/g, '');
    if (cleanValue.length <= 11) {
      return cleanValue
        .replace(/(\d{3})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d{1,2})/, '$1-$2');
    }
    return cleanValue;
  };

  const formatCNPJ = (value) => {
    const cleanValue = value.replace(/[^0-9]/g, '');
    if (cleanValue.length <= 14) {
      return cleanValue
        .replace(/(\d{2})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d)/, '$1/$2')
        .replace(/(\d{4})(\d{1,2})/, '$1-$2');
    }
    return cleanValue;
  };

  const formatCEP = (value) => {
    const cleanValue = value.replace(/[^0-9]/g, '');
    if (cleanValue.length <= 8) {
      return cleanValue.replace(/(\d{5})(\d{1,3})/, '$1-$2');
    }
    return cleanValue;
  };
  const formatTelefone = (value) => {
    const cleanValue = value.replace(/[^0-9]/g, '');
    if (cleanValue.length <= 11) {
      if (cleanValue.length <= 10) {
        return cleanValue
          .replace(/(\d{2})(\d)/, '($1) $2')
          .replace(/(\d{4})(\d{1,4})/, '$1-$2');
      } else {
        return cleanValue
          .replace(/(\d{2})(\d)/, '($1) $2')
          .replace(/(\d{5})(\d{1,4})/, '$1-$2');
      }
    }
    return cleanValue;
  };

  const formatRG = (value) => {
    // Remove tudo que não é número ou X
    const cleanValue = value.replace(/[^0-9Xx]/g, '').toUpperCase();
    if (cleanValue.length <= 9) {
      return cleanValue
        .replace(/(\d{2})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d{1})/, '$1-$2');
    }
    return cleanValue;
  };

  const formatIE = (value) => {
    // Para IE, apenas números com formatação básica
    const cleanValue = value.replace(/[^0-9]/g, '');
    if (cleanValue.length <= 15) {
      // Formatação genérica para IE (pode variar por estado)
      return cleanValue
        .replace(/(\d{3})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d{1,3})/, '$1-$2');
    }
    return cleanValue;
  };
  // Função específica para campos numéricos com máscara
  const handleNumericChange = (e, maxLength, maskFunction) => {
    const { name } = e.target;
    let value = e.target.value.replace(/[^0-9]/g, '');
    
    if (maxLength && value.length > maxLength) {
      value = value.substring(0, maxLength);
    }
    
    // Limpa o erro do campo quando o usuário começar a digitar
    if (fieldErrors[name]) {
      setFieldErrors(prev => {
        const newErrors = { ...prev };
        delete newErrors[name];
        return newErrors;
      });
    }
    
    // Armazena o valor limpo no estado
    setCliente({ ...cliente, [name]: value });
  };
  // Função para obter valor formatado para exibição
  const getDisplayValue = (fieldName, value) => {
    if (!value) return '';
    
    switch (fieldName) {
      case 'telefone':
        return formatTelefone(value);
      case 'cep':
        return formatCEP(value);
      case 'cnpjCpf':
        return cliente.tipo === 'FISICA' ? formatCPF(value) : formatCNPJ(value);
      case 'rgInscricaoEstadual':
        return cliente.tipo === 'FISICA' ? formatRG(value) : formatIE(value);
      default:
        return value;
    }
  };
  // Função específica para RG (permite X no final)
  const handleRgChange = (e) => {
    const { name } = e.target;
    let value = e.target.value;
    const maxLength = cliente.tipo === 'FISICA' ? 9 : 15; // RG: 9 caracteres, IE: 15 caracteres
    
    if (cliente.tipo === 'FISICA') {
      // Para RG: permite números e X apenas no final
      value = value.replace(/[^0-9Xx]/g, '').toUpperCase();
      if (value.includes('X') && value.indexOf('X') !== value.length - 1) {
        value = value.replace(/X/g, '');
      }
    } else {
      // Para IE: apenas números
      value = value.replace(/[^0-9]/g, '');
    }
    
    if (value.length > maxLength) {
      value = value.substring(0, maxLength);
    }
    
    // Limpa o erro do campo quando o usuário começar a digitar
    if (fieldErrors[name]) {
      setFieldErrors(prev => {
        const newErrors = { ...prev };
        delete newErrors[name];
        return newErrors;
      });
    }
    
    setCliente({ ...cliente, [name]: value });
  };const handleSubmit = (e) => {
    e.preventDefault();
    
    // Limpa erros anteriores
    setFieldErrors({});
    setErrorMessage('');
    
    // Validação de campos obrigatórios
    const errors = {};
    
    if (!cliente.nome?.trim()) {
      errors.nome = 'Este campo é obrigatório';
    }
    
    if (!cliente.endereco?.trim()) {
      errors.endereco = 'Este campo é obrigatório';
    }
    
    if (!cliente.numero?.trim()) {
      errors.numero = 'Este campo é obrigatório';
    }
    
    if (!cliente.bairro?.trim()) {
      errors.bairro = 'Este campo é obrigatório';
    }
    
    if (!cliente.cep?.trim()) {
      errors.cep = 'Este campo é obrigatório';
    }
    
    if (!cliente.telefone?.trim()) {
      errors.telefone = 'Este campo é obrigatório';
    }
    
    if (!cliente.email?.trim()) {
      errors.email = 'Este campo é obrigatório';
    }
    
    if (!cliente.cnpjCpf?.trim()) {
      errors.cnpjCpf = 'Este campo é obrigatório';
    }
    
    if (!cliente.cidadeId) {
      errors.cidade = 'Selecione uma cidade';
    }
    
    // Se há erros, exibe e para a execução
    if (Object.keys(errors).length > 0) {
      setFieldErrors(errors);
      setShowRequiredErrors(true);
      setErrorMessage('Por favor, preencha todos os campos obrigatórios.');
      return;
    }
    
    // Validando campos obrigatórios
    if (!cliente.cidadeId) {
      setErrorMessage('Por favor, selecione uma cidade.');
      return;
    }
    
    // Validação do telefone (deve ter 10 ou 11 dígitos)
    const telefoneSemMascara = cliente.telefone?.replace(/\D/g, '') || '';
    if (telefoneSemMascara.length !== 0 && (telefoneSemMascara.length < 10 || telefoneSemMascara.length > 11)) {
      setErrorMessage('O telefone deve ter 10 ou 11 dígitos.');
      return;
    }
    
    // Validação do CPF/CNPJ
    const cpfCnpjSemMascara = cliente.cnpjCpf?.replace(/\D/g, '') || '';
    const isCpf = cliente.tipo === 'FISICA';
    const tamanhoEsperado = isCpf ? 11 : 14;
    
    if (cpfCnpjSemMascara.length !== 0 && cpfCnpjSemMascara.length !== tamanhoEsperado) {
      setErrorMessage(`O ${isCpf ? 'CPF' : 'CNPJ'} deve ter exatamente ${tamanhoEsperado} dígitos.`);
      return;
    }
    
    // Validação do CEP
    const cepSemMascara = cliente.cep?.replace(/\D/g, '') || '';
    if (cepSemMascara.length !== 0 && cepSemMascara.length !== 8) {
      setErrorMessage('O CEP deve ter exatamente 8 dígitos.');
      return;
    }

    // Formatando os dados para corresponder ao modelo esperado pelo backend
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
    // Limpa o erro da cidade quando uma cidade for selecionada
    if (fieldErrors.cidade) {
      setFieldErrors(prev => {
        const newErrors = { ...prev };
        delete newErrors.cidade;
        return newErrors;
      });
    }
    
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
    <Box sx={{ padding: 2, bgcolor: '#f8f9fa', minHeight: '100vh' }}>
      <Typography 
        variant="h5" 
        component="h1" 
        align="center" 
        gutterBottom 
        sx={{ mb: 2, color: '#333' }}
      >
        {id ? 'Editar Cliente' : 'Cadastrar Novo Cliente'}
      </Typography>        <Paper 
        component="form"
        onSubmit={handleSubmit}
        elevation={1}
        sx={{
          width: '100%',
          maxWidth: 1400, // Aumentado de 1200 para 1400
          mx: 'auto',
          p: { xs: 2, md: 3 },
          borderRadius: 2,
          overflow: 'hidden',
          position: 'relative',
          '& .MuiFormLabel-root': {
            display: 'flex',
            alignItems: 'flex-start',
            ml: -0.5,
          },
          '& .MuiFormControl-root': {
            width: '100%'
          }
        }}
      >        {/* Linha 1: Código, Tipo de Pessoa, Nome, Apelido, Estado Civil, Ativo */}

        <Grid container spacing={2} alignItems="center" sx={{ mb: 2 }}>
          <Grid item sx={{ width: '5%', minWidth: 80 }}>
            <TextField
              fullWidth
              size="small"
              label="Código"
              name="id"
              value={id || ''}
              InputProps={{ readOnly: true }}
              variant="outlined"
              disabled
            />
          </Grid>

          <Grid item sx={{ width: '14%', minWidth: 140 }}>
            <FormControl fullWidth size="small">
              <InputLabel>Tipo de Pessoa</InputLabel>
              <Select
                name="tipo"
                value={cliente.tipo}
                onChange={handleChange}
                label="Tipo de Pessoa"
              >
                <MenuItem value="FISICA">Pessoa Física</MenuItem>
                <MenuItem value="JURIDICA">Pessoa Jurídica</MenuItem>
              </Select>
            </FormControl>
          </Grid>

          <Grid item sx={{ width: '25%' }}>
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
              error={!!fieldErrors.nome}
              helperText={fieldErrors.nome || ''}
            />
          </Grid>
          <Grid item sx={{ width: '15%' }}>
            <TextField
              fullWidth
              size="small"
              label={cliente.tipo === 'FISICA' ? 'Apelido' : 'Nome Fantasia'}
              name="apelido"
              value={cliente.apelido}
              onChange={handleChange}
              placeholder={cliente.tipo === 'FISICA' ? "Apelido" : "Nome Fantasia"}
              variant="outlined"
            />
          </Grid>
          <Grid item sx={{ width: '15%' }}>
            <FormControl fullWidth size="small">
              <InputLabel>Estado Civil</InputLabel>
              <Select
                name="estadoCivil"
                value={cliente.estadoCivil}
                onChange={handleChange}
                label="Estado Civil"
                sx={{ minWidth: 140 }}
              >
                <MenuItem value="">Selecione...</MenuItem>
                <MenuItem value="SOLTEIRO">Solteiro(a)</MenuItem>
                <MenuItem value="CASADO">Casado(a)</MenuItem>
                <MenuItem value="DIVORCIADO">Divorciado(a)</MenuItem>
                <MenuItem value="VIUVO">Viúvo(a)</MenuItem>
                <MenuItem value="UNIAO_ESTAVEL">União Estável</MenuItem>
                <MenuItem value="SEPARADO">Separado(a)</MenuItem>
                <MenuItem value="OUTRO">Outro</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item sx={{ width: '12%', display: 'flex', justifyContent: 'center' }}>
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
        </Grid>{/* Linha 2: Rua, Número, Complemento, Bairro, CEP, Cidade */}
        <Grid container spacing={2} sx={{ mb: 2 }}>

          <Grid item sx={{ width: '30%' }}>
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
              error={!!fieldErrors.endereco}
              helperText={fieldErrors.endereco || ''}
            />
          </Grid>
              <Grid item sx={{ width: '8%', minWidth: 80 }}>
            <TextField
              fullWidth
              required
              size="small"
              label="Número"
              name="numero"
              value={cliente.numero}
              onChange={(e) => handleNumericChange(e)}
              placeholder="Nº"
              variant="outlined"
              inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }}
              error={!!fieldErrors.numero}
              helperText={fieldErrors.numero || ''}
            />
          </Grid>
            
          <Grid item sx={{ width: '13%' }}>
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
              <Grid item sx={{ width: '13%', minWidth: 120 }}>
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
              error={!!fieldErrors.bairro}
              helperText={fieldErrors.bairro || ''}
            />
          </Grid>

          <Grid item sx={{ width: '10%', minWidth: 100 }}>
            <TextField
              fullWidth
              required
              size="small"
              label="CEP"
              name="cep"
              value={getDisplayValue('cep', cliente.cep)}
              onChange={e => handleNumericChange(e, 8)}
              variant="outlined"
              error={!!fieldErrors.cep || (cliente.cep && cliente.cep.length !== 8)}
              helperText={fieldErrors.cep || (cliente.cep && cliente.cep.length !== 8 ? 'CEP inválido' : '')}
              inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }}
              autoComplete="off"
            />
          </Grid>

<Grid item sx={{ width: '20%', minWidth: 150 }}>
            <FormControl fullWidth variant="outlined" size="small" error={!!fieldErrors.cidade}>
              <TextField
                id="cidade-input"
                value={cliente.cidadeNome || ''}
                label="Cidade"
                disabled
                fullWidth
                size="small"
                sx={{
                  backgroundColor: '#f8f9fa',
                  '& .MuiInputBase-input': {
                    paddingTop: '8px',
                    paddingBottom: '8px'
                  }
                }}
                InputLabelProps={{ 
                  shrink: true
                }}
                InputProps={{
                  endAdornment: (
                    <Tooltip title="Buscar cidade">
                      <IconButton 
                        onClick={handleOpenCidadeModal}
                        size="small"
                        color="primary"
                      >
                        <SearchIcon />
                      </IconButton>
                    </Tooltip>
                  )
                }}
                error={!!fieldErrors.cidade}
                helperText={fieldErrors.cidade || ''}
              />
            </FormControl>
          </Grid>
        </Grid>{/* Linha 3: Telefone, Email, Sexo, Data de Nascimento */}
        <Grid container spacing={2} sx={{ mb: 2 }}>          <Grid item sx={{ width: '20%', minWidth: 150 }}>            <TextField
              fullWidth
              required
              size="small"
              label="Telefone"
              name="telefone"
              value={getDisplayValue('telefone', cliente.telefone)}
              onChange={e => handleNumericChange(e, 11)}
              variant="outlined"
              error={!!fieldErrors.telefone || (cliente.telefone && (cliente.telefone.length < 10 || cliente.telefone.length > 11))}
              helperText={fieldErrors.telefone || (cliente.telefone && (cliente.telefone.length < 10 || cliente.telefone.length > 11) ? 'Telefone inválido (10 ou 11 dígitos)' : '')}
              inputProps={{ inputMode: 'numeric' }}
              autoComplete="off"
            />
          </Grid>
          <Grid item sx={{ width: cliente.tipo === 'FISICA' ? '25%' : '40%' }}>
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
              error={!!fieldErrors.email}
              helperText={fieldErrors.email || ''}
            />
          </Grid>
          
          {cliente.tipo === 'FISICA' && (
            <Grid item sx={{ width: '15%', minWidth: 120 }}>
              <FormControl fullWidth size="small">
                <InputLabel>Sexo</InputLabel>
                <Select
                  name="sexo"
                  value={cliente.sexo}
                  onChange={handleChange}
                  label="Sexo"
                >
                  <MenuItem value="M">Masculino</MenuItem>
                  <MenuItem value="F">Feminino</MenuItem>
                </Select>
              </FormControl>
            </Grid>
          )}
          
          <Grid item sx={{ width: cliente.tipo === 'FISICA' ? '20%' : '35%', minWidth: 150 }}>
            <TextField
              fullWidth
              size="small"
              label={cliente.tipo === 'FISICA' ? 'Data de Nascimento' : 'Data de Abertura'}
              name="dataNascimento"
              type="date"
              value={cliente.dataNascimento ? cliente.dataNascimento.split('T')[0] : ''}
              onChange={handleChange}
              InputLabelProps={{ shrink: true }}
              variant="outlined"
              error={false}
              helperText={''}
              autoComplete="off"
            />
          </Grid>
        </Grid>        {/* Linha 4: CPF/CNPJ, RG/Inscrição Estadual, Limite de Crédito, Condição de Pagamento */}
        <Grid container spacing={2} sx={{ mb: 2 }}>

          <Grid item sx={{ width: '15%', minWidth: 150 }}>
            <TextField
              fullWidth
              required
              size="small"
              label={cliente.tipo === 'FISICA' ? 'CPF' : 'CNPJ'}
              name="cnpjCpf"
              value={getDisplayValue('cnpjCpf', cliente.cnpjCpf)}
              onChange={e => {
                const maxLength = cliente.tipo === 'FISICA' ? 11 : 14;
                handleNumericChange(e, maxLength);
              }}
              variant="outlined"
              error={!!fieldErrors.cnpjCpf || (cliente.cnpjCpf && ((cliente.tipo === 'FISICA' && cliente.cnpjCpf.length !== 11) || (cliente.tipo === 'JURIDICA' && cliente.cnpjCpf.length !== 14)))}
              helperText={fieldErrors.cnpjCpf || (cliente.cnpjCpf && ((cliente.tipo === 'FISICA' && cliente.cnpjCpf.length !== 11) || (cliente.tipo === 'JURIDICA' && cliente.cnpjCpf.length !== 14)) ? `${cliente.tipo === 'FISICA' ? 'CPF' : 'CNPJ'} inválido` : '')}
              inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }}
              autoComplete="off"
            />
          </Grid>

          <Grid item sx={{ width: '20%', minWidth: 150 }}>
            <TextField
              fullWidth
              size="small"
              label={cliente.tipo === 'FISICA' ? 'RG' : 'Inscrição Estadual'}
              name="rgInscricaoEstadual"
              value={getDisplayValue('rgInscricaoEstadual', cliente.rgInscricaoEstadual)}
              onChange={handleRgChange}
              variant="outlined"
              inputProps={{ maxLength: cliente.tipo === 'FISICA' ? 15 : 20 }}
              autoComplete="off"
            />
          </Grid>
          
          <Grid item sx={{ width: '16%', minWidth: 120 }}>
            <TextField
              fullWidth
              size="small"
              label="Limite de Crédito"
              name="limiteCredito"
              type="number"
              value={cliente.limiteCredito}
              onChange={handleChange}
              placeholder="0,00"
              InputProps={{
                startAdornment: <InputAdornment position="start">R$</InputAdornment>
              }}
              inputProps={{
                step: "0.01",
                min: "0"
              }}
              variant="outlined"
            />
          </Grid>

            <Grid item sx={{ width: '30%', minWidth: 200 }}>
            <FormControl fullWidth variant="outlined" size="small">
              <TextField
                id="condicao-pagamento-input"
                value={cliente.condicaoPagamentoDescricao || ''}
                label="Condição de Pagamento"
                disabled
                fullWidth
                size="small"
                sx={{
                  backgroundColor: '#f8f9fa',
                  '& .MuiInputBase-input': {
                    paddingTop: '8px',
                    paddingBottom: '8px'
                  }
                }}
                InputLabelProps={{ 
                  shrink: true 
                }}
                InputProps={{
                  endAdornment: (
                    <Tooltip title="Buscar condição de pagamento">
                      <IconButton 
                        onClick={handleOpenCondicaoPagamentoModal}
                        size="small"
                        color="primary"
                      >
                        <SearchIcon />
                      </IconButton>
                    </Tooltip>
                  )
                }}
              />
            </FormControl>
          </Grid>
        </Grid>{/* Linha 5: Observação */}
        <Grid container spacing={2} sx={{ mb: 2 }}>
          <Grid item sx={{ width: '100%' }}>
            <TextField
              fullWidth
              multiline
              rows={3}
              size="small"
              label="Observações"
              name="observacao"
              value={cliente.observacao}
              onChange={handleChange}
              placeholder="Informações adicionais sobre o cliente"
              variant="outlined"
            />
          </Grid>
        </Grid>          {/* Informações de registro */}        <Stack spacing={1} sx={{ mt: 2, mb: 1 }}>
          {cliente.dataCadastro && (
            <Typography variant="caption" color="text.secondary">
              Data de cadastro: {new Date(cliente.dataCadastro).toLocaleString('pt-BR')}
            </Typography>
          )}
          {cliente.ultimaModificacao && (
            <Typography variant="caption" color="text.secondary">
              Última modificação: {new Date(cliente.ultimaModificacao).toLocaleString('pt-BR')}
            </Typography>
          )}
        </Stack>{/* Mensagem de erro */}
        {errorMessage && (
          <Alert 
            severity="error" 
            variant="filled"
            onClose={() => setErrorMessage('')}
            sx={{ mb: 2, mt: 2 }}
          >
            {errorMessage}
          </Alert>
        )}{/* Botões */}        <Box
          sx={{
            display: 'flex',
            justifyContent: 'flex-end',
            gap: 1,
            mt: 2,
            pt: 2,
            borderTop: '1px solid #eee',
            position: 'sticky',
            bottom: '20px',
            backgroundColor: 'white',
            zIndex: 10,
            pb: 2,
            boxShadow: '0px -4px 8px rgba(0, 0, 0, 0.05)'
          }}
        >
          <Button
            variant="outlined"
            onClick={handleCancel}
            color="inherit"
            type="button"
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
