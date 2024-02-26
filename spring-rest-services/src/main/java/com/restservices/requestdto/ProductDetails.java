package com.restservices.requestdto;

import java.util.List;

public class ProductDetails {
	List<Product> similarProducts;

	public List<Product> getSimilarProducts() {
		return similarProducts;
	}

	public void setSimilarProducts(List<Product> similarProducts) {
		this.similarProducts = similarProducts;
	}

	@Override
	public String toString() {
		return "ProductDetails [similarProducts=" + similarProducts + "]";
	}
}