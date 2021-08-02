package com.supermarket.CheckOutJavaMaven;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class CheckOutJavaMavenApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(CheckOutJavaMavenApplicationTests.class);

	private Map<String,Items> itemsMap;

	public void init(){
		itemsMap = new HashMap<>();

		Items itemsA = new Items();
		itemsA.setSku("A");
		itemsA.setPrice(50);
		itemsA.setDiscount("Y");
		itemsA.setSpecialUnit(3);
		itemsA.setSpecialPrice(130);
		itemsMap.put("A",itemsA);

		Items itemsB = new Items();
		itemsB.setSku("B");
		itemsB.setPrice(30);
		itemsB.setDiscount("Y");
		itemsB.setSpecialUnit(2);
		itemsB.setSpecialPrice(45);
		itemsMap.put("B",itemsB);

		Items itemsC = new Items();
		itemsC.setSku("C");
		itemsC.setPrice(20);
		itemsC.setDiscount("N");
		itemsC.setSpecialUnit(0);
		itemsC.setSpecialPrice(0);
		itemsMap.put("C",itemsC);
		System.out.println("itemsMap="+itemsMap.entrySet());

	}

	@Test
	public void calculateTotalPriceTest1(){
		init();

		Map<String, Integer> shoppingCart = new HashMap<>();
		// 1 set of special price of item A and B,
		// 1 unit of original price for item C
		shoppingCart.put("A", 3);
		shoppingCart.put("B", 2);
		shoppingCart.put("C", 1);

		PricingRules pricingRules = new PricingRules();
		int actual = pricingRules.calculateTotalPrice(shoppingCart,itemsMap);
		int expected = 195; //130+45+20
		System.out.println("Total amount: "+actual);

		Assert.assertEquals(expected,actual);
	}

	@Test
	public void calculateTotalPriceTest2(){
		init();

		Map<String, Integer> shoppingCart = new HashMap<>();
		// 2 set of special price + 1 unit of original price for item A and B,
		// 1 unit of original price for item C
		shoppingCart.put("A", 4);
		shoppingCart.put("B", 3);
		shoppingCart.put("C", 1);

		PricingRules pricingRules = new PricingRules();
		int actual = pricingRules.calculateTotalPrice(shoppingCart,itemsMap);
		int expected = 275; //130+50+45+30+20
		System.out.println("Total amount: "+actual);

		Assert.assertEquals(expected,actual);
	}

	@Test
	public void calculateTotalPriceTest3(){
		init();

		Map<String, Integer> shoppingCart = new HashMap<>();
		// Zero unit of item A, B and C
		shoppingCart.put("A", 0);
		shoppingCart.put("B", 0);
		shoppingCart.put("C", 0);

		PricingRules pricingRules = new PricingRules();
		int actual = pricingRules.calculateTotalPrice(shoppingCart,itemsMap);
		int expected = 0;
		System.out.println("Total amount: "+actual);

		Assert.assertEquals(expected,actual);
	}

	@Test
	public void calculateTotalPriceTest4(){
		init();

		Map<String, Integer> shoppingCart = new HashMap<>();
		// 2 set of special price for item A and B,
		// 1 unit of original price for item C
		shoppingCart.put("A", 6);
		shoppingCart.put("B", 4);
		shoppingCart.put("C", 1);

		PricingRules pricingRules = new PricingRules();
		int actual = pricingRules.calculateTotalPrice(shoppingCart,itemsMap);
		int expected = 370; // 2x130 + 2x45 + 20
		System.out.println("Total amount: "+actual);

		Assert.assertEquals(expected,actual);
	}
}