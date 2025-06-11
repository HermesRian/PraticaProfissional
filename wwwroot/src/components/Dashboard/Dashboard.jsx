import React from 'react';
import { Box, Typography, Card, CardContent, Grid } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import {
  People as PeopleIcon,
  Business as BusinessIcon,
  Work as WorkIcon,
  Inventory as InventoryIcon,
} from '@mui/icons-material';

const Dashboard = () => {
  const navigate = useNavigate();

  const dashboardCards = [
    {
      title: 'Clientes',
      description: 'Gerencie seus clientes',
      icon: <PeopleIcon sx={{ fontSize: 40, color: 'primary.main', mr: 2 }} />,
      path: '/clientes',
    },
    {
      title: 'Fornecedores',
      description: 'Gerencie seus fornecedores',
      icon: <BusinessIcon sx={{ fontSize: 40, color: 'primary.main', mr: 2 }} />,
      path: '/fornecedores',
    },
    {
      title: 'Funcionários',
      description: 'Gerencie seus funcionários',
      icon: <WorkIcon sx={{ fontSize: 40, color: 'primary.main', mr: 2 }} />,
      path: '/funcionarios',
    },
    {
      title: 'Produtos',
      description: 'Gerencie seus produtos',
      icon: <InventoryIcon sx={{ fontSize: 40, color: 'primary.main', mr: 2 }} />,
      path: '/produtos',
    },
  ];

  const handleCardClick = (path) => {
    navigate(path);
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Dashboard
      </Typography>
      
      <Typography variant="subtitle1" color="text.secondary" paragraph>
        Bem-vindo ao Sistema de Gestão da Cantina
      </Typography>

      <Grid container spacing={3} sx={{ mt: 2 }}>
        {dashboardCards.map((card, index) => (
          <Grid item xs={12} sm={6} md={3} key={index}>
            <Card 
              sx={{ 
                cursor: 'pointer',
                transition: 'all 0.2s ease-in-out',
                '&:hover': {
                  transform: 'translateY(-4px)',
                  boxShadow: 6,
                  '& .card-icon': {
                    transform: 'scale(1.1)',
                  },
                },
              }}
              onClick={() => handleCardClick(card.path)}
            >
              <CardContent sx={{ display: 'flex', alignItems: 'center' }}>
                <Box className="card-icon" sx={{ transition: 'transform 0.2s ease-in-out' }}>
                  {card.icon}
                </Box>
                <Box>
                  <Typography variant="h6">{card.title}</Typography>
                  <Typography variant="body2" color="text.secondary">
                    {card.description}
                  </Typography>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
};

export default Dashboard;
