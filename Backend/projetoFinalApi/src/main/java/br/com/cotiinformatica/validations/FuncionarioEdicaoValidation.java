package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.dto.EdicaoFuncionarioDTO;

public class FuncionarioEdicaoValidation {

	public static List<String> validate(EdicaoFuncionarioDTO dto) {
		List<String> mensagens = new ArrayList<String>();

		// verificando se o campo nome não foi preenchido
		if (dto.getCpf() == null || dto.getCpf().length() == 0) {
			mensagens.add("Por favor, informe o nome CPF.");
		}		
		else
		if (dto.getDataAdmissao() == null) {
			mensagens.add("Por favor, Data de Admissão.");
		}
		else
		if (dto.getIdEmpresa() == null) {
			mensagens.add("Por favor, Informe a Empresa.");
		}	
		else
		if (dto.getIdFuncionario() == null) {
			mensagens.add("Por favor, Informe o Id do Funcionário.");
		}			
		else
		if (dto.getNome() == null) {
			mensagens.add("Por favor, Informe o nome do Funcionário.");
		}			
		else
		if (dto.getSalario() == 0) {
			mensagens.add("Por favor, Informe o Salário do Funcionário.");
		}			
						
		
		return mensagens;
	}
}
