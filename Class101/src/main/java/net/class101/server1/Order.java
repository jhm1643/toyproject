package net.class101.server1;

import java.util.HashMap;
import java.util.Map;

public class Order {

	private Map<Integer, Integer> cart = new HashMap<Integer, Integer>();
	
	public void cartAdd(int product_no, int purchase_count) {
		Product product = Product.PRODUCT_TABLE.get(product_no);
		if(product==null) {
			System.out.println("존재하는 상품번호를 입력해 주세요.");
			return;
		}
		if(purchase_count < 1) {
			System.out.println("수량은 1개 이상으로 입력해 주세요.");
			return;
		}
		String product_type = product.getType();
		if(product_type.equals("클래스") && purchase_count>1) {
			System.out.println("클래스는 1개만 구매할 수 있습니다.");
			return;
		}
		if(cart.containsKey(product_no)) {
			if(product_type.equals("클래스")) {
				System.out.println("클래스는 1개만 구매할 수 있습니다.");
				return;
			}
			cart.put(product_no, cart.get(product_no)+purchase_count);
		}else {
			cart.put(product_no, purchase_count);
		}
	}

	public void paymentGo() throws SoldOutException{
		if(cart.size()==0) {
			orderExit();
		}
		Product product = new Product();
		int total_salePrice = 0;
		System.out.println("주문 내역 : ");
		System.out.println("----------------------------------------------------------------------");
		for(int key:cart.keySet()) {
			int product_no = key;
			int purchase_count = cart.get(key);
			
			product = Product.PRODUCT_TABLE.get(product_no);
			
			int stockCount = product.getStockCount();
			if(stockCount-purchase_count<0) {
				throw new SoldOutException(product.getProduct_no()+"상품은 이미 품절된 상품입니다.");
			}else{
				product.setStockCount(stockCount-purchase_count);
				Product.PRODUCT_TABLE.put(product_no, product);
			}
			int salePrice = product.getSalePrice();
			total_salePrice += purchase_count*salePrice;
			System.out.println(product.getName()+" - "+purchase_count+"개");
		}
		System.out.println("----------------------------------------------------------------------");
		System.out.println("주문금액 : "+total_salePrice);
		System.out.println("----------------------------------------------------------------------");
		System.out.println("지불금액 : "+(total_salePrice<50000?(total_salePrice+5000)+"(배송비 5000원 추가)":total_salePrice));
		System.out.println("----------------------------------------------------------------------");
		
	}
	
	public void orderExit() {
		System.out.println("고객님의 주문 감사합니다.");
		System.exit(0);
	}
}
