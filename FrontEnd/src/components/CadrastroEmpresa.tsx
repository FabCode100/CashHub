import React from "react";
import "./cadrastroEmpresa.css";

const CadastroEmpresa: React.FC = () => {
    return (
        <div className="container">
            <div className="texto">
                <p>Informe dados da Empresa:</p> <br />
            </div>
            <div className="nome">
                <label htmlFor="nomeFantasia">Nome fantasia:</label> <br />
                <input type="text" name="nome" placeholder="Nome fantasia" />
            </div>
            <div className="cnpj">
                <label htmlFor="cnpj">CNPJ:</label> <br />
                <input type="text" name="cnpj" placeholder="12.345.678/0001-00" />
            </div>
            <div className="dados">
                <label htmlFor="Estado">Qual o seu estado?</label> <br />
                <input type="text" name="estado" placeholder="estado" /> <br /> <br />
                <label htmlFor="Estado">Qual a sua cidade?</label> <br />
                <input type="text" name="cidade" placeholder="cidade" /> <br /> <br />
                <label htmlFor="endereço">Qual o seu endereço:</label> <br />
                <input type="text" name="endereço" placeholder="Rua A, nº 0" />
            </div>
            <div className="tel">
                <label htmlFor="telefone">Telefone comercial:</label> <br />
                <input type="text" name="telefone" placeholder="(00) 0 0000-0000" />
            </div>
            <div className="email">
                <label htmlFor="email">Email:</label> <br />
                <input type="email" name="email" placeholder="empresa@email.com" />
            </div>
            <div className="concluir">
            <button onclick="Concluir()">Concluir</button>
            </div>
        </div>
    );
};

export default CadastroEmpresa;
