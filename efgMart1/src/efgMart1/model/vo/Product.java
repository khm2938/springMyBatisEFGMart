package efgMart1.model.vo;

import java.util.Objects;

public class Product {
	private String serialNo; //개별 상품 고유 번호 ex) NI01001BU240001 (itemId + stockOrder)
    private String itemId; // Item의 ID를 참조
    private int stockOrder; // 입고 순번
    private String inDate; // 입고날짜
    private String outDate; // 판매날짜
    private char isSold; // 판매여부
    
    private String modelName;
    private String brand;
    private String color;
    private int shoeSize;
    
	public Product() {
	}

	public Product(String serialNo, String itemId, int stockOrder, char isSold) {
		this.itemId = itemId;
		this.stockOrder = stockOrder;
		this.serialNo = this.itemId + String.format("%03d", this.stockOrder);
		this.isSold = isSold;
	}
	
	public Product(String serialNo, String itemId, String modelName, String color, 
			int stockOrder, char isSold, String inDate, String outDate) {
		this.serialNo = serialNo;
		this.itemId = itemId;
		this.modelName = modelName;
		this.color = color;
		this.stockOrder = stockOrder;
		this.isSold = isSold;
		this.inDate = inDate;
		this.outDate = outDate;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getStockOrder() {
		return stockOrder;
	}

	public void setStockOrder(int stockOrder) {
		this.stockOrder = stockOrder;
	}

	

	public char getIsSold() {
		return isSold;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
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

	public void setIsSold(char isSold) {
		this.isSold = isSold;
	}

	@Override
	public String toString() {
		return "[시리얼넘버=%s | 품목코드=%s | 모델명=%s | 색상=%s | 입고순번=%d | 판매여부=%s | 입고날짜=%s | 판매날짜=%s]".formatted(serialNo, itemId, modelName, color, stockOrder, isSold, inDate, outDate);
	}

    @Override
    public boolean equals(Object obj) {
    	if(!(obj instanceof Product)) {
    		return false;
    	}
    	Product temp = (Product)obj;
    	return Objects.equals(temp.getSerialNo(), this.serialNo);
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(this.serialNo);
    }
    
    
    
    
}
