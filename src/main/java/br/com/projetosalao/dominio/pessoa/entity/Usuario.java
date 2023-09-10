package br.com.projetosalao.dominio.pessoa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "T_MSW_USUARIO")
public class Usuario extends Pessoa implements UserDetails {

    @Column(name = "senha")
    private String senha;

    @Column(name = "dt_ultimo_acesso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dt_ultimo_acesso;

    @Column(name = "authority")
    private String authority;

    @Column(name = "tentativas_login")
    private Integer tentativas_login;

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getAuthority() {
        return authority;
    }

    public Usuario setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public LocalDate getDt_ultimo_acesso() {
        return dt_ultimo_acesso;
    }

    public Usuario setDt_ultimo_acesso(LocalDate dt_ultimo_acesso) {
        this.dt_ultimo_acesso = dt_ultimo_acesso;
        return this;
    }

    public Integer getTentativas_login() {
        return tentativas_login;
    }

    public Usuario setTentativas_login(Integer tentativas_login) {
        this.tentativas_login = tentativas_login;
        return this;
    }

    public Usuario() {
    }

    public Usuario(String senha, LocalDate dt_ultimo_acesso, String authority, Integer tentativas_login) {
        this.senha = senha;
        this.dt_ultimo_acesso = dt_ultimo_acesso;
        this.authority = authority;
        this.tentativas_login = tentativas_login;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "senha='" + senha + '\'' +
                ", dt_ultimo_acesso=" + dt_ultimo_acesso +
                ", authority='" + authority + '\'' +
                ", tentativas_login='" + tentativas_login + '\'' +
                "} " + super.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
        authorities.add(new SimpleGrantedAuthority("ROLE_GERENTE"));

        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void incrementarTentativasLogin() {
        if (tentativas_login == null) {
            tentativas_login = 1;
        } else {
            tentativas_login++;
        }
    }
}
