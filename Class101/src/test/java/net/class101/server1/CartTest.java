package net.class101.server1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import net.class101.server1.dataStore.DataStore;
import net.class101.server1.model.Cart;


public class CartTest {

	@Before
	public void cartBefore() {
		DataStore productDataStore = new DataStore();
		productDataStore.setProductTable();
		productDataStore.showProductTable();
	}
	
	
	@Test
	public void cartClassAddTest() {
		//클래스 상품 1개만 구매 가능한지 확인
		Cart cart1 = new Cart();
		cart1.setCart(45947, 2);
		assertNull(cart1.getCart().get(45947));
		Cart cart2 = new Cart();
		cart2.setCart(45947, 1);
		cart2.setCart(45947, 1);
		assertEquals((int)cart2.getCart().get(45947), 1);
		
		//키트상품 및 클래스 등 여러개 상품 구매 확인
		Cart cart3 = new Cart();
		cart3.setCart(58395, 2);
		cart3.setCart(58395, 2);
		cart3.setCart(45947, 1);
		cart3.setCart(45947, 1);
		assertEquals((int)cart3.getCart().get(58395), 4);
		assertEquals((int)cart3.getCart().get(45947), 1);
		
		//존재하지 않는 상품 구매 확인
		Cart cart4 = new Cart();
		cart4.setCart(1234, 1);
		assertNull(cart4.getCart().get(1234));
		
		//1개 이상 구매 가능 확인
		Cart cart5 = new Cart();
		cart5.setCart(58395, 0);
		assertNull(cart5.getCart().get(58395));
		
		//주문 수량이 재고 수량보다 많은 경우 확인
		Cart cart6 = new Cart();
		cart6.setCart(58395, 32);
		assertNull(cart6.getCart().get(58395));
		
	}
	
	
}
