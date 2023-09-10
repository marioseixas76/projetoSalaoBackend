package br.com.projetosalao.infra;

public record DadosTokenJWT(String token, Long cd_pessoa, String tipoUsuario, String nomeUsuario) {

}
