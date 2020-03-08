package net.class101.server1.service;

import net.class101.server1.customException.SoldOutException;
import net.class101.server1.dataStore.DataStore;
import net.class101.server1.model.Cart;
import net.class101.server1.model.Product;

public class OrderService {

	public void paymentGo(Cart cart) throws Exception{
		String thread_name = Thread.currentThread().getName();
		if(cart.getCart().size()==0) {
			System.out.println("고객님의 주문 감사합니다.");
			System.exit(0);
		}
		System.out.println("["+thread_name+"]주문 내역 : ");
		System.out.println("----------------------------------------------------------------------");
		int order_totalPrice = 0;
		for(int key:cart.getCart().keySet()) {
			int purchase_count = cart.getCart().get(key);
			Product order_product=null;
			
			synchronized(this) {
				order_product = DataStore.PRODUCT_TABLE.get(key);
				int stockCount = order_product.getStockCount();
				if(stockCount<1) {
					throw new SoldOutException("["+thread_name+"]"+order_product.getProduct_no()+"상품은 이미 품절된 상품입니다.");
				}
				order_product.setStockCount(stockCount-purchase_count);
				DataStore.PRODUCT_TABLE.put(key, order_product);
			}
				int salePrice = order_product.getSalePrice();
				order_totalPrice += purchase_count*salePrice;
			
			System.out.println("["+thread_name+"]"+order_product.getName()+" - "+purchase_count+"개");
		}
		System.out.println("----------------------------------------------------------------------");
		System.out.println("["+thread_name+"]"+"주문금액 : "+order_totalPrice);
		System.out.println("----------------------------------------------------------------------");
		System.out.println("["+thread_name+"]"+"지불금액 : "+(order_totalPrice<50000?(order_totalPrice+5000)+"(배송비 5000원 추가)":order_totalPrice));
		System.out.println("----------------------------------------------------------------------");
	}

}
