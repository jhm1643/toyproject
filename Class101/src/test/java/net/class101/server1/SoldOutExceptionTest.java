package net.class101.server1;

import org.junit.Test;

import net.class101.server1.dataStore.DataStore;
import net.class101.server1.model.Cart;
import net.class101.server1.service.OrderService;

public class SoldOutExceptionTest {

	@Test
	public void soldOutExceptionTest() throws Exception{
		
		DataStore productDataStore = new DataStore();
		productDataStore.setProductTable();
		OrderService order = new OrderService();
		
		//1개 씩 구매 시 32번째 고객에서 SoldOutException 확인
		//2개 이상씩 구매할 경우 장바구니에서 조건문에 의해 걸러짐
		for(int i=0;i<32;i++) {
			new Thread(()->{
				try {
					Cart cart = new Cart();
					cart.setCart(58395, 1);
					order.paymentGo(cart);
				}catch(Exception e) {
					e.printStackTrace();
				}
			},"name"+i).start();;
			Thread.sleep(1);
		}
	}
}
