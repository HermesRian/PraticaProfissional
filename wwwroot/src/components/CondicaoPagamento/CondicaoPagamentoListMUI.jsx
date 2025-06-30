import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import {
  Box,
  Typography,
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TableSortLabel,
  IconButton,
  Chip,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Grid,
  Divider,
  TextField,
  InputAdornment,
  Tooltip,
  Alert,
  Stack,
  Switch,
  FormControlLabel,
  Select,
  MenuItem,
  FormControl,
  InputLabel
} from '@mui/material';
import {
  Visibility as VisibilityIcon,
  Edit as EditIcon,
  Delete as DeleteIcon,
  Add as AddIcon,
  Search as SearchIcon,
  Payment as PaymentIcon,
  Close as CloseIcon,
  Block as BlockIcon,
  CheckCircle as CheckCircleIcon,
  Schedule as ScheduleIcon
} from '@mui/icons-material';

const CondicaoPagamentoListMUI = () => {
  const [condicoesPagamento, setCondicoesPagamento] = useState([]);
  const [condicaoSelecionada, setCondicaoSelecionada] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [sortConfig, setSortConfig] = useState({ key: 'descricao', direction: 'asc' });
  const [filtro, setFiltro] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [filtroStatus, setFiltroStatus] = useState('todos'); // 'todos', 'ativos', 'inativos'
  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://localhost:8080/condicoes-pagamento')
      .then((response) => response.json())
      .then((data) => {
        setCondicoesPagamento(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Erro ao buscar condições de pagamento:', error);
        setError('Erro ao carregar condições de pagamento');
        setLoading(false);
      });
  }, []);

  const handleSort = (key) => {
    let direction = 'asc';
    if (sortConfig.key === key && sortConfig.direction === 'asc') {
      direction = 'desc';
    }
    setSortConfig({ key, direction });

    const sortedCondicoes = [...condicoesPagamento].sort((a, b) => {
      let aValue = a[key];
      let bValue = b[key];

      if (typeof aValue === 'string') {
        aValue = aValue.toLowerCase();
        bValue = bValue.toLowerCase();
      }

      if (aValue < bValue) return direction === 'asc' ? -1 : 1;
      if (aValue > bValue) return direction === 'asc' ? 1 : -1;
      return 0;
    });

    setCondicoesPagamento(sortedCondicoes);
  };

  const handleViewDetails = (condicao) => {
    setCondicaoSelecionada(condicao);
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setCondicaoSelecionada(null);
    setIsModalOpen(false);
  };

  const handleDelete = (id) => {
    if (window.confirm('Tem certeza que deseja excluir esta condição de pagamento?')) {
      fetch(`http://localhost:8080/condicoes-pagamento/${id}`, {
        method: 'DELETE',
      })
        .then((response) => {
          if (response.ok) {
            setCondicoesPagamento(condicoesPagamento.filter((condicao) => condicao.id !== id));
          } else {
            setError('Erro ao excluir condição de pagamento');
          }
        })
        .catch((error) => {
          console.error('Erro ao excluir condição de pagamento:', error);
          setError('Erro ao excluir condição de pagamento');
        });
    }
  };

  const filteredCondicoes = condicoesPagamento.filter((condicao) => {
    const matchesFilter = condicao.descricao?.toLowerCase().includes(filtro.toLowerCase()) ||
                         condicao.id?.toString().includes(filtro);
    
    const matchesStatus = filtroStatus === 'todos' ||
                         (filtroStatus === 'ativos' && condicao.ativo) ||
                         (filtroStatus === 'inativos' && !condicao.ativo);
    
    return matchesFilter && matchesStatus;
  });

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '50vh' }}>
        <Typography>Carregando...</Typography>
      </Box>
    );
  }

  return (
    <Box sx={{ padding: { xs: 2, md: 3 }, bgcolor: '#f8f9fa', minHeight: '100vh' }}>
      <Paper elevation={10} sx={{ p: { xs: 2, md: 3 }, borderRadius: 2 }}>
        {/* Cabeçalho */}
        <Box sx={{ 
          display: 'flex', 
          justifyContent: 'space-between', 
          alignItems: 'center', 
          mb: 3,
          flexWrap: 'wrap',
          gap: 2
        }}>
          <Typography 
            variant="h4" 
            component="h1" 
            sx={{ 
              fontWeight: 600, 
              color: '#333',
              display: 'flex',
              alignItems: 'center',
              gap: 1
            }}
          >
            <PaymentIcon sx={{ fontSize: 35 }} />
            Condições de Pagamento
          </Typography>
          <Button
            variant="contained"
            startIcon={<AddIcon />}
            component={Link}
            to="/condicoes-pagamento/cadastrar"
            sx={{ 
              bgcolor: '#1976d2',
              '&:hover': { bgcolor: '#1565c0' },
              minWidth: { xs: '100%', sm: 'auto' }
            }}
          >
            Nova Condição
          </Button>
        </Box>

        {/* Filtros */}
        <Grid container spacing={2} sx={{ mb: 3 }}>
          <Grid item xs={12} md={6}>
            <TextField
              fullWidth
              size="small"
              label="Pesquisar"
              value={filtro}
              onChange={(e) => setFiltro(e.target.value)}
              placeholder="Pesquisar por código ou descrição..."
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <SearchIcon />
                  </InputAdornment>
                ),
              }}
            />
          </Grid>
          
          <Grid item xs={12} md={3}>
            <FormControl fullWidth size="small">
              <InputLabel>Status</InputLabel>
              <Select
                value={filtroStatus}
                onChange={(e) => setFiltroStatus(e.target.value)}
                label="Status"
              >
                <MenuItem value="todos">Todos</MenuItem>
                <MenuItem value="ativos">Ativos</MenuItem>
                <MenuItem value="inativos">Inativos</MenuItem>
              </Select>
            </FormControl>
          </Grid>

          <Grid item xs={12} md={3}>
            <Typography variant="body2" color="text.secondary" sx={{ mt: 1 }}>
              Total: {filteredCondicoes.length} condição(ões)
            </Typography>
          </Grid>
        </Grid>

        {/* Mensagem de erro */}
        {error && (
          <Alert severity="error" sx={{ mb: 2 }} onClose={() => setError('')}>
            {error}
          </Alert>
        )}

        {/* Tabela */}
        <TableContainer component={Paper} elevation={0} sx={{ border: '1px solid #e0e0e0' }}>
          <Table size="small">
            <TableHead>
              <TableRow sx={{ bgcolor: '#f5f5f5' }}>
                <TableCell>
                  <TableSortLabel
                    active={sortConfig.key === 'id'}
                    direction={sortConfig.key === 'id' ? sortConfig.direction : 'asc'}
                    onClick={() => handleSort('id')}
                  >
                    Código
                  </TableSortLabel>
                </TableCell>
                <TableCell>
                  <TableSortLabel
                    active={sortConfig.key === 'descricao'}
                    direction={sortConfig.key === 'descricao' ? sortConfig.direction : 'asc'}
                    onClick={() => handleSort('descricao')}
                  >
                    Descrição
                  </TableSortLabel>
                </TableCell>
                <TableCell align="center">
                  <TableSortLabel
                    active={sortConfig.key === 'dias'}
                    direction={sortConfig.key === 'dias' ? sortConfig.direction : 'asc'}
                    onClick={() => handleSort('dias')}
                  >
                    Prazo (dias)
                  </TableSortLabel>
                </TableCell>
                <TableCell align="center">
                  <TableSortLabel
                    active={sortConfig.key === 'parcelas'}
                    direction={sortConfig.key === 'parcelas' ? sortConfig.direction : 'asc'}
                    onClick={() => handleSort('parcelas')}
                  >
                    Parcelas
                  </TableSortLabel>
                </TableCell>
                <TableCell align="center">Status</TableCell>
                <TableCell align="center">Ações</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {filteredCondicoes.map((condicao) => (
                <TableRow 
                  key={condicao.id}
                  sx={{ 
                    '&:hover': { bgcolor: '#f8f9fa' },
                    '&:nth-of-type(even)': { bgcolor: '#fafafa' }
                  }}
                >
                  <TableCell sx={{ fontWeight: 500 }}>
                    {String(condicao.id).padStart(3, '0')}
                  </TableCell>
                  <TableCell sx={{ fontWeight: 500 }}>
                    {condicao.descricao}
                  </TableCell>
                  <TableCell align="center">
                    <Chip
                      icon={<ScheduleIcon />}
                      label={`${condicao.dias} dias`}
                      size="small"
                      variant="outlined"
                      color="info"
                    />
                  </TableCell>
                  <TableCell align="center">
                    <Chip
                      label={`${condicao.parcelas || 0}x`}
                      size="small"
                      variant="outlined"
                      color="default"
                    />
                  </TableCell>
                  <TableCell align="center">
                    <Chip
                      icon={condicao.ativo ? <CheckCircleIcon /> : <BlockIcon />}
                      label={condicao.ativo ? 'Ativo' : 'Inativo'}
                      size="small"
                      color={condicao.ativo ? 'success' : 'error'}
                      variant={condicao.ativo ? 'filled' : 'outlined'}
                    />
                  </TableCell>
                  <TableCell align="center">
                    <Box sx={{ display: 'flex', gap: 0.5, justifyContent: 'center' }}>
                      <Tooltip title="Visualizar">
                        <IconButton
                          size="small"
                          onClick={() => handleViewDetails(condicao)}
                          sx={{ color: '#1976d2' }}
                        >
                          <VisibilityIcon fontSize="small" />
                        </IconButton>
                      </Tooltip>
                      <Tooltip title="Editar">
                        <IconButton
                          size="small"
                          component={Link}
                          to={`/condicoes-pagamento/editar/${condicao.id}`}
                          sx={{ color: '#ed6c02' }}
                        >
                          <EditIcon fontSize="small" />
                        </IconButton>
                      </Tooltip>
                      <Tooltip title="Excluir">
                        <IconButton
                          size="small"
                          onClick={() => handleDelete(condicao.id)}
                          sx={{ color: '#d32f2f' }}
                        >
                          <DeleteIcon fontSize="small" />
                        </IconButton>
                      </Tooltip>
                    </Box>
                  </TableCell>
                </TableRow>
              ))}
              {filteredCondicoes.length === 0 && (
                <TableRow>
                  <TableCell colSpan={6} align="center" sx={{ py: 4 }}>
                    <Typography variant="body2" color="text.secondary">
                      Nenhuma condição de pagamento encontrada
                    </Typography>
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>

        {/* Modal de Detalhes */}
        <Dialog 
          open={isModalOpen} 
          onClose={handleCloseModal}
          maxWidth="md"
          fullWidth
          PaperProps={{
            sx: { borderRadius: 2 }
          }}
        >
          <DialogTitle sx={{ 
            display: 'flex', 
            justifyContent: 'space-between', 
            alignItems: 'center',
            bgcolor: '#f5f5f5',
            borderBottom: '1px solid #e0e0e0'
          }}>
            <Typography variant="h6" component="div" sx={{ fontWeight: 600 }}>
              Detalhes da Condição de Pagamento
            </Typography>
            <IconButton onClick={handleCloseModal} size="small">
              <CloseIcon />
            </IconButton>
          </DialogTitle>
          
          <DialogContent sx={{ p: 3 }}>
            {condicaoSelecionada && (
              <Grid container spacing={3}>
                {/* Informações Básicas */}
                <Grid item xs={12}>
                  <Typography variant="h6" gutterBottom sx={{ color: '#1976d2', fontWeight: 600 }}>
                    Informações Básicas
                  </Typography>
                  <Divider sx={{ mb: 2 }} />
                </Grid>
                
                <Grid item xs={12} md={4}>
                  <Typography variant="subtitle2" color="text.secondary">
                    Código
                  </Typography>
                  <Typography variant="body1" sx={{ fontWeight: 500 }}>
                    {String(condicaoSelecionada.id).padStart(3, '0')}
                  </Typography>
                </Grid>
                
                <Grid item xs={12} md={8}>
                  <Typography variant="subtitle2" color="text.secondary">
                    Descrição
                  </Typography>
                  <Typography variant="body1" sx={{ fontWeight: 500 }}>
                    {condicaoSelecionada.descricao}
                  </Typography>
                </Grid>
                
                <Grid item xs={12} md={3}>
                  <Typography variant="subtitle2" color="text.secondary">
                    Prazo
                  </Typography>
                  <Typography variant="body1" sx={{ fontWeight: 500 }}>
                    {condicaoSelecionada.dias} dias
                  </Typography>
                </Grid>
                
                <Grid item xs={12} md={3}>
                  <Typography variant="subtitle2" color="text.secondary">
                    Parcelas
                  </Typography>
                  <Typography variant="body1" sx={{ fontWeight: 500 }}>
                    {condicaoSelecionada.parcelas || 0}x
                  </Typography>
                </Grid>
                
                <Grid item xs={12} md={3}>
                  <Typography variant="subtitle2" color="text.secondary">
                    Status
                  </Typography>
                  <Chip
                    icon={condicaoSelecionada.ativo ? <CheckCircleIcon /> : <BlockIcon />}
                    label={condicaoSelecionada.ativo ? 'Ativo' : 'Inativo'}
                    size="small"
                    color={condicaoSelecionada.ativo ? 'success' : 'error'}
                    variant={condicaoSelecionada.ativo ? 'filled' : 'outlined'}
                  />
                </Grid>

                {/* Informações Financeiras */}
                <Grid item xs={12}>
                  <Typography variant="h6" gutterBottom sx={{ color: '#1976d2', fontWeight: 600, mt: 2 }}>
                    Informações Financeiras
                  </Typography>
                  <Divider sx={{ mb: 2 }} />
                </Grid>
                
                <Grid item xs={12} md={4}>
                  <Typography variant="subtitle2" color="text.secondary">
                    Juros (%)
                  </Typography>
                  <Typography variant="body1" sx={{ fontWeight: 500 }}>
                    {condicaoSelecionada.jurosPercentual || 0}%
                  </Typography>
                </Grid>
                
                <Grid item xs={12} md={4}>
                  <Typography variant="subtitle2" color="text.secondary">
                    Multa (%)
                  </Typography>
                  <Typography variant="body1" sx={{ fontWeight: 500 }}>
                    {condicaoSelecionada.multaPercentual || 0}%
                  </Typography>
                </Grid>
                
                <Grid item xs={12} md={4}>
                  <Typography variant="subtitle2" color="text.secondary">
                    Desconto (%)
                  </Typography>
                  <Typography variant="body1" sx={{ fontWeight: 500 }}>
                    {condicaoSelecionada.descontoPercentual || 0}%
                  </Typography>
                </Grid>

                {/* Parcelas */}
                {condicaoSelecionada.parcelasCondicao && condicaoSelecionada.parcelasCondicao.length > 0 && (
                  <>
                    <Grid item xs={12}>
                      <Typography variant="h6" gutterBottom sx={{ color: '#1976d2', fontWeight: 600, mt: 2 }}>
                        Parcelas
                      </Typography>
                      <Divider sx={{ mb: 2 }} />
                    </Grid>
                    
                    <Grid item xs={12}>
                      <TableContainer component={Paper} variant="outlined">
                        <Table size="small">
                          <TableHead>
                            <TableRow sx={{ bgcolor: '#f5f5f5' }}>
                              <TableCell align="center">Nº</TableCell>
                              <TableCell align="center">Dias</TableCell>
                              <TableCell align="center">Percentual</TableCell>
                              <TableCell>Forma de Pagamento</TableCell>
                            </TableRow>
                          </TableHead>
                          <TableBody>
                            {condicaoSelecionada.parcelasCondicao.map((parcela, index) => (
                              <TableRow key={index}>
                                <TableCell align="center" sx={{ fontWeight: 500 }}>
                                  {parcela.numeroParcela}
                                </TableCell>
                                <TableCell align="center">
                                  {parcela.dias}
                                </TableCell>
                                <TableCell align="center">
                                  <Chip
                                    label={`${parcela.percentual}%`}
                                    size="small"
                                    variant="outlined"
                                    color="primary"
                                  />
                                </TableCell>
                                <TableCell>
                                  {parcela.formaPagamento?.descricao || 'Não informada'}
                                </TableCell>
                              </TableRow>
                            ))}
                          </TableBody>
                        </Table>
                      </TableContainer>
                    </Grid>
                  </>
                )}

                {/* Datas */}
                {(condicaoSelecionada.dataCadastro || condicaoSelecionada.ultimaModificacao) && (
                  <>
                    <Grid item xs={12}>
                      <Typography variant="h6" gutterBottom sx={{ color: '#1976d2', fontWeight: 600, mt: 2 }}>
                        Informações de Registro
                      </Typography>
                      <Divider sx={{ mb: 2 }} />
                    </Grid>
                    
                    {condicaoSelecionada.dataCadastro && (
                      <Grid item xs={12} md={6}>
                        <Typography variant="subtitle2" color="text.secondary">
                          Data de Cadastro
                        </Typography>
                        <Typography variant="body1" sx={{ fontWeight: 500 }}>
                          {new Date(condicaoSelecionada.dataCadastro).toLocaleString('pt-BR')}
                        </Typography>
                      </Grid>
                    )}
                    
                    {condicaoSelecionada.ultimaModificacao && (
                      <Grid item xs={12} md={6}>
                        <Typography variant="subtitle2" color="text.secondary">
                          Última Modificação
                        </Typography>
                        <Typography variant="body1" sx={{ fontWeight: 500 }}>
                          {new Date(condicaoSelecionada.ultimaModificacao).toLocaleString('pt-BR')}
                        </Typography>
                      </Grid>
                    )}
                  </>
                )}
              </Grid>
            )}
          </DialogContent>
          
          <DialogActions sx={{ p: 2, pt: 0 }}>
            <Button onClick={handleCloseModal} variant="outlined">
              Fechar
            </Button>
            {condicaoSelecionada && (
              <Button
                variant="contained"
                startIcon={<EditIcon />}
                component={Link}
                to={`/condicoes-pagamento/editar/${condicaoSelecionada.id}`}
                onClick={handleCloseModal}
              >
                Editar
              </Button>
            )}
          </DialogActions>
        </Dialog>
      </Paper>
    </Box>
  );
};

export default CondicaoPagamentoListMUI;
