# Sistema de Cadastro de Pessoas

## Sobre o projeto

Este projeto é uma aplicação Java desktop que demonstra um sistema simples de cadastro de pessoas usando JavaFX para a interface gráfica e um repositório em memória para armazenamento temporário.

Projeto desenvolvido como trabalho prático para a disciplina de Linguagens Comerciais, com o objetivo de servir como exemplo prático para: programação orientada a objetos, uso de JavaFX, validação básica de dados e organização de uma pequena aplicação modular em Java.

## Estrutura do projeto

```
.
├── pom.xml
├── README.MD
└── src/
	└── main/
		└── java/
			├── module-info.java
			└── com/luisotaviodias/
				├── App.java
				├── Person.java
				├── PersonRepository.java
				├── SystemInfo.java
				└── styles/
					└── global.css
```

## Como funciona

A aplicação abre uma janela JavaFX com um formulário para cadastro (nome, CPF, email, telefone) e uma tabela para listar os cadastros. O armazenamento é feito em memória por `PersonRepository` — não há persistência em banco de dados.

Principais fluxos:

- Preencher os campos e clicar em "Cadastrar" para adicionar um registro.
- A aplicação valida campos obrigatórios (`nome` e `cpf`) e impede CPF duplicado.
- A guia "Relatórios" mostra a listagem atual dos cadastros.

## Principais classes

- `com.luisotaviodias.App` — ponto de entrada e definição da interface JavaFX.
- `com.luisotaviodias.Person` — modelo de domínio que representa uma pessoa.
- `com.luisotaviodias.PersonRepository` — repositório em memória para salvar/listar pessoas.
- `com.luisotaviodias.SystemInfo` — utilitários e informações do sistema.
- `src/main/java/com/luisotaviodias/styles/global.css` — estilos para a interface JavaFX.

## Dependências e modularidade

O projeto usa Maven e é modularizado (`module-info.java`) com o módulo `com.luisotaviodias`. A dependência principal é `org.openjfx:javafx-controls`.

Veja `[pom.xml](pom.xml)` para detalhes do build e do plugin `javafx-maven-plugin` configurado para execução.

## Como compilar e executar

Recomendado: use o plugin Maven para JavaFX (já incluído no `pom.xml`).

Linux / macOS (com Maven instalado):

```bash
mvn clean package
mvn javafx:run
```

Windows (Prompt/PowerShell):

```powershell
mvn clean package
mvn javafx:run
```

Observação: o comando `mvn javafx:run` usa a configuração do `javafx-maven-plugin` e é a forma mais direta de executar a aplicação durante o desenvolvimento.

Executando a aplicação empacotada (opcional):

1. Gere o JAR:

```bash
mvn -DskipTests package
```

2. Execute com o JavaFX SDK no `module-path` (substitua `/path/to/javafx/lib` pelo caminho do seu SDK JavaFX):

```bash
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.graphics -m com.luisotaviodias/com.luisotaviodias.App
```

O uso do `--module-path` é necessário quando executar o JAR fora do plugin Maven, pois o JavaFX não faz parte da JDK padrão.

## Tratamento de validações

A aplicação valida presença de `nome` e `cpf` no formulário e exibe diálogos de alerta quando há campos incompletos ou CPF duplicado. Exceções internas são capturadas no `start()` e logadas via `printStackTrace()`.

## Exemplo de comportamento esperado

- Ao abrir, a janela mostra o formulário de cadastro.
- Preenchendo `Nome` e `CPF` corretamente e clicando em `Cadastrar` a pessoa é adicionada e uma mensagem de confirmação é exibida.
- Tentar cadastrar com CPF já existente mostra um alerta informando duplicidade.

## Cobertura dos requisitos do projeto

- Interface gráfica com JavaFX: atendido (`App.java`, `global.css`).
- Estrutura modular (`module-info.java`): atendido.
- Repositório em memória para cadastros: atendido (`PersonRepository.java`).
- Validações básicas e mensagens de alerta: atendido.
