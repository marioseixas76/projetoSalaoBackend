package br.com.projetosalao.dominio.pessoa.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "T_MSW_CLIENTE")

public class Cliente extends Pessoa {

    public Cliente() {
    }

    @Override
    public String toString() {
        return "Cliente{} " + super.toString();
    }
}
