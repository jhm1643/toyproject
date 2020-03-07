package net.class101.server1;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrderTest {
	
	@Test
	public void test() throws SoldOutException{
		Product product = new Product();
		product.productListSet();
		
		new Thread (() ->{
			System.out.println(1);
			Order order = new Order();
			order.cartAdd(42031, 1);
			try {
				order.paymentGo();
			}catch(SoldOutException e) {
				e.printStackTrace();
			}
		}).run();
		
		new Thread (() ->{
			System.out.println(2);
			Order order = new Order();
			order.cartAdd(42031, 1);
			try {
				order.paymentGo();
			}catch(SoldOutException e) {
				e.printStackTrace();
			}
		}).run();
		new Thread (() ->{
			System.out.println(3);
			Order order = new Order();
			order.cartAdd(42031, 1);
			try {
				order.paymentGo();
			}catch(SoldOutException e) {
				e.printStackTrace();
			}
		}).run();
	}
}
