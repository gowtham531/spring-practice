package com.restservices.requestdto;

public class Order {
	private String orderId;
	private double amount;
	Product product;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", amount=" + amount + ", product=" + product + "]";
	}
	
	

}
