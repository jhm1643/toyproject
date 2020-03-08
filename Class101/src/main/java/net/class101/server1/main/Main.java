package net.class101.server1.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import net.class101.server1.dataStore.DataStore;
import net.class101.server1.model.Cart;
import net.class101.server1.service.OrderService;

public class Main{

	public static void shoppingStart(OrderService orderService){
		new Thread(()->{
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				while(true) {
					System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
					String order_value = in.readLine();
					if(order_value.equals("q")) {
						System.out.println("고객님의 주문 감사합니다.");
						System.exit(0);
					}else if(order_value.equals("o")) {
						DataStore productDataStore = new DataStore();
						productDataStore.setProductTable();
						productDataStore.showProductTable();
						Cart cart = new Cart();
						while(true) {
							try {
								System.out.print("상품번호 : ");
								String product_no = in.readLine();
								if(product_no.equals(" ")) {
									break;
								}
								System.out.print("수량 : ");
								String purchase_count = in.readLine();;
								cart.setCart(Integer.parseInt(product_no), Integer.parseInt(purchase_count));
							}catch(NumberFormatException e) {
								System.out.println("숫자를 입력해 주세요.");
							}
						}
						orderService.paymentGo(cart);
						System.exit(0);
					}else {
						System.out.println("o나 q를 입력해주세요.");
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}).start();
	}
	
	public static void main(String[] args){
		OrderService orderService = new OrderService();
		shoppingStart(orderService);
	}
	
	
}
