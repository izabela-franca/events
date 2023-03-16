package com.log.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.log.domain.exception.NegocioException;
import com.log.domain.model.Cliente;
import com.log.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	//Poderia ser injetado com Autowired, mas foi utilizado AllArgsConstructor
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		//Aplicar regras de negócio
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		//Aplicar regras de negócio
		clienteRepository.deleteById(clienteId);
	}
}