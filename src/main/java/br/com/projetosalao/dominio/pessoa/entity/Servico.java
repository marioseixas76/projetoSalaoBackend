package br.com.projetosalao.dominio.pessoa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_MSW_SERVICO")
public class Servico {

    @Id
    @SequenceGenerator(name = "SQ_SERVICO", sequenceName = "SQ_SERVICO", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SERVICO")
    @Column(name = "cd_servico")
    private Long id;

    @Column(name = "ds_servico")
    private String ds_servico;

    @Column(name = "vl_servico")
    private BigDecimal vl_servico;

    @Column(name = "status")
    private String status;


    public Long getId() {
        return id;
    }

    public Servico setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDs_servico() {
        return ds_servico;
    }

    public Servico setDs_servico(String ds_servico) {
        this.ds_servico = ds_servico;
        return this;
    }

    public BigDecimal getVl_servico() {
        return vl_servico;
    }

    public Servico setVl_servico(BigDecimal vl_servico) {
        this.vl_servico = vl_servico;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Servico setStatus(String status) {
        this.status = status;
        return this;
    }

    public Servico() {
    }

    public Servico(Long id, String ds_servico, BigDecimal vl_servico, String status) {
        this.id = id;
        this.ds_servico = ds_servico;
        this.vl_servico = vl_servico;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", ds_servico='" + ds_servico + '\'' +
                ", vl_servico=" + vl_servico +
                ", status='" + status + '\'' +
                '}';
    }
}
