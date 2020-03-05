package net.class101.server1;

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
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	
}
