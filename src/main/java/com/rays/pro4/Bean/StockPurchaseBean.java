package com.rays.pro4.Bean;

import java.util.Date;

import com.rays.pro4.Util.DataUtility;

public class StockPurchaseBean extends BaseBean {

	private int quantity;
	private int purchasePrice;
	private Date purchaseDate;
	private String orderType;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public String getkey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return DataUtility.getDateString(purchaseDate);
	}

}