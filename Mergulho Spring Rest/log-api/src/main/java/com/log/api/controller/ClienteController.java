package com.log.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Izabela França");
		cliente1.setEmail("izabela@email.com");
		cliente1.setTelefone("16 988194077");

		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Diego Doce");
		cliente2.setEmail("diego@email.com");
		cliente2.setTelefone("19 981475420");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
