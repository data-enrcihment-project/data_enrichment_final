package com.dcommerce.threads;

import com.dcommerce.database.*;
import java.io.IOException;

public class Hybrids extends Thread {

	@Override
	public void run() {

		System.out.println("Hybrids");
		String Statement = "SELECT shop_item.description FROM shop_item INNER JOIN shop_item_has_category "
				+ "ON shop_item.item_no = shop_item_has_category.item_no "
				+ "WHERE shop_item_has_category.category_line_no = 24687 ";
		String File = "data/Hybrids.csv";
		try {
			DatabaseQuery.returnResult(Statement, File);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hybrids End");
	}
}
