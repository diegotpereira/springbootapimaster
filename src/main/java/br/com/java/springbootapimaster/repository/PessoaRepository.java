package br.com.java.springbootapimaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.java.springbootapimaster.entity.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}
