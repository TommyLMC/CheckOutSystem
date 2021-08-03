package com.supermarket.CheckOutJavaMaven;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class CheckOutJavaMavenApplication {

	public static void main(String[] args) {

		SpringApplication.run(CheckOutJavaMavenApplication.class, args);
		// Item list file path
		String file = "src\\items.csv";
		Map<String, Items> itemsMap;
		Map<String, Integer> shoppingCart;
		PricingRules pricingRules = new PricingRules();
		int totalAmount = 0;

		// Get the item list
		itemsMap = getMapFromFile(file);
		System.out.println(itemsMap);

		// Get the shopping cart
		shoppingCart = getMapOfShoppingCart(itemsMap);

		// calculate total amount
		if (itemsMap.size() > 0 && shoppingCart.size() > 0)
			totalAmount = pricingRules.calculateTotalPrice(shoppingCart, itemsMap);

		System.out.println("Total amount: " + totalAmount);
	}

	public static Map getMapFromFile(String file) {
		Map<String, Items> itemsMap = new HashMap<>();
		// Load the item list
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			CSVParser csvRecords = CSVParser.parse(reader, CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			for (CSVRecord record : csvRecords) {
				Items items = new Items();
				items.setSku(record.get(0));
				items.setPrice(Integer.parseInt(record.get(1)));
				items.setDiscount(record.get(2));
				items.setSpecialUnit(Integer.parseInt(record.get(3)));
				items.setSpecialPrice(Integer.parseInt(record.get(4)));

				itemsMap.put(record.get(0), items);
			}
			//System.out.println(itemsMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return itemsMap;
	}

	public static Map getMapOfShoppingCart(Map<String, Items> itemsMap) {
		Scanner scanner = new Scanner(System.in);
		String input;
		Map<String, Integer> shoppingCart = new HashMap<>();
		if (itemsMap.size() > 0) {
			while (true) {
				System.out.print("Input item code to continue or [checkout] to quit: ");
				input = scanner.next().toUpperCase();
				// input "CHECKOUT" to quit the scan
				if (input.equals("CHECKOUT"))
					break;
				// check the input item exists in the item list
				if (!itemsMap.containsKey(input)) {
					System.out.println("Invalid item code!");
				} else {
					if (shoppingCart.containsKey(input)) {
						Integer count = shoppingCart.get(input);
						shoppingCart.put(input, count + 1);
					} else {
						shoppingCart.put(input, 1);
					}
				}
			}
		}
		return shoppingCart;
	}
}
