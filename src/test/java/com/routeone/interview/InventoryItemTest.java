package com.routeone.interview;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class InventoryItemTest {

	@Test
	public void sortHighPriceToTop() throws Exception {
		List<InventoryItem> list = new LinkedList<InventoryItem>();
		InventoryItem secondItem = new InventoryItem("last", 100, "Woo Hooo");
		InventoryItem firstItem = new InventoryItem("first", 101, "Woo Hooo");	
		list.add(secondItem);
		list.add(firstItem);
		Collections.sort(list);
		assertEquals(firstItem.getName(), list.get(0).getName());
	}
	@Test
	public void sortAlphabetWhenPriceEqual() throws Exception {
		List<InventoryItem> list = new LinkedList<InventoryItem>();
		InventoryItem secondItem = new InventoryItem("last", 100, "last");
		InventoryItem firstItem = new InventoryItem("first", 100, "first");	
		list.add(secondItem);
		list.add(firstItem);
		Collections.sort(list);
		assertEquals(firstItem.getName(), list.get(0).getName());
	}

}
