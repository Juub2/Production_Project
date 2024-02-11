package com.project.factory.resource;

/**
 * 회원 정보를 나타내는 클래스이다.
 */
public class Members {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String phoneNum;
	private String address;
	private String level;
	private String dept;
	private String email;

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 회원의 직급을 반환하는 메서드이다.
	 * 직급은 관리자(1), 사원(2), 대리점(3)으로 구분된다.
	 * 
	 * @return 회원의 직급
	 */
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDept() {
		return dept;
	}
	
	
	
	public void setDept(String dept) {
		this.dept = dept;
	}
	

	/**
	 * 행정 구역 번호에 따른 구역 이름을 한글로 반환하는 메서드이다.
	 * 
	 * @param dept 행정 구역 번호
	 * @return 행정 구역 한글 이름
	 */
	public Object getArea(String dept) {
		if (getLevel().equals("3")) {
			switch (dept) {
			case "1":
				return "서울특별시";
			case "2":
				return "부산광역시";
			case "3":
				return "대구광역시";
			case "4":
				return "인천광역시";
			case "5":
				return "광주광역시";
			case "6":
				return "대전광역시";
			case "7":
				return "울산광역시";
			case "8":
				return "세종특별자치시";
			case "9":
				return "경기도";
			case "10":
				return "충청북도";
			case "11":
				return "충청남도";
			case "12":
				return "전라남도";
			case "13":
				return "경상북도";
			case "14":
				return "경상남도";
			case "15":
				return "제주특별자치도";
			case "16":
				return "강원특별자치도";
			case "17":
				return "전북특별자치도";
			}
		} else {
			return "대리점 계정이 아닙니다.";
		}
		return "";
	}

	public String getId() {
		return id;
	}

	public String getBirth() {
		return birth;
	}

	public String getEmail() {
		return email;
	}

	 /**
     * Members 클래스의 생성자이다.
     * 
     * @param id       사원번호
     * @param pw       비밀번호
     * @param name     이름
     * @param birth    생년월일
     * @param phoneNum 전화번호
     * @param address  주소
     * @param level    직급
     * @param dept     부서 or 행정 구역 번호
     * @param email    이메일
     */
	// 사원번호■비밀번호■이름■생년월일■전화번호■주소■직급■부서■이메일
	// 대리점ID■비밀번호■이름■전화번호■주소■직급■구역■이메일
	public Members(String id, String pw, String name, String birth, String phoneNum, String address, String level,
			String dept, String email) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.phoneNum = phoneNum;
		this.address = address;
		this.level = level;
		this.dept = dept;
		this.email = email;

	}

	@Override
	public String toString() {
		return super.toString();
	}

	public String getMemberId() {
		// TODO Auto-generated method stub
		return null;
	}





}
