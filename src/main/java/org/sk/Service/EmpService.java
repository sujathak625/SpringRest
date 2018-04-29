package org.sk.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.sk.DAO.EmpRepository;
import org.sk.MSModel.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  

public class EmpService {
	
	@PersistenceContext
    private EntityManager em;
	
	    @Autowired  
	    private EmpRepository empRepository;  
	    public List<Employee> getAllUsers(){  
	        List<Employee> empRec = new ArrayList<>();  
	        empRepository.findAll().forEach(empRec::add);  
	        return empRec;  
	    }  
	    public Employee getUser(String id){  
	        return empRepository.findOne(id);
	    }  
	    public void addOrUpdateEmployee(Employee emp){  
	    	empRepository.save(emp);  
	    }  
	    public void delete(String id){  
	    	empRepository.delete(id);	    
	    }  
	    
	    public void deleteEmployeeIfSupervisor(String id) {
	    	Employee emp = new Employee();
	    	List<Employee> results = new ArrayList<Employee>();
	    	results = findIfEmployeeIsSupervisor(id);
	    	if(results.size()>0) {
	    		Query query = em.createQuery("update employee set superid=0 where superid="+id);
	    	}	    	
	    }
	    
	    public List<Employee> findIfEmployeeIsSupervisor(String id){
	    	Employee emp = new Employee();
	    	String empId = null;
	    	String sql = "select id from employee where superid="+id;
	    	Query query = em.createQuery(sql);
	    	List<Employee> results = query.getResultList();
			return results;
	    }
	    
	    public List<Employee> findEmployeesWhoAreSupervisors(){
	    	Employee emp = new Employee();	    
	    	String sql = "select id,empname from employee where id in (select distinct(superid) from employee)";
	    	Query query = em.createQuery(sql);	    	
	    	List<Employee> results = query.getResultList();
			return results;
	    }
	   
	}  


