package com.project.factory.resource.sub;

/**
 * 주문서를 나타내는 클래스이다.
 */
public class Order {
	// 주문서
	// 주문서번호■주문서 작성일■대리점명■대리점 주소■전화번호■개수■납기일■모델명
	private String id;
	private String writeDate;
	private String agencyName;
	private String agencyAddress;
	private String agencyPhoneNum;
	private int quantity;
	private String dueDate;
	private String modelId;

	/**
	 * Order 클래스의 생성자이다.
	 * 
	 * @param id             주문서 번호
	 * @param writeDate      주문서 작성일
	 * @param agencyName     대리점명
	 * @param agencyAddress  대리점 주소
	 * @param agencyPhoneNum 대리점 전화번호
	 * @param quantity       수량
	 * @param dueDate        납기일
	 * @param modelId        모델명
	 */
	public Order(String id, String writeDate, String agencyName, String agencyAddress, String agencyPhoneNum,
			int quantity, String dueDate, String modelId) {
		this.id = id;
		this.writeDate = writeDate;
		this.agencyName = agencyName;
		this.agencyAddress = agencyAddress;
		this.agencyPhoneNum = agencyPhoneNum;
		this.quantity = quantity;
		this.dueDate = dueDate;
		this.modelId = modelId;
	}

	/**
	 * 수량을 반환하는 메서드이다.
	 * 
	 * @return 수량
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 수량을 설정하는 메서드이다.
	 * 
	 * @param quantity 수량
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 주문서 번호를 반환하는 메서드이다.
	 * 
	 * @return 주문서 번호
	 */
	public String getId() {
		return id;
	}

	/**
	 * 주문서 작성일을 반환하는 메서드이다.
	 * 
	 * @return 주문서 작성일
	 */
	public String getWriteDate() {
		return writeDate;
	}

	/**
	 * 대리점명을 반환하는 메서드이다.
	 * 
	 * @return 대리점명
	 */
	public String getAgencyName() {
		return agencyName;
	}

	/**
	 * 대리점 주소를 반환하는 메서드이다.
	 * 
	 * @return 대리점 주소
	 */
	public String getAgencyAddress() {
		return agencyAddress;
	}

	/**
	 * 대리점 전화번호를 반환하는 메서드이다.
	 * 
	 * @return 대리점 전화번호
	 */
	public String getAgencyPhoneNum() {
		return agencyPhoneNum;
	}

	/**
	 * 납기일을 반환하는 메서드이다.
	 * 
	 * @return 납기일
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * 모델명을 반환하는 메서드이다.
	 * 
	 * @return 모델명
	 */
	public String getModelId() {
		return modelId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", writeDate=" + writeDate + ", agencyName=" + agencyName + ", agencyAddress="
				+ agencyAddress + ", agencyPhoneNum=" + agencyPhoneNum + ", quantity=" + quantity + ", dueDate="
				+ dueDate + ", modelId=" + modelId + "]";
	}

}
