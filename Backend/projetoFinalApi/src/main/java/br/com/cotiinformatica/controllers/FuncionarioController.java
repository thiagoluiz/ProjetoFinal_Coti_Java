package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dto.CadastroFuncionarioDTO;
import br.com.cotiinformatica.dto.ConsultaFuncionarioDTO;
import br.com.cotiinformatica.dto.EdicaoFuncionarioDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.interfaces.IEmpresaRepository;
import br.com.cotiinformatica.interfaces.IFuncionarioRepository;
import br.com.cotiinformatica.validations.FuncionarioCadastroValidation;
import br.com.cotiinformatica.validations.FuncionarioEdicaoValidation;

@Controller
public class FuncionarioController {

	@Autowired
	private IFuncionarioRepository _funcionarioRepository;
	
	@Autowired
	private IEmpresaRepository _empresaRepository;

	private static final String ENDPOINT = "/api/funcionarios";
	
	@CrossOrigin
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> InserirFuncionario(@RequestBody CadastroFuncionarioDTO dto) {

		List<String> mensagens = new ArrayList<String>();
		try 
		{
			if(_funcionarioRepository.findByCPF(dto.getCpf()) != null) {					
				throw new Exception("O CPF informado já encontra-se cadastrado.");
			}
						
			mensagens = FuncionarioCadastroValidation.validate(dto);
			
			if(mensagens.size() == 0) {
				
				Empresa empresa = _empresaRepository.ObterEmpresa(dto.getIdEmpresa());	
				
				if (empresa == null)
				{
					throw new Exception("A empresa informada não foi cadastrada.");
				}
				
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

	@CrossOrigin
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<List<String>> EditarFuncionario(@RequestBody EdicaoFuncionarioDTO dto)
	{
		List<String> mensagens = new ArrayList<String>();
		
		try {
									
			mensagens = FuncionarioEdicaoValidation.validate(dto);			
			
			Empresa empresa = _empresaRepository.ObterEmpresa(dto.getIdEmpresa());	
			
			Funcionario funcionario = new Funcionario();
			
			funcionario.setIdFuncionario(dto.getIdFuncionario());
			funcionario.setCpf(dto.getCpf());
			funcionario.setDataAdmissao(dto.getDataAdmissao());
			funcionario.setEmpresa(empresa);
			funcionario.setNome(dto.getNome());
			funcionario.setSalario(dto.getSalario());
			
			_funcionarioRepository.save(funcionario);
			
			mensagens.add("Empresa com sucesso.");
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(mensagens);	
		}
		catch(Exception e) {
			
			mensagens.add("Ocorreu um erro: " + e.getMessage());
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(mensagens);		
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = ENDPOINT + "/{id}", method = RequestMethod.DELETE)
	@ResponseBody	
	public ResponseEntity<List<String>> ExcluirFuncionario(@PathVariable("id") Integer id)
	{
		
		List<String> mensagens = new ArrayList<String>();
		
		try {
			
			Funcionario funcionario = _funcionarioRepository.ObterFuncionario(id);
			
			if (funcionario == null)
			{
				throw new Exception("A Funcionário informado não foi encontrado.");					
			}
			
			_funcionarioRepository.delete(funcionario);
			
			mensagens.add("Funcionario excluído com sucesso.");
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(mensagens);	
		}
		catch(Exception e) {
			
			mensagens.add("Ocorreu um erro: " + e.getMessage());
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(mensagens);		
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@ResponseBody 	
	public List<ConsultaFuncionarioDTO> consultarFuncionario()
	{
		try
		{
			List<ConsultaFuncionarioDTO> lista = new ArrayList<ConsultaFuncionarioDTO>();
			
			for(Funcionario funcionario : _funcionarioRepository.findAll())
			{
				ConsultaFuncionarioDTO dto = new ConsultaFuncionarioDTO();
				dto.setCpf(funcionario.getCpf());
				dto.setDataAdmissao(funcionario.getDataAdmissao());
				dto.setIdFuncionario(funcionario.getIdFuncionario());
				dto.setNome(funcionario.getNome());
				dto.setSalario(funcionario.getSalario());
				dto.setEmpresa(funcionario.getEmpresa());
				lista.add(dto);
			}
			
			return lista;
			
		}
		catch (Exception e)
		{
			return null;
		}
	}	
}
