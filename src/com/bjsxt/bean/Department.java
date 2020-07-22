package com.bjsxt.bean;

import java.io.Serializable;
/**
 * 部门类
 */
public class Department implements Serializable,Comparable<Department>{
	
	private static final long serialVersionUID = 4067208243807288699L;
	private int deptno;//部门编号
	private String deptName;//部门名称
	private String location;//部门地址
	public Department() {
		super();
	}
	
	public Department(int deptno, String deptName, String location) {
		super();
		this.deptno = deptno;
		this.deptName = deptName;
		this.location = location;
	}

	@Override
	public String toString() {
		return "Department [deptno=" + deptno + ", deptName=" + deptName + ", location=" + location + "]";
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public int compareTo(Department other) {
		return this.deptno - other.deptno;
	}
	
}
