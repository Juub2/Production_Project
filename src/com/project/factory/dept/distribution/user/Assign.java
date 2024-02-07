package com.project.factory.dept.distribution.user;

import com.project.factory.resource.Members;

public class Assign {

		private String id;
		private String writeDate;
		private String agencyName;
		private String agencyAddress;
		private String agencyPhoneNum;
		private int quantity;
		private String dueDate;
		private String modelId;
		private String userName;
		private String userId;
		private String area;
		private String state = "미완료";
		
		//주문서번호■주문서 작성일■대리점명■대리점 주소■전화번호■개수■납기일■모델명 사원이름 아이디 구역 상태
		public Assign(String id, String writeDate, String agencyName, String agencyAddress, String agencyPhoneNum,
				int quantity, String dueDate, String modelId, String userName, String userId, Object area, String state) {

			this.id = id;
			this.writeDate = writeDate;
			this.agencyName = agencyName;
			this.agencyAddress = agencyAddress;
			this.agencyPhoneNum = agencyPhoneNum;
			this.quantity = quantity;
			this.dueDate = dueDate;
			this.modelId = modelId;
			this.userName = userName;
			this.userId = userId;
			this.area = (String) area;
			this.state = state;
		
			
			
		}
		public String getUserName() {
			return userName;
		}
		public String getArea() {
			return area;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getWriteDate() {
			return writeDate;
		}
		public void setWriteDate(String writeDate) {
			this.writeDate = writeDate;
		}
		public String getAgencyName() {
			return agencyName;
		}
		public void setAgencyName(String agencyName) {
			this.agencyName = agencyName;
		}
		public String getAgencyAddress() {
			return agencyAddress;
		}
		public void setAgencyAddress(String agencyAddress) {
			this.agencyAddress = agencyAddress;
		}
		public String getAgencyPhoneNum() {
			return agencyPhoneNum;
		}
		public void setAgencyPhoneNum(String agencyPhoneNum) {
			this.agencyPhoneNum = agencyPhoneNum;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getDueDate() {
			return dueDate;
		}
		public void setDueDate(String dueDate) {
			this.dueDate = dueDate;
		}
		public String getModelId() {
			return modelId;
		}
		public void setModelId(String modelId) {
			this.modelId = modelId;
		}



		

	    

		/**
		 * 
		 * @param id 주무서 번호
		 * @param writeDate 주문서 작성일
		 * @param agencyName 대리점명
		 * @param agencyAddress 대리점 주소
		 * @param agencyPhoneNum 대리점 전화번호
		 * @param quantity 수량
		 * @param dueDate 납기일
		 * @param modelId 모델명
		 */
//			System.out.println("[주문서 번호]\t[구역]\t[대리점명]\t[모델명]\t[수량]\t[상태]");
//		public Assign(String id, String assignRegion, String agencyName, String modelId,  int quantity, String state) {
//			this.id = id;
//			this.assignRegion = assignRegion;
//			this.agencyName = agencyName;
//			this.modelId = modelId;
//			this.quantity = quantity;
//			
//		}

		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}

	


		






	

}
