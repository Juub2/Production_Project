package com.project.factory.view.dept;

/**
 * 행정 구역 정보를 출력하는 클래스이다.
 */
public class AreaView {
	
	/**
	 * 행정 구역 정보를 출력하는 메서드이다.
	 */
	 public static void areaView() {
	        System.out.println("\t\t\t\t[행정구역 번호]");
	        System.out.println();
	        System.out.println("1. 서울특별시\t2. 부산광역시\t3. 대구광역시\t4. 인천광역시\t5. 광주광역시\t6. 대전광역시");
	        System.out.println("7. 울산광역시\t8. 세종특별자치시\t9. 경기도\t\t10. 충청북도\t11. 충청남도\t12. 전라남도");
	        System.out.println("13. 경상북도\t14. 경상남도\t15. 제주특별자치도\t16. 강원특별자치도\t17.전북특별자치도");
	        System.out.println();
	        System.out.println("행정구역 번호를 입력하세요.");
	    }

}
