package br.com.projetosalao.repository;

import br.com.projetosalao.dominio.pessoa.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
}
