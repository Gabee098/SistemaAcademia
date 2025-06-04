package model;

import java.time.LocalDate;

public class Treino{


    private int id, idAluno, duracao_minutos;
        private String TipoTreino, descricao;
        private LocalDate DataInicio;
public Treino(int id, int idAluno, int duracao_minutos, String TipoTreino, String descricao, LocalDate DataInicio) {
            this.id = id;
            this.idAluno = idAluno;
            this.duracao_minutos = duracao_minutos;
            this.TipoTreino = TipoTreino;
            this.descricao = descricao;
            this.DataInicio = DataInicio;
        }
      public int getId(){
            return id;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getIdAluno(){
            return idAluno;
        }
        public void setIdAluno(int idAluno){
            this.idAluno = idAluno;
        }
        public int getDuracao_minutos(){
            return this.duracao_minutos;
        }
        public void setDuracaoMinutos(int duracaoMinutos){
            this.duracao_minutos = duracaoMinutos;
        }
        public String getTipoTreino(){
            return TipoTreino;
        }
        public void setTipoTreino(String TipoTreino){
            this.TipoTreino = TipoTreino;
        }
        public String getDescricao(){
            return descricao;
        }
        public void setDescricao(String descricao){
            this.descricao = descricao;
        }
        public LocalDate getDataInicio(){
            return DataInicio;
        }
        public void setDataInicio(LocalDate DataInicio){
            this.DataInicio = DataInicio;
        }

      
    }

      