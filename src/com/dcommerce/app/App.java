package com.dcommerce.app;
import com.dcommerce.threads.*;

public class App {
	public static void CallPricing() {
		
		Runnable t1 = new Eisen("22500");
		Thread myThread1 = new Thread(t1);
		myThread1.start();
		

		Runnable t2 = new Driver("23750");
		Thread myThread2 = new Thread(t2);
		myThread2.start();
		

		Runnable t3 = new Fairways("24375");
		Thread myThread3 = new Thread(t3);
		myThread3.start();
		

		Runnable t4 = new Hybrids("24687");
		Thread myThread4 = new Thread(t4);
		myThread4.start();
		

		Runnable t5 = new Wedges("24843");
		Thread myThread5 = new Thread(t5);
		myThread5.start();
		
		
		Runnable t6 = new Putter("24921");
		Thread myThread6 = new Thread(t6);
		myThread6.start();
		
		Runnable t7 = new Accessories("75000");
		Thread myThread7 = new Thread(t7);
		myThread7.start();
		
		
		Runnable t8 = new Bag("30000");
		Thread myThread8 = new Thread(t8);
		myThread8.start();
		

		Runnable t9 = new Golfball("32500");
		Thread myThread9 = new Thread(t9);
		myThread9.start();
		

		Runnable t10 = new GolfoutfitsIhn("65000");
		Thread myThread10 = new Thread(t10);
		myThread10.start();
		

		Runnable t11 = new GolfoutfitsSie("5500");
		Thread myThread11 = new Thread(t11);
		myThread11.start();
		

		Runnable t12 = new GolfschuheDamen("44843");
		Thread myThread12 = new Thread(t12);
		myThread12.start();
		

		Runnable t13 = new GolfschuheHerren("44921");
		Thread myThread13 = new Thread(t13);
		myThread13.start();
		

		Runnable t14 = new Halbsets("28750");
		Thread myThread14 = new Thread(t14);
		myThread14.start();
		

		Runnable t15 = new Komplettsets("27500");
		Thread myThread15 = new Thread(t15);
		myThread15.start();
		

		Runnable t16 = new Handschuhe("42500");
		Thread myThread16 = new Thread(t16);
		myThread16.start();
		

		Runnable t17 = new Messtechnik("35000");
		Thread myThread17 = new Thread(t17);
		myThread17.start();
		

		Runnable t18 = new Schuhpflege("44960");
		Thread myThread18 = new Thread(t18);
		myThread18.start();

	}
}
