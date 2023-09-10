package br.com.projetosalao.repository;

import br.com.projetosalao.dominio.pessoa.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
