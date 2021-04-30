package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.dto.CadastroFuncionarioDTO;

public class FuncionarioCadastroValidation {

	public static List<String> validate(CadastroFuncionarioDTO dto) {
		List<String> mensagens = new ArrayList<String>();

		// verificando se o campo nome não foi preenchido
		if (dto.getCpf() == null || dto.getCpf().length() == 0) {
			mensagens.add("Por favor, informe o nome CPF.");
		}		
		return mensagens;
	}

}
