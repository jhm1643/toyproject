package net.class101.server1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.class101.server1.dataStore.DataStore;
import net.class101.server1.model.Cart;
import net.class101.server1.service.OrderService;

public class OrderTest {

	@Before
	public void cartBefore() {
		DataStore productDataStore = new DataStore();
		productDataStore.setProductTable();
		productDataStore.showProductTable();
	}
	
	@Test
	public void orderTest() throws Exception{
		OrderService order = new OrderService();
		
		//50000원 이하 결제 시 배송비 5000원 추가 확인
		Cart cart1 = new Cart();
		cart1.setCart(58395, 1);
		order.paymentGo(cart1);
		
		//50000원 이상 결제 시 배송비 추가 안됨
		Cart cart2 = new Cart();
		cart2.setCart(58395, 3);
		order.paymentGo(cart2);
		
		//클래스, 키트 동시 구매 시 결제 확인
		Cart cart3 = new Cart();
		cart3.setCart(58395, 3);
		cart3.setCart(78311, 1);
		order.paymentGo(cart3);
	}
}
