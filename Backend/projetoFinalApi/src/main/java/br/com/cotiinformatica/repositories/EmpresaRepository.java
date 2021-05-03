package br.com.cotiinformatica.repositories;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.interfaces.IEmpresaRepository;

@Service
@Transactional
public class EmpresaRepository {

	@Autowired
	private IEmpresaRepository _empresaRepository;
	
	public void save(Empresa empresa) throws Exception {
		_empresaRepository.save(empresa);
	}

	public void delete(Empresa empresa) throws Exception {
		_empresaRepository.delete(empresa);
	}
	
	public Empresa findById(Integer id) throws Exception {
		return _empresaRepository.findById(id).get();
	}	
	
	
	public Empresa findByCNPJ(String cnpj) throws Exception
	{
		return _empresaRepository.findByCNPJ(cnpj);
	}
	
	public Empresa findByRazaoSocial(String razaoSocial) throws Exception 
	{
		return _empresaRepository.findByRazaoSocial(razaoSocial);
	}
	
	public Empresa ObterEmpresa(@Param("pIdEmpresa") Integer idEmpresa) throws Exception
	{
		return _empresaRepository.ObterEmpresa(idEmpresa);
	}
}
