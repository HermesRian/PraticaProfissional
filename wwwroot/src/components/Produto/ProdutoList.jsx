import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';

const ProdutoList = () => {
  const [produtos, setProdutos] = useState([]);
  const navigate = useNavigate();


  return (
    <div>
        <img src="https://images.tcdn.com.br/img/img_prod/861159/placa_sinalizacao_de_obras_a_24_obras_5964537_1_d754ed4bcbb34fee792ee1cda882ef09.jpg" alt="Em obras." style={{ maxWidth: "100%", maxHeight: "100%" }} />
    </div>
  );
};

export default ProdutoList;