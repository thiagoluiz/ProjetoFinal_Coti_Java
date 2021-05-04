package br.com.cotiinformatica.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Funcionario;

public interface IFuncionarioRepository extends CrudRepository<Funcionario, Integer>  {
	@Query("select f from Funcionario f where f.cpf=:pCPF ")
	public Funcionario findByCPF(@Param("pCPF") String cpf);
	
	@Query("select f from Funcionario f where f.idFuncionario=:pIdFuncionario ")
	public Funcionario ObterFuncionario(@Param("pIdFuncionario") Integer id);

	@Query("select count(*) from Funcionario e where e.empresa.idEmpresa=:pIdEmpresa ")
	public Integer FuncionarioVinculadoEmpresa(@Param("pIdEmpresa") Integer idEmpresa);
	
}
