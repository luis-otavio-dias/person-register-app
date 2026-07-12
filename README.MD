# Sistema de Cadastro de Pessoas

## Sobre o projeto

Este projeto e uma aplicacao Java desktop com JavaFX para cadastro de pessoas. A arquitetura foi reorganizada em MVC e agora conta com persistencia em banco de dados H2 atraves de JDBC, substituindo o armazenamento em memoria. O sistema inclui captura de dados do formulario, validacoes e exibicao em TableView.

## Estrutura do projeto

```text
.
├── pom.xml
├── README.MD
└── src/
	└── main/
		└── java/
			├── module-info.java
			└── com/luisotaviodias/
				├── Main.java
				├── controller/
				│   └── PersonController.java
				├── model/
				│   ├── ConexaoBD.java
				│   ├── Person.java
				│   └── PersonRepository.java
				├── styles/
				│   └── global.css
				└── view/
					└── PersonView.java
```

## Organizacao MVC

- model: `Person`, `PersonRepository` (DAO) e `ConexaoBD` (Fabrica de conexoes JDBC e DDL do H2).
- view: `PersonView` concentra construcao da interface, Scene e Stage.
- controller: `PersonController` controla eventos de botoes e faz a ponte entre view e model.
- pacote default do projeto: contem apenas `Main.java`.

## Funcionalidades

- Cadastro com campos nome, CPF, email e telefone.
- Validacao de nome/CPF obrigatorios.
- Bloqueio de CPF duplicado, buscando direto no banco de dados.
- Exibicao dos registros em TableView na area de relatorios.
- Persistencia de dados com banco de dados embutido (H2) via API JDBC e instrucoes SQL (DML e DDL).

## Como executar

```bash
mvn clean package
mvn javafx:run
```

## Observacoes

- O modulo abre `com.luisotaviodias.view` para inicializacao JavaFX.
- O modulo abre `com.luisotaviodias.model` para binding reflexivo da TableView (`PropertyValueFactory`).
- O modulo requer `java.sql` para suportar as operacoes de banco de dados via JDBC.
