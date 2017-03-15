	package com.verizontelematics;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EnvironmentsRepository extends CrudRepository<Environments, String> {

	@Query("Select * from environments where name=?0")
	public Environments findByName(String name);

	@Query("Select * from environments where value=?0")
	public List<Environments> findByValue(String value);
}
