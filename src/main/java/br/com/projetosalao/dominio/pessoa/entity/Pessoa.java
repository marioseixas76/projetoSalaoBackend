package br.com.projetosalao.dominio.pessoa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "T_MSW_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    @SequenceGenerator(name = "SQ_PESSOA", sequenceName = "SQ_PESSOA", allocationSize = 1, initialValue = 1)
    @Column(name = "cd_pessoa")
    private Long cd_pessoa;

    @Column(name = "nm_pessoa")
    private String nome;

    @Column(name = "nr_cep")
    private String cep;

    @Column(name = "ds_logradouro")
    private String logradouro;

    @Column(name = "ds_complemento")
    private String complemento;

    @Column(name = "nm_bairro")
    private String bairro;

    @Column(name = "nm_cidade")
    private String cidade;

    @Column(name = "nm_estado")
    private String estado;

    @Column(name = "nr_telefone")
    private String telefone;

    @Column(name = "nr_telefone_recado")
    private String telefoneRecado;

    @Column(name = "email", unique = true, nullable = true)
    private String email;

    @Column(name = "whats_app")
    private boolean whatsApp;

    @Column(name = "status")
    private String status;

    @Column(name = "dt_cadastro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastro;

    @Column(name = "nr_cnpj", unique = true)
    private String CNPJ;

    @Column(name = "nm_cpf", unique = true)
    private String CPF;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;


    public Long getCd_pessoa() {
        return cd_pessoa;
    }

    public Pessoa setCd_pessoa(Long cd_pessoa) {
        this.cd_pessoa = cd_pessoa;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Pessoa setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Pessoa setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Pessoa setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Pessoa setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Pessoa setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getEstado() {
        return estado;
    }

    public Pessoa setEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Pessoa setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getTelefoneRecado() {
        return telefoneRecado;
    }

    public Pessoa setTelefoneRecado(String telefoneRecado) {
        this.telefoneRecado = telefoneRecado;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Pessoa setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isWhatsApp() {
        return whatsApp;
    }

    public Pessoa setWhatsApp(boolean whatsApp) {
        this.whatsApp = whatsApp;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Pessoa setStatus(String status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Pessoa setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public Pessoa setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
        return this;
    }

    public String getCPF() {
        return CPF;
    }

    public Pessoa setCPF(String CPF) {
        this.CPF = CPF;
        return this;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Pessoa setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public Pessoa() {
    }

    public Pessoa(Long cd_pessoa, String nome, String cep, String logradouro, String complemento, String bairro, String cidade, String estado, String telefone, String telefoneRecado, String email, boolean whatsApp, String status, LocalDateTime dataCadastro, String CNPJ, String CPF, LocalDate dataNascimento) {
        this.cd_pessoa = cd_pessoa;
        this.nome = nome;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.telefoneRecado = telefoneRecado;
        this.email = email;
        this.whatsApp = whatsApp;
        this.status = status;
        this.dataCadastro = dataCadastro;
        this.CNPJ = CNPJ;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "cd_pessoa=" + cd_pessoa +
                ", nome='" + nome + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", telefone='" + telefone + '\'' +
                ", telefoneRecado='" + telefoneRecado + '\'' +
                ", email='" + email + '\'' +
                ", whatsApp=" + whatsApp +
                ", status='" + status + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", CNPJ='" + CNPJ + '\'' +
                ", CPF='" + CPF + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }


}
