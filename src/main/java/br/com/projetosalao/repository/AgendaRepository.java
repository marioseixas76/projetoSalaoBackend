package br.com.projetosalao.repository;

import br.com.projetosalao.dominio.pessoa.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
