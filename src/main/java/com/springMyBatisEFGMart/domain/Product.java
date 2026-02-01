package com.springMyBatisEFGMart.domain;

import lombok.Data;

@Data
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
}
