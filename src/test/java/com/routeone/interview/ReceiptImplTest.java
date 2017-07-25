package com.routeone.interview;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ReceiptImplTest {

	@Rule
    public ExpectedException thrown= ExpectedException.none();

	@Test
	public void formattedValueHas_USD_commas_2_decimal_Places() throws Exception {
		Inventory storeInventory = new Inventory();
		InventoryItem value = new InventoryItem("testItem", 1200, "one");
		storeInventory.put(value.getName(), value);

		value = new InventoryItem("testItem1", 11000, "one");
		storeInventory.put(value.getName(), value);

		value = new InventoryItem("testItem2", 12000, "two");
		storeInventory.put(value.getName(), value);

		value = new InventoryItem("testItem3", 13000, "three");
		storeInventory.put(value.getName(), value);

		value = new InventoryItem("testItem4", 14000, "four");
		storeInventory.put(value.getName(), value);

		value = new InventoryItem("testItem5", 15000, "five");
		storeInventory.put(value.getName(), value);

		value = new InventoryItem("testItem6", 16000, "six");
		storeInventory.put(value.getName(), value);

		ReceiptImpl aReceipt = new ReceiptImpl(storeInventory);
		aReceipt.add("testItem1");//11000
		aReceipt.add("testItem3");//13000
		aReceipt.add("testItem2");//12000
		aReceipt.add("testItem4");//14000
		aReceipt.add("testItem6");//16000

		assertEquals("$66,000.00",aReceipt.getFormattedTotal());
		
	}
	@Test
	public void listOfItemsIsIn_USD_Name_Order() throws Exception {
		Inventory storeInventory = new Inventory();
		InventoryItem value = new InventoryItem("testItem", 1200, "one");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem1", 11.11, "one");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem2", 12.12, "two");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem3", 13.13, "three");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem4b", 14.14, "four");
		storeInventory.put(value.getName(), value);

		value = new InventoryItem("testItem4a", 14.14, "four");
		storeInventory.put(value.getName(), value);

		value = new InventoryItem("testItem4z", 14.14, "four");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem5", 15.15, "five");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem6", 16.00, "six");
		storeInventory.put(value.getName(), value);
		
		ReceiptImpl aReceipt = new ReceiptImpl(storeInventory);
		aReceipt.add("testItem1");
		aReceipt.add("testItem4b");	
		aReceipt.add("testItem3");
		aReceipt.add("testItem4a");
		aReceipt.add("testItem2");
		aReceipt.add("testItem4z");
		aReceipt.add("testItem6");
		
		List<String> itemsOnReciept = aReceipt.getOrderedItems();
		
		assertEquals("testItem6",itemsOnReciept.get(0).split(",")[0]);
		assertEquals("testItem4a",itemsOnReciept.get(1).split(",")[0]);
		assertEquals("testItem4b",itemsOnReciept.get(2).split(",")[0]);
		assertEquals("testItem4z",itemsOnReciept.get(3).split(",")[0]);
		assertEquals("testItem3",itemsOnReciept.get(4).split(",")[0]);
		
	}
	@Test
	public void formattedReallyBigValueHas_USD_commas_2_decimal_Places() throws Exception {
		Inventory storeInventory = new Inventory();
		InventoryItem value = new InventoryItem("testItem", 1200, "one");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem1", 11000000, "one");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem2", 12000000.12, "two");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem3", 13000000, "three");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem4", 14000000, "four");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem5", 15000000, "five");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem6", 16000000.123456789, "six");
		storeInventory.put(value.getName(), value);
		
		ReceiptImpl aReceipt = new ReceiptImpl(storeInventory);
		aReceipt.add("testItem1");
		aReceipt.add("testItem3");
		aReceipt.add("testItem2");
		aReceipt.add("testItem4");
		aReceipt.add("testItem6");
		
		assertEquals("$66,000,000.24",aReceipt.getFormattedTotal());
		
	}
	@Test
	public void invalidItemAddedToListReturnNotFoundError() throws Exception {
		Inventory storeInventory = new Inventory();
		InventoryItem value = new InventoryItem("testItem", 1200, "one");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem1", 11000000, "one");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem2", 12000000.12, "two");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem3", 13000000, "three");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem4", 14000000, "four");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem5", 15000000, "five");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem6", 16000000.123456789, "six");
		storeInventory.put(value.getName(), value);
		
		ReceiptImpl aReceipt = new ReceiptImpl(storeInventory);
		aReceipt.add("testItem1");
		aReceipt.add("testItem3");
		thrown.expect(InvalidItemException.class);
		aReceipt.add("testItemNotFound");
		aReceipt.add("testItem4");
		aReceipt.add("testItem6");
		
		assertEquals("$66,000,000.24",aReceipt.getFormattedTotal());
		
	}
	@Test
	public void formattedZeroValueHas_USD_commas_2_decimal_Places() throws Exception {
		Inventory storeInventory = new Inventory();
		InventoryItem value = new InventoryItem("testItem", 1200, "one");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem1", 11000000, "one");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem2", 12000000.12, "two");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem3", 13000000, "three");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem4", 14000000, "four");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem5", 15000000, "five");
		storeInventory.put(value.getName(), value);
		
		value = new InventoryItem("testItem6", 16000000.123456789, "six");
		storeInventory.put(value.getName(), value);
		
		ReceiptImpl aReceipt = new ReceiptImpl(storeInventory);
		
		assertEquals("$0.00",aReceipt.getFormattedTotal());
		
	}
}
