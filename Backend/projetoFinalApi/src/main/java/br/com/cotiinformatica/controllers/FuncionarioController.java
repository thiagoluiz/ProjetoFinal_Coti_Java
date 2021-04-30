package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dto.CadastroFuncionarioDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.interfaces.IEmpresaRepository;
import br.com.cotiinformatica.interfaces.IFuncionarioRepository;
import br.com.cotiinformatica.validations.FuncionarioCadastroValidation;

@Controller
public class FuncionarioController {

	@Autowired
	private IFuncionarioRepository _funcionarioRepository;
	
	@Autowired
	private IEmpresaRepository _empresaRepository;

	private static final String ENDPOINT = "/api/funcionarios";

	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> inserirFuncionario(@RequestBody CadastroFuncionarioDTO dto) {

		List<String> mensagens = new ArrayList<String>();
		try 
		{
			if(!_funcionarioRepository.findByCPF(dto.getCpf())) {					
				throw new Exception("O email informado já encontra-se cadastrado.");
			}
						
			if(mensagens.size() == 0) {
				mensagens = FuncionarioCadastroValidation.validate(dto);
				
				Empresa empresa = _empresaRepository.ObterEmpresa(dto.getIdEmpresa());	
				
				Funcionario funcionario = new Funcionario();
				funcionario.setCpf(dto.getCpf());
				funcionario.setDataAdmissao(dto.getDataAdmissao());
				funcionario.setEmpresa(empresa);
				funcionario.setNome(dto.getNome());
				funcionario.setSalario(dto.getSalario());
				
				_funcionarioRepository.save(funcionario);
				
				mensagens.add("Funcionario cadastrado com sucesso.");
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(mensagens);				
				
			}
			else 
			{
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagens);				
			}
			
		} 
		catch (Exception e) 
		{
			mensagens.add("Ocorreu um erro: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagens);
		}
	}

}
