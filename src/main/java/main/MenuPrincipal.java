package main;

import java.util.Scanner;

import dao.AlunoDAO;
import dao.TreinoDAO;
import model.Aluno;
import model.Treino;

public class MenuPrincipal {

    public static void main(String[] args) {
        int option;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println(" ===== MENU PRINCIPAL =====");
                System.out.println("1 - Cadastrar Aluno \t 2 - Consultar Alunos");
                System.out.println("3 - Buscar Aluno por ID  4 - Atualizar Aluno");
                System.out.println("5 - Excluir Aluno");
                System.out.println("---------------------------");
                System.out.println("6 - Cadastrar Treino \t 7 - Buscar Treino por ID");
                System.out.println("8 - Atualizar Treino \t 9 - Excluir Treino");
                System.out.println("10 - Listar Treinos\n");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: \n");
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> {
                        System.out.print("Nome do Aluno: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF do Aluno: ");
                        String cpf = scanner.nextLine();
                        if (!cpf.matches("\\d{11}")) {
                            System.out.println("CPF inválido. Deve conter 11 dígitos.");
                            continue;
                        }
                        AlunoDAO alunoDAO = new AlunoDAO();
                        if (alunoDAO.verificarCpfExistente(cpf, 0)) {
                            System.out.println("CPF já cadastrado para outro aluno.");
                            continue;
                        }
                        System.out.print("Telefone do Aluno: ");
                        String telefone = scanner.nextLine();
                        System.out.print("Email do Aluno: ");
                        String email = scanner.nextLine();
                        System.out.print("Data de Nascimento do Aluno (dd/MM/yyyy): ");
                        String dataNascimentoStr = scanner.nextLine();
                        java.time.LocalDate dataNascimento;
                        try {
                            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            dataNascimento = java.time.LocalDate.parse(dataNascimentoStr, formatter);
                        } catch (java.time.format.DateTimeParseException e) {
                            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
                            continue;
                        }

                        Aluno aluno = new Aluno(0, nome, cpf, telefone, email, dataNascimento);
                        alunoDAO.inserirAluno(aluno);
                        System.out.println("Aluno cadastrado com sucesso!");
                    }
                    case 2 -> {
                        System.out.println("Consultar alunos: ");
                        AlunoDAO alunoDAO = new AlunoDAO();
                        TreinoDAO treinoDAO = new TreinoDAO();
                        for (Aluno aluno : alunoDAO.listarTodos()) {
                            System.out.println("ID: " + aluno.getId());
                            System.out.println("Nome: " + aluno.getNome());
                            System.out.println("CPF: " + aluno.getCpf());
                            System.out.println("Telefone: " + aluno.getTelefone());
                            System.out.println("Email: " + aluno.getEmail());
                            System.out.println("Data de Nascimento: " + aluno.getDataNascimento());
                            System.out.println("-----------------------------");
                            Treino treino = treinoDAO.buscarPorId(aluno.getId());
                            if (treino != null) {
                                System.out.println("Treino: " + treino.getDescricao());
                                System.out.println("Tipo: " + treino.getTipoTreino());
                                System.out.println("Data de Início: " + treino.getDataInicio());
                                System.out.println("-----------------------------");
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("Digite o ID do Aluno: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        AlunoDAO alunoDAO = new AlunoDAO();
                        Aluno aluno = alunoDAO.buscarPorId(id);
                        if (aluno != null) {
                            System.out.println("ID: " + aluno.getId());
                            System.out.println("Nome: " + aluno.getNome());
                            System.out.println("CPF: " + aluno.getCpf());
                            System.out.println("Telefone: " + aluno.getTelefone());
                            System.out.println("Email: " + aluno.getEmail());
                            System.out.println("Data de Nascimento: " + aluno.getDataNascimento());
                            System.out.println("-----------------------------");
                        } else {
                            System.out.println("Aluno não encontrado com o ID informado.");
                        }
                    }
                    case 4 -> {
                        System.out.print("Digite o ID do Aluno a ser atualizado: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        AlunoDAO alunoDAO = new AlunoDAO();
                        Aluno aluno = alunoDAO.buscarPorId(id);
                        if (aluno != null) {
                            System.out.print("Novo Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Novo CPF: ");
                            String cpf = scanner.nextLine();
                            if (!cpf.matches("\\d{11}")) {
                                System.out.println("CPF inválido. Deve conter 11 dígitos.");
                                continue;
                            }
                            if (alunoDAO.verificarCpfExistente(cpf, id)) {
                                System.out.println("CPF já cadastrado para outro aluno.");
                                continue;
                            }
                            System.out.print("Novo Telefone: ");
                            String telefone = scanner.nextLine();
                            System.out.print("Novo Email: ");
                            String email = scanner.nextLine();
                            System.out.print("Data de Nascimento do Aluno (dd/MM/yyyy): ");
                            String dataNascimentoStr = scanner.nextLine();
                            java.time.LocalDate dataNascimento;
                            try {
                                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                dataNascimento = java.time.LocalDate.parse(dataNascimentoStr, formatter);
                            } catch (java.time.format.DateTimeParseException e) {
                                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
                                continue;
                            }

                            aluno.setNome(nome);
                            aluno.setCpf(cpf);
                            aluno.setTelefone(telefone);
                            aluno.setEmail(email);
                            aluno.setDataNascimento(dataNascimento);

                            alunoDAO.atualizar(aluno);
                            System.out.println("Aluno atualizado com sucesso!");
                        } else {
                            System.out.println("Aluno não encontrado com o ID informado.");
                        }
                    }
                    case 5 -> {
                        System.out.print("Digite o ID do Aluno a ser excluído: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        AlunoDAO alunoDAO = new AlunoDAO();
                        Aluno aluno = alunoDAO.buscarPorId(id);
                        if (aluno != null) {
                            alunoDAO.excluir(id);
                            System.out.println("Aluno excluído com sucesso!");
                        } else {
                            System.out.println("Aluno não encontrado com o ID informado.");
                        }
                    }
                    case 6 -> {
                        System.out.print("ID do Aluno: ");
                        int aluno_id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Descrição do Treino: ");
                        String descricao = scanner.nextLine();
                        System.out.print("Tipo do Treino: ");
                        String tipo = scanner.nextLine();
                        System.out.println("Qual Durãção do Treino? (em minutos)");
                        int duracao = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Data de Início do Treino (dd/MM/yyyy): ");
                        String dataInicioStr = scanner.nextLine();
                        java.time.LocalDate dataInicio;
                        try {
                            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            dataInicio = java.time.LocalDate.parse(dataInicioStr, formatter);
                        } catch (java.time.format.DateTimeParseException e) {
                            System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
                            continue;
                        }

                        Treino treino = new Treino(0, aluno_id, duracao, descricao, tipo, dataInicio);
                        TreinoDAO treinoDAO = new TreinoDAO();
                        treinoDAO.salvar(treino);
                        System.out.println("Treino cadastrado com sucesso!");
                    }
                    case 7 -> {
                        System.out.print("Digite o ID do Treino: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        TreinoDAO treinoDAO = new TreinoDAO();
                        Treino treino = treinoDAO.buscarPorId(id);
                        if (treino != null) {
                            System.out.println("ID: " + treino.getId());
                            System.out.println("ID do Aluno: " + treino.getIdAluno());
                            System.out.println("Descrição: " + treino.getDescricao());
                            System.out.println("Tipo: " + treino.getTipoTreino());
                            System.out.println("Duração do treino " + treino.getDuracao_minutos());
                            System.out.println("Data de Início: " + treino.getDataInicio());
                            System.out.println("-----------------------------");
                        } else {
                            System.out.println("Treino não encontrado com o ID informado.");
                        }
                    }
                    case 8 -> {
                        System.out.print("Digite o ID do Treino a ser atualizado: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        TreinoDAO treinoDAO = new TreinoDAO();
                        Treino treino = treinoDAO.buscarPorId(id);
                        if (treino != null) {
                            System.out.print("Nova Descrição: ");
                            String descricao = scanner.nextLine();
                            System.out.print("Novo Tipo: ");
                            String tipo = scanner.nextLine();
                            System.out.print("Nova Duração do Treino (em minutos): ");
                            int duracao = scanner.nextInt();
                            scanner.nextLine();
                            treino.setDuracaoMinutos(duracao);
                            System.out.print("Data de Início do Treino (dd/MM/yyyy): ");
                            String dataInicioStr = scanner.nextLine();
                            java.time.LocalDate dataInicio;
                            try {
                                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                dataInicio = java.time.LocalDate.parse(dataInicioStr, formatter);
                            } catch (java.time.format.DateTimeParseException e) {
                                System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
                                continue;
                            }

                            treino.setDescricao(descricao);
                            treino.setTipoTreino(tipo);
                            treino.setDataInicio(dataInicio);

                            treinoDAO.atualizar(treino);
                            System.out.println("Treino atualizado com sucesso!");
                        } else {
                            System.out.println("Treino não encontrado com o ID informado.");
                        }
                    }
                    case 9 -> {
                        System.out.print("Digite o ID do Treino a ser excluído: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        TreinoDAO treinoDAO = new TreinoDAO();
                        Treino treino = treinoDAO.buscarPorId(id);
                        if (treino != null) {
                            treinoDAO.excluir(id);
                            System.out.println("Treino excluído com sucesso!");
                        } else {
                            System.out.println("Treino não encontrado com o ID informado.");
                        }
                    }
                    case 10 -> {
                        System.out.println("Listando Treinos: ");
                        TreinoDAO treinoDAO = new TreinoDAO();
                        for (Treino treino : treinoDAO.listarTodos()) {
                            System.out.println("ID: " + treino.getId());
                            System.out.println("ID do Aluno: " + treino.getIdAluno());
                            System.out.println("Descrição: " + treino.getDescricao());
                            System.out.println("Tipo: " + treino.getTipoTreino());
                            System.out.println("Duração do treino: " + treino.getDuracao_minutos() + " minutos");
                            System.out.println("Data de Início: " + treino.getDataInicio());
                            System.out.println("-----------------------------");
                        }
                    }

                    case 0 ->
                        System.out.println("Encerrando...");
                    default ->
                        System.out.println("Opção não é válida, por favor tente novamente.");
                }
            } while (option != 0);
        }
    }
}
