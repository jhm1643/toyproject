package net.class101.server1.model;

public class Product {

	private int product_no;
	private String type;
	private String name;
	private int salePrice;
	private int stockCount;
	public Product() {}
	public Product(int product_no, String type, String name, int salePrice, int stockCount) {
		this.product_no=product_no;
		this.type=type;
		this.name=name;
		this.salePrice=salePrice;
		this.stockCount=stockCount;
	}
	public int getProduct_no() {
		return product_no;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCount(int stockCount) {
		this.stockCount=stockCount;
	}
	
}
