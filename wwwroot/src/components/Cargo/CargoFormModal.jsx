import React, { useState } from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  FormControlLabel,
  Switch,
  Box,
  Typography,
  IconButton,
  Alert
} from '@mui/material';
import {
  Close as CloseIcon
} from '@mui/icons-material';

const CargoFormModal = ({ onClose }) => {
  const [cargo, setCargo] = useState({
    nome: '',
    descricao: '',
    cargaHoraria: '',
    requerCnh: false,
    ativo: true
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [fieldErrors, setFieldErrors] = useState({});

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setCargo({
      ...cargo,
      [name]: type === 'checkbox' ? checked : value
    });

    // Limpar erro do campo quando o usuário começar a digitar
    if (fieldErrors[name]) {
      setFieldErrors(prev => {
        const newErrors = { ...prev };
        delete newErrors[name];
        return newErrors;
      });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    setFieldErrors({});

    // Validação
    const errors = {};
    if (!cargo.nome.trim()) {
      errors.nome = 'Nome é obrigatório';
    }

    if (Object.keys(errors).length > 0) {
      setFieldErrors(errors);
      setLoading(false);
      return;
    }

    try {
      const cargoData = {
        ...cargo,
        cargaHoraria: cargo.cargaHoraria ? parseFloat(cargo.cargaHoraria) : null
      };

      const response = await fetch('http://localhost:8080/funcoes-funcionario', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(cargoData),
      });

      if (response.ok) {
        onClose(); // Fechar modal e recarregar lista
      } else {
        const errorText = await response.text();
        setError(`Erro ao salvar cargo: ${errorText}`);
      }
    } catch (error) {
      console.error('Erro ao salvar cargo:', error);
      setError('Erro ao salvar cargo. Tente novamente.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Dialog
      open={true}
      onClose={onClose}
      maxWidth="sm"
      fullWidth
      PaperProps={{
        sx: { borderRadius: 2 }
      }}
    >
      <DialogTitle sx={{ 
        bgcolor: '#ffffff', 
        display: 'flex', 
        justifyContent: 'space-between', 
        alignItems: 'center',
        pb: 2
      }}>
        <Typography variant="h6" fontWeight={600}>
          Adicionar Novo Cargo
        </Typography>
        <IconButton onClick={onClose} size="small">
          <CloseIcon />
        </IconButton>
      </DialogTitle>

      <form onSubmit={handleSubmit}>
        <DialogContent sx={{ p: 3 }}>
          {error && (
            <Alert severity="error" sx={{ mb: 2 }}>
              {error}
            </Alert>
          )}

          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
            <TextField
              fullWidth
              required
              label="Nome do Cargo"
              name="nome"
              value={cargo.nome}
              onChange={handleChange}
              error={!!fieldErrors.nome}
              helperText={fieldErrors.nome}
              variant="outlined"
            />

            <TextField
              fullWidth
              label="Descrição"
              name="descricao"
              value={cargo.descricao}
              onChange={handleChange}
              multiline
              rows={3}
              variant="outlined"
            />

            <TextField
              fullWidth
              label="Carga Horária (horas)"
              name="cargaHoraria"
              type="number"
              value={cargo.cargaHoraria}
              onChange={handleChange}
              variant="outlined"
              inputProps={{
                step: "0.5",
                min: "0"
              }}
            />

            <FormControlLabel
              control={
                <Switch
                  checked={cargo.requerCnh}
                  onChange={handleChange}
                  name="requerCnh"
                  color="primary"
                />
              }
              label="Requer CNH"
            />

            <FormControlLabel
              control={
                <Switch
                  checked={cargo.ativo}
                  onChange={handleChange}
                  name="ativo"
                  color="primary"
                />
              }
              label="Ativo"
            />
          </Box>
        </DialogContent>

        <DialogActions sx={{ p: 3, bgcolor: '#ffffff' }}>
          <Button
            onClick={onClose}
            variant="outlined"
            color="inherit"
            disabled={loading}
          >
            Cancelar
          </Button>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            disabled={loading}
          >
            {loading ? 'Salvando...' : 'Salvar'}
          </Button>
        </DialogActions>
      </form>
    </Dialog>
  );
};

export default CargoFormModal;
