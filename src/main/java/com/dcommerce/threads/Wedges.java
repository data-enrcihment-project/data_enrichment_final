package com.dcommerce.threads;

import com.dcommerce.database.*;
import java.io.IOException;

public class Wedges extends Thread {

	@Override
	public void run() {

		System.out.println("Wedges");
		String Statement = "SELECT shop_item.description FROM shop_item INNER JOIN shop_item_has_category "
				+ "ON shop_item.item_no = shop_item_has_category.item_no "
				+ "WHERE shop_item_has_category.category_line_no = 24843 ";
		String File = "data/Wedges.csv";
		try {
			DatabaseQuery.returnResult(Statement, File);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Wedges End");
	}
}
