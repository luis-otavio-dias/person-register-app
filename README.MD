# Sistema de Cadastro de Pessoas

## Sobre o projeto

Este projeto e uma aplicacao Java desktop com JavaFX para cadastro de pessoas. A arquitetura foi reorganizada em MVC, mantendo as funcionalidades existentes: captura de dados do formulario, armazenamento em memoria e exibicao em TableView.

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
				│   ├── Person.java
				│   └── PersonRepository.java
				├── styles/
				│   └── global.css
				└── view/
					└── PersonView.java
```

## Organizacao MVC

- model: `Person` e `PersonRepository`.
- view: `PersonView` concentra construcao da interface, Scene e Stage.
- controller: `PersonController` controla eventos de botoes e faz a ponte entre view e model.
- pacote default do projeto: contem apenas `Main.java`.

## Funcionalidades mantidas

- Cadastro com campos nome, CPF, email e telefone.
- Validacao de nome/CPF obrigatorios.
- Bloqueio de CPF duplicado.
- Exibicao dos registros em TableView na area de relatorios.
- Armazenamento em memoria.

## Como executar

```bash
mvn clean package
mvn javafx:run
```

## Observacoes

- O modulo abre `com.luisotaviodias.view` para inicializacao JavaFX.
- O modulo abre `com.luisotaviodias.model` para binding reflexivo da TableView (`PropertyValueFactory`).
