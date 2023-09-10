package br.com.projetosalao.dominio.pessoa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "T_MSW_AGENDA")
public class Agenda {

    @Id
    @SequenceGenerator(name = "SQ_AGENDA", sequenceName = "SQ_AGENDA", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AGENDA")
    @Column(name = "cd_agendamento")
    private Long id;

    @Column(name = "cd_cliente")
    private Long cd_cliente;

    @Column(name = "cd_profissional")
    private Long cd_profissional;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "horario")
    private LocalTime horario;

    @Column(name = "cd_servico")
    private Long cd_servico;

    @Column(name = "vl_servico")
    private BigDecimal vl_servico;


    public Long getId() {
        return id;
    }

    public Agenda setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCd_cliente() {
        return cd_cliente;
    }

    public Agenda setCd_cliente(Long cd_cliente) {
        this.cd_cliente = cd_cliente;
        return this;
    }

    public Long getCd_profissional() {
        return cd_profissional;
    }

    public Agenda setCd_profissional(Long cd_profissional) {
        this.cd_profissional = cd_profissional;
        return this;
    }

    public LocalDate getData() {
        return data;
    }

    public Agenda setData(LocalDate data) {
        this.data = data;
        return this;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public Agenda setHorario(LocalTime horario) {
        this.horario = horario;
        return this;
    }

    public Long getCd_servico() {
        return cd_servico;
    }

    public Agenda setCd_servico(Long cd_servico) {
        this.cd_servico = cd_servico;
        return this;
    }

    public BigDecimal getVl_servico() {
        return vl_servico;
    }

    public Agenda setVl_servico(BigDecimal vl_servico) {
        this.vl_servico = vl_servico;
        return this;
    }

    public Agenda() {
    }

    public Agenda(Long id, Long cd_cliente, Long cd_profissional, LocalDate data, LocalTime horario, Long cd_servico, BigDecimal vl_servico) {
        this.id = id;
        this.cd_cliente = cd_cliente;
        this.cd_profissional = cd_profissional;
        this.data = data;
        this.horario = horario;
        this.cd_servico = cd_servico;
        this.vl_servico = vl_servico;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", cd_cliente=" + cd_cliente +
                ", cd_profissional=" + cd_profissional +
                ", data=" + data +
                ", horario=" + horario +
                ", cd_servico=" + cd_servico +
                ", vl_servico=" + vl_servico +
                '}';
    }
}
