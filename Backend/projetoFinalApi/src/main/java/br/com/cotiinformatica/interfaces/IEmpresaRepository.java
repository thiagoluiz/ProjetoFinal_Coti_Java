package br.com.cotiinformatica.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Empresa;

public interface IEmpresaRepository extends CrudRepository<Empresa, Integer>  {
	@Query("select e from Empresa e where e.cnpj = :pCNPJ")
	public boolean findByCNPJ(@Param("pCNPJ") String cnpj);
	
	@Query("select e from Empresa e where e.razaoSocial=:pRazaoSocial")
	public boolean findByRazaoSocial(@Param("pRazaoSocial") String razaoSocial);
	
	@Query("select e from Empresa e where e.idEmpresa = :pIdEmpresa")
	public Empresa ObterEmpresa(@Param("pIdEmpresa") Integer idEmpresa);

}
