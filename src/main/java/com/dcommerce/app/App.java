package com.dcommerce.app;
import com.dcommerce.threads.*;
import com.dcommerce.threads2.*;

public class App {
	public static void CallPricing() {
		
		Thread t1 = new Eisen();
		Thread t2 = new Driver();
		Thread t3 = new Fairways();
		Thread t4 = new Hybrids();
		Thread t5 = new Wedges();
		Thread t6 = new Putter();
		Thread t7 = new Accessories();
		Thread t8 = new Bag();
		Thread t9 = new Golfball();
		Thread t10 = new GolfoutfitsIhn();
		Thread t11 = new GolfoutfitsSie();
		Thread t12 = new GolfschuheDamen();
		Thread t13 = new GolfschuheHerren();
		Thread t14 = new Halbsets();
		Thread t15 = new Komplettsets();
		Thread t16 = new Handschuhe();
		Thread t17 = new Messtechnik();
		Thread t18 = new Schuhpflege();
		
		
		Thread p1 = new Elektro();
		Thread p2 = new FzgZubehoer();
		Thread p3 = new Grillen();
		Thread p4 = new Haushalt();
		Thread p5 = new Heizen();
		Thread p6 = new Kuehlen();
		Thread p7 = new Moebel();
		Thread p8 = new Outdoor();
		Thread p9 = new Sanitaer();
		Thread p10 = new Zelte();
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t11.start();
		t12.start();
		t13.start();
		t14.start();
		t15.start();
		t16.start();
		t17.start();
		t18.start();
		
		
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		p6.start();
		p7.start();
		p8.start();
		p9.start();
		p10.start();
		
	}

	
}
