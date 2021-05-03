package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.dto.EdicaoEmpresaDTO;

public class EmpresaEdicaoValidation {

	public static List<String> validate(EdicaoEmpresaDTO dto) {
		List<String> mensagens = new ArrayList<String>();

		// verificando se o campo nome não foi preenchido
		if (dto.getNomeFantasia() == null || dto.getNomeFantasia().length() == 0) {
			mensagens.add("Por favor, informe o nome Fantasia.");
		}
		// verificando o minimo e máximo de caracteres do campo nome do usuario
		else if (dto.getNomeFantasia().trim().length() < 6 || dto.getNomeFantasia().trim().length() > 150) {
			mensagens.add("Por favor, informe o nome Fantasia contendo de 6 a 150 caracteres.");
		}

		// verificando se o campo nome não foi preenchido
		if (dto.getRazaoSocial() == null || dto.getRazaoSocial().length() == 0) {
			mensagens.add("Por favor, informe a Razão Social.");
		}
		// verificando o minimo e máximo de caracteres do campo nome do usuario
		else if (dto.getRazaoSocial().trim().length() < 6 || dto.getRazaoSocial().trim().length() > 150) {
			mensagens.add("Por favor, informe a Razão Social contendo de 6 a 150 caracteres.");
		}

		// verificando se o campo nome não foi preenchido
		if (dto.getCnpj() == null || dto.getCnpj().length() == 0) {
			mensagens.add("Por favor, informe o CNPJ.");
		} else if (dto.getCnpj().trim().length() < 11) {
			mensagens.add("Por favor, informe o nome CNPJ contendo de 11 caracteres.");
		}
		
		return mensagens;
	}
}
