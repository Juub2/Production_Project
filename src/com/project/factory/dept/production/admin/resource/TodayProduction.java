package com.project.factory.dept.production.admin.resource;

public class TodayProduction {
	private int todayProductNum;
	private int todayDefectiveNum;
	private String model;
	
	
	public TodayProduction(int todayProductNum, int todayDefectiveNum, String model) {
		this.todayProductNum = todayProductNum;
		this.todayDefectiveNum = todayDefectiveNum;
		this.model = model;
	}

	public TodayProduction() {
		// TODO Auto-generated constructor stub
	}

	public int getTodayProductNum() {
		return todayProductNum;
	}

	public void setTodayProductNum(int todayProductNum) {
		this.todayProductNum = todayProductNum;
	}

	public int getTodayDefectiveNum() {
		return todayDefectiveNum;
	}

	public void setTodayDefectiveNum(int todayDefectiveNum) {
		this.todayDefectiveNum = todayDefectiveNum;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
	
	
}
