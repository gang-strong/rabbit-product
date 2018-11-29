package com.example.rabbittest;

public class Test {

	public static void main(String[] args) {
		Runnable able = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("111111111");
			}
		};
		
		Thread t1 = new Thread(able);
		Thread t2 = new Thread(able);
		Thread t3 = new Thread(able);
		Thread t4 = new Thread(able);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
