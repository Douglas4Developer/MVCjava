AUTHOR: DOUGLAS SOARES DE SOUZA FERREIRA
### README para Projeto sem Hibernate

```markdown
Descrição
Este projeto é uma aplicação web de gerenciamento de projetos e tarefas. Ele permite aos usuários visualizar, adicionar, editar e excluir projetos e suas respectivas tarefas. Cada projeto pode conter uma ou mais tarefas associadas.

Tecnologias Utilizadas
Java Server Faces (JSF): Utilizado para a camada de apresentação.
RichFaces: Usado para aprimorar a interface do usuário com componentes ricos.
Spring Framework: Utilizado para a camada de negócio e injeção de dependência.

Requisitos Funcionais
Cadastro de Projetos: Permitir aos usuários adicionar novos projetos, incluindo os campos de título, descrição e data de início.
Listagem de Projetos: Exibir uma lista de todos os projetos cadastrados, com opções para visualizar, editar e excluir cada um.
Cadastro de Tarefas: Permitir aos usuários adicionar novas tarefas para um projeto existente, incluindo os campos de título, descrição, prioridade (Muito Alta, Alta, Baixa e Muito Baixa) e estimativa em horas.
Listagem de Tarefas: Exibir uma lista de todas as tarefas associadas a um projeto, com opções para visualizar, editar e excluir cada uma.

Requisitos Técnicos
Padrão MVC: Utilização do padrão de projeto MVC (Model-View-Controller) para estruturar a aplicação.
RichFaces: Utilização de RichFaces para aprimorar a interface do usuário com componentes ricos.
Spring Integration: Configuração do Spring para realizar a injeção de dependências nos beans gerenciados pelo JSF.
JDBC: Utilização de JDBC para realizar operações no banco de dados.dos.

## Estrutura do Projeto

- `src/main/java`: Contém o código-fonte Java.
  - `com.doug.mvcjava.benas`: Onde se encontra nossos models.
  - `com.doug.mvcjava.controller`: Controladores JSF.
  - `com.doug.mvcjava.dao`: Acesso aos dados utilizando JDBC.
 

- `src/main/webapp`: Contém os arquivos web.
  - `resources`: Arquivos estáticos como CSS e imagens.
  - `WEB-INF`: Configurações do projeto.
    - `pages`: Páginas JSF. XHTML.
    - `applicationContext.xml`: Configurações do Spring.
    - `faces-config.xml`: Configurações do JSF.
    - `web.xml`: Configurações do servlet.

## Tecnologias Utilizadas

- Java 8
- Spring Framework
- JDBC
- JavaServer Faces (JSF)
- RichFaces
- MySQL

## Configuração do Ambiente

1. Instale o Java 8.
2. Utilizando o ambiente IntelliJ importe o projeto
3. Configure um servidor Apache Tomcat. VERSÃO 9 

## Configuração do Banco de Dados

1. Crie um banco de dados MySQL:
   ```sql
   CREATE DATABASE desafiojava;
username= root
senha= 326598

//SCRIPTS MYSQL
CREATE TABLE projeto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_inicio DATE
);

CREATE TABLE tarefa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    prioridade VARCHAR(50),
    estimativa_horas INT,
    projeto_id BIGINT,
    FOREIGN KEY (projeto_id) REFERENCES projeto(id)
);
