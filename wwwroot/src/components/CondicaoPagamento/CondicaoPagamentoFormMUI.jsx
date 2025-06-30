import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

// Importações do Material-UI
import {
  Box,
  Typography,
  TextField,
  Button,
  Grid,
  Switch,
  FormControlLabel,
  Paper,
  Alert,
  Stack,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  IconButton,
  Divider,
  Chip
} from '@mui/material';
import {
  Add as AddIcon,
  Delete as DeleteIcon,
  Calculate as CalculateIcon
} from '@mui/icons-material';

// Componente de formulário de condição de pagamento
const CondicaoPagamentoFormMUI = () => {
  const [condicaoPagamento, setCondicaoPagamento] = useState({
    descricao: '',
    dias: '',
    jurosPercentual: '',
    multaPercentual: '',
    descontoPercentual: '',
    ativo: true,
    dataCadastro: '',
    ultimaModificacao: '',
    parcelas: []
  });
  const [formasPagamento, setFormasPagamento] = useState([]);
  const [numeroParcelas, setNumeroParcelas] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [fieldErrors, setFieldErrors] = useState({});
  const [erroPercentual, setErroPercentual] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    // Buscar formas de pagamento
    fetch('http://localhost:8080/formas-pagamento')
      .then((response) => response.json())
      .then((data) => setFormasPagamento(data))
      .catch((error) => console.error('Erro ao buscar formas de pagamento:', error));

    // Buscar condição de pagamento para edição
    if (id) {
      fetch(`http://localhost:8080/condicoes-pagamento/${id}`)
        .then((response) => response.json())
        .then((data) => {
          console.log('Dados recebidos do backend:', data);
          
          const condicaoPagamentoAtualizada = {
            descricao: data.descricao || '',
            dias: data.dias || '',
            jurosPercentual: data.jurosPercentual || '',
            multaPercentual: data.multaPercentual || '',
            descontoPercentual: data.descontoPercentual || '',
            ativo: data.ativo ?? true,
            dataCadastro: data.dataCadastro || '',
            ultimaModificacao: data.ultimaModificacao || '',
            parcelas: (data.parcelasCondicao || []).map((parcela) => ({
              numeroParcela: parcela.numeroParcela || '',
              dias: parcela.dias || '',
              percentual: parcela.percentual || '',
              formaPagamentoId: parcela.formaPagamento?.id || '',
            }))
          };
          
          console.log('Condição de pagamento final:', condicaoPagamentoAtualizada);
          setCondicaoPagamento(condicaoPagamentoAtualizada);
        })
        .catch((error) => console.error('Erro ao buscar condição de pagamento:', error));
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    
    // Limpa o erro do campo quando o usuário começar a digitar
    if (fieldErrors[name]) {
      setFieldErrors(prev => {
        const newErrors = { ...prev };
        delete newErrors[name];
        return newErrors;
      });
    }
    
    setCondicaoPagamento({ ...condicaoPagamento, [name]: type === 'checkbox' ? checked : value });
  };

  const handleGerarParcelas = () => {
    const qtdParcelas = parseInt(numeroParcelas, 10) || 0;
    if (qtdParcelas <= 0) {
      setErrorMessage('Número de parcelas deve ser maior que zero.');
      return;
    }
    
    const parcelasGeradas = Array.from({ length: qtdParcelas }, (_, index) => ({
      numeroParcela: index + 1,
      dias: '',
      percentual: '',
      formaPagamentoId: '',
    }));
    
    setCondicaoPagamento({ ...condicaoPagamento, parcelas: parcelasGeradas });
    setNumeroParcelas('');
    setErrorMessage('');
  };

  const handleAdicionarParcela = () => {
    const proximoNumero = condicaoPagamento.parcelas.length + 1;
    const novaParcela = {
      numeroParcela: proximoNumero,
      dias: '',
      percentual: '',
      formaPagamentoId: '',
    };
    
    setCondicaoPagamento({ 
      ...condicaoPagamento, 
      parcelas: [...condicaoPagamento.parcelas, novaParcela] 
    });
  };

  const handleParcelaChange = (index, field, value) => {
    const parcelasAtualizadas = condicaoPagamento.parcelas.map((parcela, i) =>
      i === index ? { ...parcela, [field]: value } : parcela
    );
    setCondicaoPagamento({ ...condicaoPagamento, parcelas: parcelasAtualizadas });
  };

  const handleRemoverParcela = (index) => {
    const parcelasAtualizadas = condicaoPagamento.parcelas.filter((_, i) => i !== index);
    setCondicaoPagamento({ ...condicaoPagamento, parcelas: parcelasAtualizadas });
  };

  const calcularSomaPercentuais = () => {
    return condicaoPagamento.parcelas.reduce((soma, parcela) => soma + parseFloat(parcela.percentual || 0), 0);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    // Limpa erros anteriores
    setFieldErrors({});
    setErrorMessage('');
    setErroPercentual(false);
    
    // Validação de campos obrigatórios
    const errors = {};
    
    if (!condicaoPagamento.descricao?.trim()) {
      errors.descricao = 'Este campo é obrigatório';
    }
    
    if (!condicaoPagamento.dias?.toString().trim()) {
      errors.dias = 'Este campo é obrigatório';
    }
    
    // Validações das parcelas
    if (condicaoPagamento.parcelas.length === 0) {
      setErrorMessage('É necessário criar pelo menos uma parcela.');
      return;
    }
    
    const diasCondicao = parseInt(condicaoPagamento.dias, 10) || 0;
    const parcelasInvalidas = condicaoPagamento.parcelas.some(
      (parcela) => parseInt(parcela.dias, 10) > diasCondicao
    );
    
    if (parcelasInvalidas) {
      setErrorMessage('Os dias das parcelas não podem exceder o prazo total da condição de pagamento.');
      return;
    }
    
    const somaPercentuais = calcularSomaPercentuais();
    if (Math.abs(somaPercentuais - 100) > 0.01) {
      setErroPercentual(true);
      setErrorMessage('A soma dos percentuais das parcelas deve ser igual a 100%.');
      return;
    }
    
    // Se há erros, exibe e para a execução
    if (Object.keys(errors).length > 0) {
      setFieldErrors(errors);
      setErrorMessage('Por favor, preencha todos os campos obrigatórios.');
      return;
    }

    // Formatando os dados para corresponder ao modelo esperado pelo backend
    const payload = {
      descricao: condicaoPagamento.descricao,
      dias: parseInt(condicaoPagamento.dias, 10) || 0,
      parcelas: condicaoPagamento.parcelas.length,
      ativo: condicaoPagamento.ativo,
      jurosPercentual: parseFloat(condicaoPagamento.jurosPercentual) || 0,
      multaPercentual: parseFloat(condicaoPagamento.multaPercentual) || 0,
      descontoPercentual: parseFloat(condicaoPagamento.descontoPercentual) || 0,
      parcelasCondicao: condicaoPagamento.parcelas.map((parcela) => ({
        numeroParcela: parseInt(parcela.numeroParcela, 10) || 0,
        dias: parseInt(parcela.dias, 10) || 0,
        percentual: parseFloat(parcela.percentual) || 0,
        formaPagamento: { id: parseInt(parcela.formaPagamentoId, 10) || null },
      })),
    };

    console.log('Dados enviados:', payload);

    const method = id ? 'PUT' : 'POST';
    const url = id ? `http://localhost:8080/condicoes-pagamento/${id}` : 'http://localhost:8080/condicoes-pagamento';

    fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload),
    })
      .then((response) => {
        if (!response.ok) {
          return response.text().then(text => {
            console.error('Erro do servidor:', text);
            throw new Error(`Erro do servidor: ${response.status} - ${text}`);
          });
        }
        return response.json();
      })
      .then(() => {
        navigate('/condicoes-pagamento');
      })
      .catch((error) => {
        console.error('Erro ao salvar condição de pagamento:', error);
        setErrorMessage('Erro ao salvar condição de pagamento. Tente novamente.');
      });
  };

  const handleCancel = () => {
    navigate('/condicoes-pagamento');
  };

  return (
    <Box sx={{ 
      padding: { xs: 2, md: 3 }, 
      bgcolor: '#f8f9fa', 
      minHeight: '100vh',
      paddingBottom: 0.5
    }}>
      <Paper 
        component="form"
        onSubmit={handleSubmit}
        elevation={10}
        sx={{
          width: '95%',
          maxWidth: 1200,
          minHeight: 'auto',
          mx: 'auto',
          p: { xs: 2, md: 3, lg: 4 },
          pb: 0,
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
      >
        {/* Cabeçalho com título e switch Ativo */}
        <Box sx={{ 
          display: 'flex', 
          justifyContent: 'space-between', 
          alignItems: 'center', 
          mb: 4 
        }}>
          {/* Espaço vazio à esquerda para centralizar o título */}
          <Box sx={{ width: 120 }}></Box>
          
          {/* Título centralizado */}
          <Typography 
            variant="h5" 
            component="h1" 
            align="center" 
            sx={{ color: '#333', fontWeight: 600, flex: 1 }}
          >
            {id ? 'Editar Condição de Pagamento' : 'Cadastrar Nova Condição de Pagamento'}
          </Typography>

          {/* Switch Ativo à direita */}
          <Box sx={{ width: 120, display: 'flex', justifyContent: 'flex-end' }}>
            <FormControlLabel
              control={
                <Switch
                  checked={condicaoPagamento.ativo}
                  onChange={handleChange}
                  name="ativo"
                  color="primary"
                  disabled={!id} // Desabilita durante cadastro (quando não há id)
                />
              }
              label="Ativo"
              sx={{ mr: 0 }}
            />
          </Box>
        </Box>

        {/* Linha 1: Código, Descrição, Dias */}
        <Grid container spacing={2} alignItems="center" sx={{ mb: 4 }}>
          <Grid item sx={{ width: '8%', minWidth: 100 }}>
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

          <Grid item sx={{ width: '62%' }}>
            <TextField
              fullWidth
              required
              size="small"
              label="Descrição"
              name="descricao"
              value={condicaoPagamento.descricao}
              onChange={handleChange}
              placeholder="Descrição da condição de pagamento"
              variant="outlined"
              error={!!fieldErrors.descricao}
              helperText={fieldErrors.descricao || ''}
            />
          </Grid>

          <Grid item sx={{ width: '15%', minWidth: 120 }}>
            <TextField
              fullWidth
              required
              size="small"
              label="Prazo (dias)"
              name="dias"
              type="number"
              value={condicaoPagamento.dias}
              onChange={handleChange}
              placeholder="Dias"
              variant="outlined"
              error={!!fieldErrors.dias}
              helperText={fieldErrors.dias || ''}
              inputProps={{ min: 0 }}
            />
          </Grid>

          <Grid item sx={{ width: '30%' }}>
            <TextField
              fullWidth
              size="small"
              label="Juros (%)"
              name="jurosPercentual"
              type="number"
              value={condicaoPagamento.jurosPercentual}
              onChange={handleChange}
              placeholder="Juros"
              variant="outlined"
              inputProps={{ min: 0, step: 0.01 }}
            />
          </Grid>

          <Grid item sx={{ width: '30%' }}>
            <TextField
              fullWidth
              size="small"
              label="Multa (%)"
              name="multaPercentual"
              type="number"
              value={condicaoPagamento.multaPercentual}
              onChange={handleChange}
              placeholder="Multa"
              variant="outlined"
              inputProps={{ min: 0, step: 0.01 }}
            />
          </Grid>

          <Grid item sx={{ width: '30%' }}>
            <TextField
              fullWidth
              size="small"
              label="Desconto (%)"
              name="descontoPercentual"
              type="number"
              value={condicaoPagamento.descontoPercentual}
              onChange={handleChange}
              placeholder="Desconto"
              variant="outlined"
              inputProps={{ min: 0, step: 0.01 }}
            />
          </Grid>

          <Grid item sx={{ width: '15%', minWidth: 120 }}>
            {/* Espaço vazio para manter alinhamento */}
          </Grid>
        </Grid>

        {/* Seção de Parcelas */}
        <Divider sx={{ my: 3 }} />
        
        <Typography variant="h6" sx={{ mb: 2, fontWeight: 600, color: '#333' }}>
          Parcelas
        </Typography>

        {/* Gerador de Parcelas */}
        <Grid container spacing={2} alignItems="center" sx={{ mb: 3 }}>
          <Grid item xs={12} md={3}>
            <TextField
              fullWidth
              size="small"
              label="Número de Parcelas"
              type="number"
              value={numeroParcelas}
              onChange={(e) => setNumeroParcelas(e.target.value)}
              placeholder="Quantidade"
              variant="outlined"
              inputProps={{ min: 1 }}
            />
          </Grid>
          <Grid item xs={6} md={3}>
            <Button
              variant="contained"
              startIcon={<AddIcon />}
              onClick={handleGerarParcelas}
              sx={{ height: 40 }}
            >
              Gerar Parcelas
            </Button>
          </Grid>
          <Grid item xs={6} md={3}>
            <Button
              variant="outlined"
              startIcon={<AddIcon />}
              onClick={handleAdicionarParcela}
              sx={{ height: 40 }}
            >
              Adicionar Parcela
            </Button>
          </Grid>
          <Grid item xs={12} md={3}>
            {condicaoPagamento.parcelas.length > 0 && (
              <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                <CalculateIcon color="primary" />
                <Typography variant="body2" color="text.secondary">
                  Soma: {calcularSomaPercentuais().toFixed(2)}%
                </Typography>
                <Chip 
                  label={Math.abs(calcularSomaPercentuais() - 100) < 0.01 ? "✓ 100%" : "≠ 100%"}
                  size="small"
                  color={Math.abs(calcularSomaPercentuais() - 100) < 0.01 ? "success" : "error"}
                />
              </Box>
            )}
          </Grid>
        </Grid>

        {/* Lista de Parcelas */}
        {condicaoPagamento.parcelas.map((parcela, index) => (
          <Grid container spacing={2} alignItems="center" sx={{ mb: 2 }} key={index}>
            <Grid item xs={6} md={1.5}>
              <TextField
                fullWidth
                size="small"
                label="Nº"
                type="number"
                value={parcela.numeroParcela}
                onChange={(e) => handleParcelaChange(index, 'numeroParcela', e.target.value)}
                variant="outlined"
                inputProps={{ min: 1 }}
              />
            </Grid>
            <Grid item xs={6} md={2}>
              <TextField
                fullWidth
                size="small"
                label="Dias"
                type="number"
                value={parcela.dias}
                onChange={(e) => handleParcelaChange(index, 'dias', e.target.value)}
                variant="outlined"
                inputProps={{ min: 0 }}
              />
            </Grid>
            <Grid item xs={6} md={2}>
              <TextField
                fullWidth
                size="small"
                label="Percentual (%)"
                type="number"
                value={parcela.percentual}
                onChange={(e) => handleParcelaChange(index, 'percentual', e.target.value)}
                variant="outlined"
                inputProps={{ min: 0, max: 100, step: 0.01 }}
              />
            </Grid>
            <Grid item sx={{ width: '30%' }}>
              <FormControl fullWidth size="small">
                <InputLabel>Forma de Pagamento</InputLabel>
                <Select
                  value={parcela.formaPagamentoId}
                  onChange={(e) => handleParcelaChange(index, 'formaPagamentoId', e.target.value)}
                  label="Forma de Pagamento"
                >
                  <MenuItem value="">Selecione...</MenuItem>
                  {formasPagamento.map((forma) => (
                    <MenuItem key={forma.id} value={forma.id}>
                      {forma.descricao}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} md={1}>
              <IconButton
                color="error"
                onClick={() => handleRemoverParcela(index)}
                size="small"
              >
                <DeleteIcon />
              </IconButton>
            </Grid>
          </Grid>
        ))}

        {/* Mensagem de erro */}
        {(errorMessage || erroPercentual) && (
          <Alert 
            severity="error" 
            variant="filled"
            onClose={() => {
              setErrorMessage('');
              setErroPercentual(false);
            }}
            sx={{ mb: 2, mt: 2 }}
          >
            {errorMessage}
          </Alert>
        )}

        {/* Botões e Informações de registro */}
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'center',
            gap: 2,
            mt: 2,
            pt: 2,
            borderTop: '1px solid #eee',
            position: 'sticky',
            bottom: '5px',
            backgroundColor: 'white',
            zIndex: 10,
            pb: 0.5,
            boxShadow: '0px -4px 8px rgba(0, 0, 0, 0.05)'
          }}
        >
          {/* Informações de registro - lado esquerdo */}
          <Stack spacing={0.5} sx={{ flex: 1 }}>
            {condicaoPagamento.dataCadastro && (
              <Typography variant="caption" color="text.secondary">
                Data de cadastro: {new Date(condicaoPagamento.dataCadastro).toLocaleString('pt-BR')}
              </Typography>
            )}
            {condicaoPagamento.ultimaModificacao && (
              <Typography variant="caption" color="text.secondary">
                Última modificação: {new Date(condicaoPagamento.ultimaModificacao).toLocaleString('pt-BR')}
              </Typography>
            )}
          </Stack>

          {/* Botões - lado direito */}
          <Box sx={{ display: 'flex', gap: 2 }}>
            <Button
              variant="contained"
              type="submit"
              color="primary"
              size="medium"
              sx={{ minWidth: 100, py: 1 }}
            >
              Salvar
            </Button>
            <Button
              variant="outlined"
              onClick={handleCancel}
              color="inherit"
              type="button"
              size="medium"
              sx={{ minWidth: 100, py: 1 }}
            >
              Cancelar
            </Button>
          </Box>
        </Box>
      </Paper>
    </Box>
  );
};

export default CondicaoPagamentoFormMUI;
