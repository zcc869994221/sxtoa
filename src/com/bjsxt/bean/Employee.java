package com.bjsxt.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee {
	private String empid;//员工id
	private String password;//密码
	private String realName;//真实姓名
	private String sex;//性别
	private Date birthDate;//出生日期
	private Date hireDate;//入职日期
	private Date leaveDate;//离职日期
	private int onDuty;//是否在职   0离职，1在职
	private int empType;//员工类型   1.普通员工  2.管理人员 含经理、总监、总裁等  3.管理员 
	private String phone;//联系方式
	private String qq;
	private String emerContactPerson;//紧急联系人
	private String idCard;//身份证号码
	private int deptno;//员工的部门id
	private int posid;//员工的岗位id
	private String  mgrid;//员工的上级id
	
	private Department dept;//员工所属部门
	private Position position;//员工所属的岗位
	private Employee mgr;//员工的上级
	private List<Employee> empList = new ArrayList<Employee>();//员工的下级
	public Employee() {
		super();
	}
	public Employee(String empid, String password, String realName, String sex, Date birthDate, Date hireDate,
			Date leaveDate, int onDuty, int empType, String phone, String qq, String emerContactPerson, String idCard,
			int deptno, int posid, String mgrid, Department dept, Position position, Employee mgr,
			List<Employee> empList) {
		super();
		this.empid = empid;
		this.password = password;
		this.realName = realName;
		this.sex = sex;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		this.leaveDate = leaveDate;
		this.onDuty = onDuty;
		this.empType = empType;
		this.phone = phone;
		this.qq = qq;
		this.emerContactPerson = emerContactPerson;
		this.idCard = idCard;
		this.deptno = deptno;
		this.posid = posid;
		this.mgrid = mgrid;
		this.dept = dept;
		this.position = position;
		this.mgr = mgr;
		this.empList = empList;
	}
	public Employee(String empid, String password, String realName, String sex, Date birthDate, Date hireDate,
			Date leaveDate, int onDuty, int empType, String phone, String qq, String emerContactPerson, String idCard,
			int deptno, int posid, String mgrid) {
		super();
		this.empid = empid;
		this.password = password;
		this.realName = realName;
		this.sex = sex;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		this.leaveDate = leaveDate;
		this.onDuty = onDuty;
		this.empType = empType;
		this.phone = phone;
		this.qq = qq;
		this.emerContactPerson = emerContactPerson;
		this.idCard = idCard;
		this.deptno = deptno;
		this.posid = posid;
		this.mgrid = mgrid;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public int getOnDuty() {
		return onDuty;
	}
	public void setOnDuty(int onDuty) {
		this.onDuty = onDuty;
	}
	public int getEmpType() {
		return empType;
	}
	public void setEmpType(int empType) {
		this.empType = empType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmerContactPerson() {
		return emerContactPerson;
	}
	public void setEmerContactPerson(String emerContactPerson) {
		this.emerContactPerson = emerContactPerson;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getPosid() {
		return posid;
	}
	public void setPosid(int posid) {
		this.posid = posid;
	}
	public String getMgrid() {
		return mgrid;
	}
	public void setMgrid(String mgrid) {
		this.mgrid = mgrid;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Employee getMgr() {
		return mgr;
	}
	public void setMgr(Employee mgr) {
		this.mgr = mgr;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", password=" + password + ", realName=" + realName + ", sex=" + sex
				+ ", birthDate=" + birthDate + ", hireDate=" + hireDate + ", leaveDate=" + leaveDate + ", onDuty="
				+ onDuty + ", empType=" + empType + ", phone=" + phone + ", qq=" + qq + ", emerContactPerson="
				+ emerContactPerson + ", idCard=" + idCard + ", deptno=" + deptno + ", posid=" + posid + ", mgrid="
				+ mgrid + ", dept=" + dept + ", position=" + position + ", mgr=" + mgr + ", empList=" + empList + "]";
	}
	
}
