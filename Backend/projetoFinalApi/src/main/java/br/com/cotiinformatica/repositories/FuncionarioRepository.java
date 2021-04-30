package br.com.cotiinformatica.repositories;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.interfaces.IFuncionarioRepository;

@Service
@Transactional
public class FuncionarioRepository {
	
	@Autowired
	private IFuncionarioRepository _funcionarioRepository;
	
	// método para cadastrar / atualizar conta no banco de dados
	public void save(Funcionario funcionario) throws Exception {
		_funcionarioRepository.save(funcionario);
	}

	// método para excluir uma conta
	public void delete(Funcionario funcionario) throws Exception {
		_funcionarioRepository.delete(funcionario);
	}
	
//	//método para buscar 1 conta pelo id
//	public Funcionario findById(Integer id) throws Exception {
//		return _funcionarioRepository.findById(id).get();
//	}	
//	
//	public boolean findByCpf(String cpf) throws Exception
//	{
//		return _funcionarioRepository.findByCPF(cpf);
//	}

}
