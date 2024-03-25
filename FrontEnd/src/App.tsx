import React from "react";
import TelaInicial from "./components/TelaInicial";
import Index from "./components";
import Login from "./components/login";
import Cadastro from "./components/Cadrastro";
import CadastroEmpresa from "./components/CadrastroEmpresa";

export default function App(){
    return (
        <main>
            <Index />
            <Login />
            <Cadastro />
            <CadastroEmpresa />
            <TelaInicial />
        </main>
    );
}