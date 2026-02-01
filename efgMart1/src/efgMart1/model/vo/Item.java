package efgMart1.model.vo;

import java.util.Objects;


public class Item {
	private String itemId; // brand(2) + category(2) + 숫자(3) + color(2) + shoeSize(3)> 예: NI01001BU240
	private String modelName; // 모델명 ex) 에어포스 1 '07
    private String brand; // AD: Adidas, NB: NewBalance, NK: Nike, CV: Converse, VA: Vans, SK: Sketchers
    private String category; // 01 : Canvas Shoes, 02 : Casual, 03 : Sports, 04 : Loafers, 
    							  //05 : Slippers, 06 : Slip-on, 07 : Sneakers, 08 : Training
    private String color; // BU:blue, GN:green, RE:red, WH:white, GY:gray, BL:black
    private int shoeSize; // 240, 245, 250, 255, 260, 265, 270, 275, 280, 285, 290
    private int price;
    private int discountRate;
    private int stockCount;
    
    
	public Item() {
		super();
	}
	
	//상품 등록용
	public Item(String itemId, String modelName, String brand, String category, String color, int shoeSize, int price) {
		super();
		this.itemId = itemId;
		this.modelName = modelName;
		this.brand = brand;
		this.category = category;
		this.color = color;
		this.shoeSize = shoeSize;
		this.price = price;
		this.stockCount = 0; //초기 재고는 0으로 초기화
        
	}
		
	//DB SELECT용 생성자
	public Item(String itemId, String modelName, String brand, String category, String color, 
			int shoeSize, int price, int discountRate, int stockCount) {
		this.itemId = itemId;
		this.modelName = modelName;
		this.brand = brand;
		this.category = category;
		this.color = color;
		this.shoeSize = shoeSize;
		this.price = price;
		this.discountRate = discountRate;
		this.stockCount = stockCount;
	}

	

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String style) {
		this.category = style;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(int shoeSize) {
		this.shoeSize = shoeSize;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	
	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	
	//유틸 메서드
	public String getBrandFullname(String brandCode) {
	    switch(brandCode.toUpperCase()) {
	        case "AD": return "Adidas";
	        case "NB": return "NewBalance";
	        case "NK": return "Nike";
	        case "CV": return "Converse";
	        case "VA": return "Vans";
	        case "SK": return "Sketchers";
	        default: return "Unknown";
	    }
	}
	
	@Override
	public String toString() {
	    return " %-12s | %-15s \t| %-4s | %-2s | %-2s | %3dmm | %,10d원 | %3d%% | %4d "
	            .formatted(itemId, modelName, brand, category, color, shoeSize, price, discountRate, stockCount);
	}
	
	/*
	@Override
	public String toString() {
		return "[품목코드=%12s | 브랜드=%2s | 분류=%2s | 색상=%2s | 사이즈=%3dmm | 가격=%,9d원 | 할인율=%2d%% | 재고수량=%4d]"
				.formatted(itemId, brand, category, color, shoeSize, price, discountRate, stockCount);
	}
	*/
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Item)) {
			return false;
		}
		Item temp = (Item) obj;
		return Objects.equals(this.itemId, temp.itemId);
		//return this.getSerialNo().equals(temp.getSerialNo());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.itemId);
	}
    
}
