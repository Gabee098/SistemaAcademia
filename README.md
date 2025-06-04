
# AcademiaSystem

Sistema de gerenciamento para uma academia, implementado em Java com JDBC e MySQL, utilizando Maven como gerenciador de dependências.

## 🚀 Tecnologias Utilizadas

- **Java 24 (com preview features)**
- **Maven** - automação de build
- **MySQL** - banco de dados relacional
- **JDBC** - conexão com o banco de dados

---

## Estrutura do Projeto

- **Modelos**:
  - `Aluno.java` - representa um aluno.
  - `Treino.java` - representa um treino.

- **DAOs**:
  - `AlunoDAO.java` - operações CRUD para alunos.
  - `TreinoDAO.java` - operações CRUD para treinos.

- **Outros**:
  - `MenuPrincipal.java` - interface de interação via console.
  - `ConnectionFactory.java` - gerenciamento da conexão com o banco de dados.

---

## Funcionalidades

- Cadastrar novos alunos.
- Listar todos os alunos cadastrados.
- Atualizar dados de alunos.
- Remover alunos.
- Cadastrar treinos para alunos.
- Listar todos os treinos.
- Atualizar ou remover treinos.

---

## Configuração do Ambiente

### Pré-requisitos

- Java 24 instalado.
- MySQL Server e Workbench.
- Maven 3.6+ instalado.

### Configuração do Banco de Dados

1. Abra `academia.mwb` no MySQL Workbench.
2. Gere o script SQL e execute para criar as tabelas no banco.
3. Ajuste as credenciais de conexão em `ConnectionFactory.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/academia";
private static final String USUARIO = "seu_usuario";
private static final String SENHA = "sua_senha";
```

---

## Como Compilar e Executar

### Compilar:

```bash
mvn clean package
```

### Executar:

```bash
mvn exec:java
```

> Obs.: O projeto utiliza **features em preview** do Java 24. Pode ser necessário adicionar `--enable-preview` na execução.

---

## Uso

1. Ao iniciar o sistema, o menu principal será exibido.
2. Selecione as opções para gerenciar alunos e treinos.
3. Informe os dados solicitados.

---

## Banco de Dados

- Modelo criado com MySQL Workbench (`academia.mwb`).
- Tabelas:
  - `alunos`: id, nome, idade, plano.
  - `treinos`: id, aluno_id, descricao.

---

## Licença

Projeto de uso acadêmico, livre para fins educacionais.