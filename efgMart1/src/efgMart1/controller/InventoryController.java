package efgMart1.controller;

import java.util.ArrayList;

import efgMart1.model.vo.Item;
import efgMart1.model.vo.Product;
import efgMart1.service.ItemService;
import efgMart1.service.ProductService;
import efgMart1.view.InventoryManagementView;

public class InventoryController {

	public void selectAllItemList() {
		ArrayList<Item> selectAllItemList = new ItemService().selectAllItemList();
		if (!selectAllItemList.isEmpty()) {
			new InventoryManagementView().displayItemList(selectAllItemList);

		} else {
			new InventoryManagementView().displayNoData("조회된 결과가 없습니다.");
		}
	}

	public void insertItem(String itemId, String modelName, String brand, String category, String color, int shoeSize,
			int price) {

		Item item = new Item(itemId, modelName, brand, category, color, shoeSize, price);
		int result = new ItemService().insertItem(item);

		try {
			if (result > 0) {
				new InventoryManagementView().displaySuccess("품목이 추가되었습니다.");
			} else {
				new InventoryManagementView().displayFailed("품목 추가 실패");
			}
		} catch (Exception e) {
		}

	}

	public void deleteItem(String itemId) {
		ArrayList<Item> list = new ItemService().searchedItemList(itemId);

		if (!list.isEmpty()) {
			new InventoryManagementView().message("정말로 삭제하시겠습니까?(Y/N)");
			String confirm = new InventoryManagementView().getSc().nextLine();

			if (confirm.trim().equalsIgnoreCase("Y")) {
				int result = new ItemService().deleteItem(itemId);
				if (result > 0) {
					new InventoryManagementView().displaySuccess("삭제하였습니다.");
				} else {
					new InventoryManagementView().displayFailed("삭제 실패하였습니다.");
				}
			}
		} else {
			new InventoryManagementView().displayFailed("해당 품목코드로 조회되는 품목이 리스트에 없습니다.");
		}

	}

	public void updateItem(String itemId) {
		ArrayList<Item> allList = new ItemService().selectAllItemList();
		Item updateItem = null;

		// 전체리스트에서 수정하고자 하는 품목의 itemId가 있으면 변수 updateItem에 저장
		for (Item i : allList) {
			if (i.getItemId().equals(itemId)) {
				updateItem = i;
				break;
			}
		}

		if (updateItem != null) {
			System.out.println(updateItem); // 수정하고자 하는 품목의 현재 상태를 보여줌

			try {
				new InventoryManagementView().message("수정 후 가격: ");
				int updatedPrice = Integer.parseInt(new InventoryManagementView().getSc().nextLine().trim());

				new InventoryManagementView().message("수정 후 할인율(ex: 15퍼센트 >> '15'만 입력, ※0~90퍼센트까지만 허용됩니다.): ");
				int discountRate = Integer.parseInt(new InventoryManagementView().getSc().nextLine().trim());

				if (discountRate >= 0 && discountRate < 90) { // 할인율 90 초과는 허용하지 않음
					updateItem.setPrice(updatedPrice);
					updateItem.setDiscountRate(discountRate);

					int result = new ItemService().updateItem(updateItem);
					if (result > 0) {
						new InventoryManagementView().displaySuccess("수정되었습니다.\n");
						System.out.println(updateItem.toString()); // 수정된 데이터의 결과값 보여줌
					} else {
						new InventoryManagementView().displayFailed("수정 실패하였습니다.");
					}
				} else {
					new InventoryManagementView().displayFailed("할인율은 0~90 사이로만 적용 가능합니다.");
				}
			} catch (NumberFormatException e) {
				new InventoryManagementView().displayFailed("숫자만 입력 가능합니다.");
			}

		}
	}

	public void insertStock(String itemId, int amount) {

		// 기존 품목 검색 ArrayList 활용
		ArrayList<Item> allList = new ItemService().selectAllItemList();
		Item searchedItem = null;

		for (Item i : allList) {
			if (i.getItemId().equals(itemId)) {
				searchedItem = i;
				break;
			}
		}

		// 서비스에 재고 추가 요청
		if (searchedItem != null) {
			int result = new ProductService().insertStock(searchedItem, amount);

			if (result > 0) {
				System.out.printf("%s %s %d개 입고 완료\n", searchedItem.getItemId(), searchedItem.getModelName() ,amount);
			} else {
				new InventoryManagementView().displayFailed("입고 실패 (DB 오류 발생)\n");
			}

		} else {
			new InventoryManagementView().displayNoData("존재하지 않는 품목코드 입니다.\n");
		}
	}

	public void selectItemListByBrand() {
		// 1. 브랜드순으로 정렬된 데이터를 DAO에게 요청
		ArrayList<Item> selectItemListByBrand = new ItemService().selectItemListByBrand();

		// 2. 결과가 있는지 확인 후 View 호출
		if (selectItemListByBrand.isEmpty()) {
			new InventoryManagementView().displayNoData("등록된 품목이 없습니다.");
		} else {
			// 기존에 만들어둔 리스트 출력 메서드 재사용!
			new InventoryManagementView().displayItemList(selectItemListByBrand);
		}
	}

	/**
	 * 사용자가 요청한 품목의 정보를 검색하고 찾은값을 리턴
	 * 
	 * @param itemId
	 */
	public void searchItem(String itemId) {
		// Item searchedItem = new Item();
		// searchedItem.setItemId(itemId);

		ArrayList<Item> searchedItemList = new ItemService().searchedItemList(itemId);
		if (!searchedItemList.isEmpty()) {
			new InventoryManagementView().displayItemList(searchedItemList);

		} else {
			new InventoryManagementView().displayNoData("조회된 결과가 없습니다.");
		}
	}

	/**
	 * 사용자가 요청한 품목의 재고의 상세정보를 검색하고 찾은값을 리턴
	 */
	public void productStockStatus(String itemId) {
		// Product searchedProduct = new Product();
		// searchedProduct.setItemId(itemId);

		ArrayList<Product> searchedProductList = new ProductService().searchedProductList(itemId);
		if (searchedProductList != null) {
			new InventoryManagementView().searchedProduct(searchedProductList);

		} else {
			new InventoryManagementView().displayNoData("조회된 결과가 없습니다.");
		}
	}
	

}


























