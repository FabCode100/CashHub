#tabela de cadastro do funcionario/dados dele
CREATE TABLE DadosFuncionario 
( 
 IDFuncionario INT PRIMARY KEY AUTO_INCREMENT,  
 NomeCompletoFuncionario varchar(20) PRIMARY KEY NOT NULL,  
 EmailFuncionario VARCHAR(20) NOT NULL UNIQUE,  
 SenhaFuncionario VARCHAR(20) NOT NULL UNIQUE,  
 Cidade VARCHAR(10) NOT NULL,  
 telefone_funcionario INT,  
 CepFuncionario INT NOT NULL,  
 Sexo enum('M','F')NOT NULL, 
 ContaFuncionario INT PRIMARY KEY, 
 AgenciaFuncionario INT PRIMARY KEY,
 PixFuncionario INT PRIMARY KEY NOT NULL  
); 

#tabela que armazena os dado da empresa.

CREATE TABLE DadosEmpresa 
( 
 NomeFantasia VARCHAR(30) PRIMARY KEY NOT NULL,  
 CNPJ INT NOT NULL,  
 EndereçoEmpresa VARCHAR(150),  
 Fundação DATE NOT NULL,  
 RegimeTributação INT NOT NULL,  
 Cidade VARCHAR(50) NOT NULL,  
 Cep INT NOT NULL,  
 País VARCHAR(10) NOT NULL DEFAULT 'Brasil',  
 Cidade VARCHAR(20) NOT NULL,  
 TelefoneComercial INT NOT NULL,  
 ContaEmpresa INT PRIMARY KEY, 
 AgenciaEmpresa INT PRIMARY KEY,
 Projetos VARCHAR(10),  
 PixEmpresa INT PRIMARY KEY NOT NULL,  
 UNIQUE (CNPJ,Cidade,Cidade)
); 

#armazena dados do representante

CREATE TABLE DadosRepresante 
( 
 NomeCompleto VARCHAR(20) NOT NULL,  
 Sexo enum('M','F') NOT NULL,  
 DataNascimento DATE NOT NULL,  
 CPF INT PRIMARY KEY,  
 Telefone INT NOT NULL,  
 Email VARCHAR(10) NOT NULL unique,  
 Senha VARCHAR(20) NOT NULL,  
 UNIQUE (Senha)
); 
