# Desafio PAN Backend

Esta aplicação segue a **Arquitetura Hexagonal** (Ports and Adapters) e é uma API REST desenvolvida com **Spring Boot** e **Java 17**. Utiliza **H2** como banco de dados em memória e permite o gerenciamento de clientes e localidades (endereço via CEP, estados e municípios).

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** (Versão 2.x)
- **Banco de Dados H2** (em memória)
- **Maven** (Para build e dependências)

## Arquitetura

O projeto segue a **Arquitetura Hexagonal**, separando a lógica de negócios das dependências externas (como APIs externas e banco de dados) e facilitando a testabilidade e manutenção da aplicação.

### Principais Componentes

1. **Controller (Adapters)**: Responsáveis por expor os endpoints REST e delegar as ações para os serviços apropriados.
2. **Services (Ports)**: Definem a lógica de negócios da aplicação e interagem com os adaptadores.
3. **Models**: Objetos que representam os dados, como `Client`, `State`, `Municipality`, entre outros.

## Funcionalidades

- **Cadastro de Clientes**
- **Consulta de Clientes por CPF ou ID**
- **Atualização de Endereço de Clientes**
- **Consulta de Endereço via CEP**
- **Consulta de Estados e Municípios**

## Pré-requisitos

Antes de rodar a aplicação, verifique se você possui:

- **Java 17** ou superior instalado
- **Maven** instalado no seu ambiente
- **IDE** ou terminal para rodar a aplicação

## Acessando o Swagger

Para facilitar a interação com a API e testar os endpoints de maneira visual, a aplicação inclui o Swagger UI.

### URL do Swagger UI

Quando a aplicação estiver rodando, você pode acessar o Swagger UI através da seguinte URL:

- **localhost:8080/swagger-ui/index.html#/v3/api-docs**


## Configuração

### 1. Clonar o Repositório

Primeiramente, clone o repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git


