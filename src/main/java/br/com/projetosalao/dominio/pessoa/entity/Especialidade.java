package br.com.projetosalao.dominio.pessoa.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "T_MSW_ESPECIALIDADE")
public class Especialidade {

    @Id
    @SequenceGenerator(name = "SQ_ESPECIALIDADE", sequenceName = "SQ_ESPECIALIDADE", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ESPECIALIDADE")
    @Column(name = "cd_especialidade")
    private Long id;

    @Column(name = "nm_especialidade")
    private String nome;

    public Long getId() {
        return id;
    }

    public Especialidade setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Especialidade setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Especialidade() {
    }

    public Especialidade(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Especialidade{" +
                "id=" + id +
                ", nome='" + nome + '\''  +
                '}';
    }
}
