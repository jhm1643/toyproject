package net.class101.server1.model;

import java.util.HashMap;
import java.util.Map;

import net.class101.server1.dataStore.DataStore;

public class Cart {

	private Map<Integer, Integer> cart = new HashMap<Integer, Integer>();
	
	public Map<Integer, Integer> getCart(){
		return cart;
	}
	
	public void setCart(int product_no, int purchase_count) {
		Product Choose_product_info = DataStore.PRODUCT_TABLE.get(product_no);
		if(Choose_product_info==null) {
			System.out.println("존재하는 상품번호를 입력해 주세요.");
			return;
		}
		if(purchase_count < 1) {
			System.out.println("수량은 1개 이상으로 입력해 주세요.");
			return;
		}
		if(purchase_count > Choose_product_info.getStockCount() && Choose_product_info.getStockCount()!=0) {
			System.out.println("주문 수량이 재고 수량보다 많습니다.");
			return;
		}
		String product_type = Choose_product_info.getType();
		if(product_type.equals("클래스") && purchase_count>1) {
			System.out.println("클래스는 1개만 구매할 수 있습니다.");
			return;
		}
		if(cart.containsKey(product_no)) {
			if(product_type.equals("클래스")) {
				System.out.println("클래스는 1개만 구매할 수 있습니다.");
				return;
			}
			int total_purchase_count = cart.get(product_no)+purchase_count;
			if(total_purchase_count > Choose_product_info.getStockCount()) {
				System.out.println("주문 수량이 재고 수량보다 많습니다.");
				return;
			}
			cart.put(product_no, total_purchase_count);
		}else {
			cart.put(product_no, purchase_count);
		}
	}
}
