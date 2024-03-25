import React from "react";
import logoC from "../assets/logoc.png";
import "./login.css";

const Login: React.FC = () => {
  return (
    <div className="container">
      <div className="logo">
        <img src={logoC} alt="logo do Cash Hub" />
      </div>
      <div className="ap">
        <h5>Seja bem vindo ao Cash Hub!</h5>
      </div>
      <div className="cnpj">
        <p>Digite o seu CNPJ:</p>
        <input type="text" name="cnpj" placeholder="12.345.678/0001-00" />
      </div>
      <div className="senha">
        <p>Digite sua senha:</p>
        <input type="password" name="senha" placeholder="Senha" />
      </div>
      <div className="login">
      <button onclick="login()">Login</button>
      </div>
      <div className="cadastro">
        <p>
          Não possui conta no Cash Hub? <br />
          <a href="#">Cadastre-se aqui!</a>
        </p>
      </div>
    </div>
  );
};

export default Login;
