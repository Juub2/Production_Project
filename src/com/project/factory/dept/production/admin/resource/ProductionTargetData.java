package com.project.factory.dept.production.admin.resource;

public class ProductionTargetData {
	
	private String modelName;
	private int targetNum;
	
	public ProductionTargetData(int targetNum, String modelName) {
		this.modelName = modelName;
		this.targetNum = targetNum;
	}
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getTargetNum() {
		return targetNum;
	}
	public void setTargetNum(int targetNum) {
		this.targetNum = targetNum;
	}
	
	
}