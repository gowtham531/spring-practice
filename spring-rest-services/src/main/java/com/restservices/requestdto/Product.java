package com.restservices.requestdto;

import java.util.List;

public class Product { 
	 private String prouctId;
     private String name;
     private double price;
     private String brand;
     private String model;
     private List<Product> similarProducts;
	public String getProuctId() {
		return prouctId;
	}
	public void setProuctId(String prouctId) {
		this.prouctId = prouctId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public List<Product> getSimilarProducts() {
		return similarProducts;
	}
	public void setSimilarProducts(List<Product> similarProducts) {
		this.similarProducts = similarProducts;
	}
	@Override
	public String toString() {
		return "Product [prouctId=" + prouctId + ", name=" + name + ", price=" + price + ", brand=" + brand + ", model="
				+ model + ", similarProducts=" + similarProducts + "]";
	}
}