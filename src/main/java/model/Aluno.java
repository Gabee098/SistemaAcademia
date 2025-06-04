package model;

import java.time.LocalDate;

public class Aluno {

    private int id;
    private String nome, cpf, telefone, email;
    private LocalDate dataNascimento;
    public Aluno(int idAluno, String nome, String cpf, String telefone, String email, LocalDate dataNascimento) {
        this.id = idAluno;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public LocalDate getDataNascimento(){
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }
  
      
    
    
    
    
    
    
    
  }