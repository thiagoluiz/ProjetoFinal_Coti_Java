package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dto.CadastroEmpresaDTO;
import br.com.cotiinformatica.dto.ConsultaEmpresaDTO;
import br.com.cotiinformatica.dto.EdicaoEmpresaDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.interfaces.IEmpresaRepository;
import br.com.cotiinformatica.validations.EmpresaCadastroValidation;

@Controller
public class EmpresaController {
	
	@Autowired
	private IEmpresaRepository _empresaRepository;

	private static final String ENDPOINT = "/api/empresas";

	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> InserirEmpresa(@RequestBody CadastroEmpresaDTO dto) {

		List<String> mensagens = new ArrayList<String>();
		try 
		{	
			if (_empresaRepository.findByCNPJ(dto.getCnpj()))
			{
				throw new Exception("O CNPJ informado já encontra-se cadastrado.");
			}
			
			if (_empresaRepository.findByRazaoSocial(dto.getRazaoSocial()))
			{
				throw new Exception("A Razão social informada já encontra-se cadastrada.");
			}
			
			if(mensagens.size() == 0) {
				mensagens = EmpresaCadastroValidation.validate(dto);
								
				Empresa empresa = new Empresa();
				empresa.setCnpj(dto.getCnpj());
				empresa.setNomeFantasia(dto.getNomeFantasia());
				empresa.setRazaoSocial(dto.getRazaoSocial());
	
				
				_empresaRepository.save(empresa);
				
				mensagens.add("Empresa cadastrada com sucesso.");
				
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
	
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<List<String>> EditarEmpresa(@RequestBody EdicaoEmpresaDTO dto) {
		List<String> mensagens = new ArrayList<String>();
		
		try {
			
			Empresa empresa = new Empresa();
			
			empresa.setIdEmpresa(dto.getIdEmpresa());
			empresa.setCnpj(dto.getCnpj());
			empresa.setNomeFantasia(dto.getNomeFantasia());
			empresa.setRazaoSocial(dto.getRazaoSocial());

			
			_empresaRepository.save(empresa);
			
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
	
	@RequestMapping(value = ENDPOINT + "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<List<String>> excluirEmpresa(@PathVariable("id") Integer id) {
		
		List<String> mensagens = new ArrayList<String>();
		
		try {
			
			Empresa empresa = _empresaRepository.ObterEmpresa(id);
			
			_empresaRepository.delete(empresa);
			
			mensagens.add("Empresa excluída com sucesso.");
			
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

	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@ResponseBody 
	public List<ConsultaEmpresaDTO> consultarEmpresa()
	{
		try
		{
			List<ConsultaEmpresaDTO> lista = new ArrayList<ConsultaEmpresaDTO>();
			
			for(Empresa empresa : _empresaRepository.findAll())
			{
				ConsultaEmpresaDTO dto = new ConsultaEmpresaDTO();
				dto.setCnpj(empresa.getCnpj());
				dto.setIdEmpresa(dto.getIdEmpresa());
				dto.setNomeFantasia(dto.getNomeFantasia());
				dto.setRazaoSocial(dto.getRazaoSocial());
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