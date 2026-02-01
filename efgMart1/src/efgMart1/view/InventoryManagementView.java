package efgMart1.view;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import efgMart1.controller.InventoryController;
import efgMart1.model.vo.Item;
import efgMart1.model.vo.Product;

public class InventoryManagementView {
	public Scanner input = new Scanner(System.in);
	private InventoryController ic = new InventoryController();

	public InventoryManagementView() {
	}

	public void mainMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			System.out.println("============= 재고 관리 메뉴 =============");
			System.out.println("1. 품목 추가    2. 품목 전체 리스트   3. 품목 검색");
			System.out.println("4. 품목 삭제    5. 상세 재고 조회     6. 제품 입고");
			System.out.println("7. 품목 수정    8. 브랜드별  리스트   0. 이전 메뉴로");
			System.out.print("원하시는 메뉴 번호를 입력하세요 : ");

			int menu = Integer.parseInt(input.nextLine().trim());
			switch (menu) {
			case 1:
				insertItem();
				break;
			case 2:
				selectAllItemList();
				break;
			case 3:
				searchItem();
				break;
			case 4:
				deleteItem();
				break;
			case 5:
				productStockStatus();
				break;
			case 6:
				insertStock();
				break;
			case 7:
				updateItem();
				break;
			case 8:
				ic.selectItemListByBrand();
				break;	
			case 0:
				System.out.println("이전메뉴로 돌아갑니다.");
				exitFlag = true;
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 올바른 메뉴 번호를 입력하세요");
				break;
			}

		}

	}

	

	// 사용자 요청 화면
	
	public void selectAllItemList() {
		ic.selectAllItemList();
	}

	public void insertItem() {
		System.out.println("============= 품목 추가 =============");
		String brand; // 브랜드 입력
		while (true) {
			System.out.print("브랜드코드: AD(아디다스), NB(뉴발란스), NK(나이키), CV(컨버스), VA(반스), SK(스케쳐스) \n");
			System.out.print("추가할 품목의 브랜드 코드를 입력하세요: ");
			brand = input.nextLine().trim().toUpperCase();
			if (brand.equals("AD") || brand.equals("NB") || brand.equals("NK") || brand.equals("CV")
					|| brand.equals("VA") || brand.equals("SK")) {
				break;
			}
			displayFailed("잘못입력하셨습니다. 올바른 브랜드코드를 입력해주세요.");
		}

		String category; // 분류 입력
		while (true) {
			System.out.print("분류코드: 01(캔버스화), 02(캐쥬얼), 03(스포츠화), 04(로퍼), 05(슬리퍼)" + "06(슬립온), 07(스니커즈), 08(트레이닝) \n");
			System.out.print("추가할 품목의 분류코드를 입력하세요: ");
			category = input.nextLine().trim();
			if (category.equals("01") || category.equals("02") || category.equals("03") || category.equals("04")
					|| category.equals("05") || category.equals("06") || category.equals("07")
					|| category.equals("08")) {
				break;
			}
			System.out.println("잘못입력하셨습니다. 올바른 분류코드를 입력해주세요.");
		}

		String itemNo; // 품목번호 입력
		while (true) {
			System.out.print("품목번호(3자리 정수) : ");
			itemNo = input.nextLine().trim();
			if (itemNo.length() == 3) {
				break;
			}
			System.out.println("잘못입력하셨습니다. 000~999 '3자리 숫자'로 입력하세요.");
		}

		System.out.print("모델명: ");
		String modelName = input.nextLine();

		System.out.print("색상코드 BU(blue), GN(green), RE(red), WH(white), GY(gray), BL(black) \n");
		System.out.print("추가할 품목의 색상코드를 입력하세요: ");
		String color = input.nextLine().toUpperCase();
		
		System.out.print("사이즈: 240, 245, 250, 255, 260, 265, 270, 275, 280, 285, 290 \n 입력 : ");
		System.out.print("추가할 품목의 사이즈를 입력하세요: ");
		int shoeSize = Integer.parseInt(input.nextLine());
		
		System.out.print("가격 : ");
		int price = Integer.parseInt(input.nextLine());

		String itemId = brand + category + itemNo + color + shoeSize;

		ic.insertItem(itemId, modelName, brand, category, color, shoeSize, price);
	}

	public void deleteItem() {
		System.out.println("========= 품목 삭제 =========");
		System.out.print("품목코드 : ");
		String ItemId = input.nextLine();
		ic.deleteItem(ItemId);
	}

	public void updateItem() {
		System.out.println("========= 품목 판매가격 및 할인율 수정 =========");
		System.out.print("수정할 품목코드 : ");
		String ItemId = input.nextLine();
		ic.updateItem(ItemId);
	}

	
	//품목코드앞7자리를 검색하여 해당하는 품목의 정보출력을 요청하는 화면
	//품목코드뒤3자리는 사이즈3자리로 품목코드 앞 7자리만 검색하여 해당 품목의 모든 사이즈를 포함하여 검색되도록
	public void searchItem() {
		System.out.println("========= 품목 검색 =========");
		System.out.printf("검색할 품목코드 앞7자리 : ");
		String itemId = input.nextLine();
		ic.searchItem(itemId);
	}

	private void insertStock() {
		System.out.println("========= 제품 입고 =========");
		System.out.printf("입고할 제품의 품목코드 : ");
		String itemId = input.nextLine();
		System.out.print("입고할 수량 : ");
	    int amount = Integer.parseInt(input.nextLine());
		ic.insertStock(itemId, amount);
	}

	
	//품목코드를 검색하여 해당하는 품목의 재고 상세정보출력을 요청하는 화면
	private void productStockStatus() {
		System.out.println("========= 상세 재고 조회 =========");
		System.out.printf("검색할 재고의 품목코드 : ");
		String itemId = input.nextLine();
		ic.productStockStatus(itemId);
	}

	// 서브 기능 메서드
	public Scanner getSc() {
		return this.input;
	}

	public String formatWithKorean(String str, int size) {
	    if (str == null) str = "";
	    int koreanCount = 0;
	    for (int i = 0; i < str.length(); i++) {
	        if (Character.getType(str.charAt(i)) == Character.OTHER_LETTER) {
	            koreanCount++; // 한글일 경우 카운트 증가
	        }
	    }
	    // 전체 사이즈에서 한글 개수만큼 뺀 너비로 포맷팅
	    return String.format("%-" + (size - koreanCount) + "s", str);
	}
	
	public String getFixedCanvas(String str, int maxSize) {
	    if (str == null) str = "";
	    
	    int currentWidth = 0;
	    for (char c : str.toCharArray()) {
	        // 한글(유니코드 기반)이면 2칸, 아니면 1칸으로 계산
	        if (Character.getType(c) == Character.OTHER_LETTER) {
	            currentWidth += 2;
	        } else {
	            currentWidth += 1;
	        }
	    }
	    
	    // 부족한 너비만큼 공백(스페이스) 추가
	    StringBuilder sb = new StringBuilder(str);
	    while (currentWidth < maxSize) {
	        sb.append(" ");
	        currentWidth++;
	    }
	    
	    return sb.toString();
	}

	
	
	public String getModelCanvas(String str, int maxSize) {
	    if (str == null) str = "";
	    int currentWidth = 0;
	    for (char c : str.toCharArray()) {
	        if (Character.getType(c) == Character.OTHER_LETTER) {
	            currentWidth += 2; // 한글은 2칸
	        } else {
	            currentWidth += 1; // 영문/숫자는 1칸
	        }
	    }

	    StringBuilder sb = new StringBuilder(str);
	    
	    // [보정 포인트] 영문 모델명은 한글보다 픽셀 너비가 좁아서 훨씬 더 많은 공백이 필요해.
	    // matches 정규식에 공백( )과 특수문자(')도 포함되게 수정했어.
	    boolean isOnlyEnglish = str.matches("^[a-zA-Z0-9'\\s]+$");
	    
	    // 만약 영문 모델명이면 maxSize보다 훨씬 넉넉하게(약 6~7칸 더) 보정치를 줘봐.
	    // 네 콘솔에서 990v6가 너무 앞에 있으면 이 숫자를 더 키우고, 너무 뒤에 있으면 줄이면 돼!
	    int targetSize = isOnlyEnglish ? maxSize + 6 : maxSize; 

	    while (currentWidth < targetSize) {
	        sb.append(" ");
	        currentWidth++;
	    }
	    return sb.toString();
	}
	
	// 요청에 대한 응답 메서드

	public void displaySuccess(String message) {
		System.out.println(message);
	}

	public void displayFailed(String message) {
		System.out.println(message);
	}

	public void displayNoData(String message) {
		System.out.println(message);
	}
	
	public void message(String message) {
		System.out.print(message);
	}

	/**
	 * 조회결과가 여러행일때 사용자에게 표시할 화면
	 * 
	 * @param list 조회된 품목정보가 담긴 리스트
	 */

	public void displayItemList(ArrayList<Item> list) {
		System.out.println(
				"========================================= 전체 품목 현황 =========================================");
		// toString의 너비 설정과 1:1로 매칭 (%,10d원 -> 12칸이므로 %-11s 등)
		System.out.printf(" %-10s | %-15s \t|%-3s | %-2s| %-2s | %-4s| %-11s | %-4s| %-4s \n", "품목코드", "모델명", "브랜드",
				"분류", "색상", "사이즈", "가격", "할인율", "재고");
		System.out.println(
				"===============================================================================================");
		for (Item item : list) {
			System.out.println(item.toString());
		}
		System.out.println(
				"===============================================================================================");
		int totalStockCount = 0;
		for (int i = 0; i < list.size(); i++) {
			totalStockCount += list.get(i).getStockCount();
		}
		System.out.printf("[품목 총 개수 : %d개  |   재고 총 개수 : %d개 ]\n", list.size(), totalStockCount);
		System.out.println(
				"===============================================================================================");
	}

	public void displayItem(ArrayList<Item> searchedItemList) {
		System.out.println("================ 해당 품목 조회 결과 ================");
		for (Item data : searchedItemList) {
			System.out.println(data);
		}

	}

	public void displayItemList(Map<String, Item> selectByBrandList) {
		System.out.println("================ 브랜드별 리스트 조회 ================");
		for (Item data : selectByBrandList.values()) {
			System.out.println(data);
		}
	}

	public void searchedProduct(ArrayList<Product> searchedProductList) {
		System.out.println("========================================= 재고 상세 조회 =========================================");
		System.out.println("     시리얼넘버     |     품목코드     |       모델명      | 색상  | 순번  | 판매여부 |   입고날짜   |   판매날짜   |");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		for (Product data : searchedProductList) {
			String model = getFixedCanvas(data.getModelName(), 16); // 모델명 자리를 16칸으로 고정
	        System.out.printf("%-16s | %-14s | %-12s | %-4s | %4d | %6c | %-10s | %s%n",
	            data.getSerialNo(),
	            data.getItemId(),
	            model, 
	            data.getColor(),
	            data.getStockOrder(),
	            data.getIsSold(),
	            data.getInDate(),
	            (data.getOutDate() == null ? " - " : data.getOutDate())
	        );
		}
		System.out.println("===============================================================================================");
	}

}