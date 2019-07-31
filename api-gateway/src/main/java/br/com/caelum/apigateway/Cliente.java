package br.com.caelum.apigateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ClienteDto {

	private String nome;
	private String cpf;
	private String email;
	private String telefone;

}
