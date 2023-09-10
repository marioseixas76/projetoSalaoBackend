package br.com.projetosalao.dominio.pessoa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "T_MSW_PROFISSIONAL")
public class Profissional extends Pessoa {

    @Column(name = "cd_especialidade")
    private Long cd_especialidade;


    public Long getCd_especialidade() {
        return cd_especialidade;
    }

    public Profissional setCd_especialidade(Long cd_especialidade) {
        this.cd_especialidade = cd_especialidade;
        return this;
    }

    public Profissional() {
    }

    public Profissional(Long cd_especialidade) {
        this.cd_especialidade = cd_especialidade;
    }

    @Override
    public String toString() {
        return "Profissional{" +
                "cd_especialidade=" + cd_especialidade +
                "} " + super.toString();
    }
}
