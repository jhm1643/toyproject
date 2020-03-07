package net.class101.server1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main extends Thread{

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Order order = new Order();
		Product product = new Product();
		while(true) {
			System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
			String order_value = in.readLine();
			if(order_value.equals("q")) {
				order.orderExit();
			}else if(order_value.equals("o")) {
				product.productListShow();
				while(true) {
					try {
						System.out.print("상품번호 : ");
						String product_no_string = in.readLine();
						
						if(product_no_string.equals(" ")) break;
						
						System.out.print("수량 : ");
						String purchase_count = in.readLine();
						order.cartAdd(Integer.parseInt(product_no_string), Integer.parseInt(purchase_count));
					}catch(NumberFormatException e) {
						System.out.println("숫자를 입력해 주세요.");
					}
				}
				new Thread(()->{
					try {
						order.paymentGo();
					}catch(SoldOutException e) {
						e.printStackTrace();
					}
					
				}).run();
				System.exit(0);
			}else {
				System.out.println("o나 q를 입력해주세요.");
			}
		}
	}
	
	
}
