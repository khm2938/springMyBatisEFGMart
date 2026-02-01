package com.springMyBatisEFGMart.domain;

import lombok.Data;

@Data
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
}
    
	
