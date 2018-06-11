package com.marcoscarvalho.watchdirectorydat.entity;

import java.math.BigDecimal;

public class OrderItem {

	private String itemId;
	private int qtd;
	private BigDecimal price;

	public OrderItem() {

	}

	public OrderItem(String line) {
		try {
			String[] quebra = line.split("-");
			this.itemId = quebra[0];
			this.qtd = Integer.valueOf(quebra[1]);
			this.price = new BigDecimal(quebra[2]) ;
		} catch (Exception e) {
			return;
		}
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[itemId: ");
		str.append(itemId);
		str.append(", qtd: ");
		str.append(qtd);
		str.append(", price: ");
		str.append(price);
		str.append("]");
		return str.toString();
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


}
