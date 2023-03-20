package com.log.api.model.input;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;

import lombok.Getter;

@Getter
@Service
public class OcorrenciaInput {

	@NotBlank
	private String descricao;
}
