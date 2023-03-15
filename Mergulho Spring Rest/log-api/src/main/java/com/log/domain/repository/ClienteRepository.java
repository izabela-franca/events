package com.log.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	List<Cliente> findByNome(String nome);
}
