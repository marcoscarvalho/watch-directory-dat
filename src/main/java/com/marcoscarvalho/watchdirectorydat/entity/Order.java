package com.marcoscarvalho.watchdirectorydat.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private String saleId;
	private String salesman;
	private List<OrderItem> items = new ArrayList<OrderItem>();
	private BigDecimal totalPrice = BigDecimal.ZERO;

	public Order() {

	}

	public Order(String line) {
		try {
			String[] quebra = line.split("ç");
			this.saleId = quebra[1];
			this.salesman = quebra[3];

			for (String str2 : quebra[2].replace("[", "").replace("]", "").split(",")) {
				items.add(new OrderItem(str2));
			}
			
			for (OrderItem oi : items) {
				this.totalPrice = totalPrice.add(oi.getPrice().multiply(new BigDecimal(oi.getQtd())));
			}
			
		} catch (Exception e) {
			return;
		}
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("saleId: ");
		str.append(saleId);
		str.append(", items: ");
		str.append(items);
		str.append(", salesman: ");
		str.append(salesman);
		str.append(", totalPrice: ");
		str.append(totalPrice);
		return str.toString();
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
