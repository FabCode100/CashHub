import React from "react";
import "./cadrastro.css";

const Cadastro: React.FC = () => {
  const handleCadastro = () => {
  };

  return (
    <div className="container">
      <div className="texto">
        <p>Digite seus dados cadastrais:</p>
      </div>
      <div className="nome">
        <label htmlFor="nome">Nome completo:</label> <br/>
        <input type="text" name="cnpj" placeholder="Nome completo"/>
      </div>
      <div className="sexo">
        <form>
          <label htmlFor="sexo">Sexo:</label> <br/>
          <select id="sexo" name="sexo">
            <option value="escolha">Escolha</option>
            <option value="masculino">Masculino</option>
            <option value="feminino">Feminino</option>
            <option value="outro">Outro</option>
            <option value="prefiro">Prefiro não dizer</option>
          </select>
        </form>
      </div>
      <div className="data">
        <form>
          <label htmlFor="dataNascimento">Data de Nascimento:</label> <br/>
          <input type="date" name="dataNascimento" placeholder="DD/MM/AAAA"/>
        </form>
      </div>
      <div className="cpf">
        <form>
          <label htmlFor="cpf">CPF:</label> <br/>
          <input type="text" name="cpf" placeholder="000.000.000-00"/>
        </form>
      </div>
      <div className="telefone">
        <form>
          <label htmlFor="telefone">Telefone:</label> <br/>
          <input type="text" name="telefone" placeholder="(00) 0 0000-0000"/>
        </form>
      </div>
      <div className="email">
        <form>
          <label htmlFor="email">Endereço de E-mail:</label> <br/>
          <input type="email" name="email" placeholder="Email"/>
        </form>
      </div>
      <div className="senha">
        <form>
          <label htmlFor="senha">Senha:</label> <br/>
          <input type="password" name="senha" placeholder="Senha"/>
        </form>
      </div>
      <div className="confirmarSenha">
        <form>
          <label htmlFor="Csenha">Confirme sua senha:</label> <br/>
          <input type="password" name="Csenha" placeholder="Confirme sua senha"/>
        </form>
      </div>
      <div className="cadastro">
        <button onClick={handleCadastro}>Cadastre-se</button>
      </div>
      <div className="login">
        <p>Possui conta no Cash Hub? <br/>
          <a href="#">Faça login</a>
        </p>
      </div>
    </div>
  );
};

export default Cadastro;
