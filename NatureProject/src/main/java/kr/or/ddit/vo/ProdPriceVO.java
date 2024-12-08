package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ProdPriceVO {
	private String prodPriceCode; 
	private int prodPrice;  
	private double prodDiscountRate; 
	private String discountStart; 
	private String discountEnd; 
	private String prodCode;
}
