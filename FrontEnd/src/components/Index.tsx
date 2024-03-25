import React from "react";
import logo from "../assets/logo.png";
import nome from "./assets/nome.png";
import "./index.css";

const Index: React.FC = () => {
    return (
        <div id="logos">
            <img src={logo} alt="logo do Cash Hub" /> <br />
            <img src={nome} alt="nome do Cash Hub" />
        </div>
    );
};

export default Index;
