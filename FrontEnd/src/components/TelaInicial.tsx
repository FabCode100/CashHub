import React from 'react';
import grafico from '../assets/grafico.png';
import './telaInicial.css'; 

const TelaInicial: React.FC = () => {
  return (
    <><div className="header">
          <h5>Olá, Fabrício B. Cardoso</h5>
      </div><div className="saldo">
              <p>Seu saldo é:</p>
              <p>R$ 9.999.999.999.999,99</p>
          </div><div className="conta">
              <p>Suas contas:</p>
              <div className="box">
                  <div className="quadrado">Fluxo de Caixa</div>
                  <div className="quadrado">Gestão de projetos</div>
                  <div className="quadrado">Despesas a pagar</div>
                  <div className="quadrado">Lucros futuros</div>
              </div>
          </div><div className="grafico">
              <img src={grafico} alt="Gráfico de finanças" /><br />
              <button>Visualizar Gráfico</button>
          </div></>
  );
};

export default TelaInicial;
