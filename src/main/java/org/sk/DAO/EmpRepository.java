package org.sk.DAO;

import org.sk.MSModel.Employee;

import java.util.List;
import java.util.concurrent.Future;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

public interface EmpRepository extends CrudRepository<Employee,String> {

	//@Async("select id from employee where superid= ?1") 
//	Future<List<Employee>> findIfEmployeeASupervisor(@Param("id") int id);
	
	
}
