package com.project.factory.dept.distribution.admin;



public class EmployeeInfo {
	private String id;
	private String name;
	private String assignedRegion;
	private String phoneNum;
	
	

//	public void setMember(Members member) {
//		this.member = member;
//	}
	
	public String getId() {
		return id;
	}
	
//	public void setId(String id) {
//		this.id = id;
//	}
	
	public String getName() {
		return name;
	}
	
//	public void setName(String name) {
//		this.name = name;
//	}
	
	public String getAssignedRegion() {
		return assignedRegion;
	}
	public void setAssignedRegion(String assignedRegion) {
		this.assignedRegion = assignedRegion;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
//	public void setPhoneNum(String phoneNum) {
//		this.phoneNum = phoneNum;
//	}
	public EmployeeInfo(String id, String name, String phoneNum,String assignedRegion) {
		this.id = id;
		this.name = name;
		this.assignedRegion = assignedRegion;
		this.phoneNum = phoneNum;
	}


	
	
}

