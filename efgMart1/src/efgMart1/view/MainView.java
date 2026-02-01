package efgMart1.view;

import java.util.Scanner;

public class MainView {
	private Scanner input = new Scanner(System.in);
	//private final String masterID = "admin";
	//private final String masterPW = "1234";
	
	public void mainMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			System.out.println("============ 통합 관리 메뉴 ============");
			System.out.println("1. 회원 관리 | 2. 재고 관리 | 0. 종료");
			System.out.print("메뉴선택>>");
			int menu = Integer.parseInt(input.nextLine());
			
			switch (menu) {
			case 1:
				//new MemberManagementView().mainMenu();
				break;
			case 2:
				new InventoryManagementView().mainMenu();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;

			default:
				break;
			}
		}
		
	}
	
	/*
	public void loginDisplay() {
		System.out.println("===== 로그인 ======");
		System.out.print("아이디 : ");
		String id = input.nextLine();
		System.out.print("패스워드 : ");
		String pw = input.nextLine();

		if (loginCheck(id, pw)) {
			displaySuccess("로그인 성공!");
		} else {
			displayFailed("ID 또는 PW가 틀렸습니다.");
		}
	}

	public boolean loginCheck(String id, String pw) {
		// ID와 PW가 일치하는지 확인 후 결과(true/false)만 반환
		return masterID.equals(id) && masterPW.equals(pw);
	}
	*/
	
	//요청에 대한 응답 메서드
	
		public void displaySuccess(String message) {
			System.out.println(message);
		}

		public void displayFailed(String message) {
			System.out.println(message);
		}
		
		public void displayNoData(String message) {
			System.out.println(message);
		}
	
}
