package br.com.cotiinformatica.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Funcionario;

public interface IFuncionarioRepository extends CrudRepository<Funcionario, Integer>  {
	@Query("select f from Funcionario f where f.cpf=:pCPF ")
	public boolean findByCPF(@Param("pCPF") String cpf);
}
