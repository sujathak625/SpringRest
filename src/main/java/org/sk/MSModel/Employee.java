package org.sk.MSModel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	String id =null;
	String empname = null;
	int superid = 0;
	float ctc = 0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public int getSuperid() {
		return superid;
	}
	public void setSuperid(int superid) {
		this.superid = superid;
	}
	public float getCtc() {
		return ctc;
	}
	public void setCtc(int ctc) {
		this.ctc = ctc;
	}
	

}
