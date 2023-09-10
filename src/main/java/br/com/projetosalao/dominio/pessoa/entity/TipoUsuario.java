package br.com.projetosalao.dominio.pessoa.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "T_MSW_T_USUARIO")
public class TipoUsuario {

    @Id
    @SequenceGenerator(name = "SQ_TIPO_USUARIO", sequenceName = "SQ_TIPO_USUARIO", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_USUARIO")
    @Column(name = "cd_tipo_usuario")
    private Long id;
    @Column(name = "nm_tipo_usuario")
    private String tipo_usuario;


    public Long getId() {
        return id;
    }

    public TipoUsuario setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public TipoUsuario setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
        return this;
    }

    public TipoUsuario() {
    }

    public TipoUsuario(Long id, String tipo_usuario) {
        this.id = id;
        this.tipo_usuario = tipo_usuario;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" +
                "id=" + id +
                ", tipo_usuario='" + tipo_usuario + '\'' +
                '}';
    }
}
