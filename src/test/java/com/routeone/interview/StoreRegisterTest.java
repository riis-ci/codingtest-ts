package com.routeone.interview;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StoreRegisterTest {

	@Rule
    public ExpectedException thrown= ExpectedException.none();


	@Test
	public void goodFileTest() throws Exception {
		StoreRegister register = new StoreRegister();
		loadInventory(register);
	}
	@ignore
	public void badDataTest() throws Exception {
		StoreRegister register = new StoreRegister();
		
		File inventoryFile = File.createTempFile("temp", "csv");
		BufferedWriter wrtr = new BufferedWriter(new FileWriter(inventoryFile));
		wrtr.write("PC1033,20,RAM");
		wrtr.newLine();
		wrtr.write("GenericProcessor,110,CPU");
		wrtr.newLine();
		wrtr.write("ZPC1033,zz,RAM");
		wrtr.newLine();
		wrtr.write("AGenericProcessor,110,CPU");
		wrtr.newLine();
		wrtr.close();
		thrown.expect(NumberFormatException.class);
		register.loadInventory(inventoryFile);
	}
	@Test
	public void incompleteDataTest() throws Exception {
		StoreRegister register = new StoreRegister();
		
		File inventoryFile = File.createTempFile("temp", "csv");
		BufferedWriter wrtr = new BufferedWriter(new FileWriter(inventoryFile));
		wrtr.write("PC1033,20,RAM");
		wrtr.newLine();
		wrtr.write("GenericProcessor");
		wrtr.newLine();
		wrtr.write("ZPC1033,zz,RAM");
		wrtr.newLine();
		wrtr.write("AGenericProcessor,110,CPU");
		wrtr.newLine();
		wrtr.close();
		thrown.expect(InvalidData.class);
		register.loadInventory(inventoryFile);
	}
	@Test
	public void emptyFileThrowsInvalidData() throws Exception {
		StoreRegister register = new StoreRegister();
		
		File inventoryFile = File.createTempFile("temp", "csv");
		thrown.expect(InvalidData.class);
		register.loadInventory(inventoryFile);
	}
	@Test
	public void noFileTest() throws Exception {
		StoreRegister register = new StoreRegister();
		File inventoryFile = new File("");
		thrown.expect(InvalidData.class);
		register.loadInventory(inventoryFile);
	}

	@Test
	public void testCheckoutOrder() throws Exception {
		StoreRegister register = new StoreRegister();
		
		loadInventory(register);
		List<String> itemsBought = new LinkedList<String>();
		itemsBought.add("PC1033");
		itemsBought.add("PC1033");
		itemsBought.add("GenericProcessor");
		itemsBought.add("PC1033");
		
		Receipt rcpt = register.checkoutOrder(itemsBought);
		assertEquals("$170.00", rcpt.getFormattedTotal());
		
	}
	@Test
	public void inventoryfromFile() throws Exception {
		StoreRegister register = new StoreRegister();
		File inventoryFile = new File("sample-inventory.csv");
		register.loadInventory(inventoryFile);
		
		List<String> itemsBought = new LinkedList<String>();
		itemsBought.add("PC1033");
		itemsBought.add("GenericMotherboard");
		itemsBought.add("Mouse");
		itemsBought.add("LCD");
		
		Receipt rcpt = register.checkoutOrder(itemsBought);
		assertEquals("$738.98", rcpt.getFormattedTotal());
		List<String> orderedItems = rcpt.getOrderedItems();
		assertEquals("GenericMotherboard",orderedItems.get(0));
		assertEquals("LCD",orderedItems.get(1));
		assertEquals("Mouse",orderedItems.get(2));
		assertEquals("PC1033",orderedItems.get(3));
	}
	private void loadInventory(StoreRegister register) throws IOException {
		File inventoryFile = File.createTempFile("temp", "csv");
		BufferedWriter wrtr = new BufferedWriter(new FileWriter(inventoryFile));
		wrtr.write("PC1033,20,RAM");
		wrtr.newLine();
		wrtr.write("GenericProcessor,110,CPU");
		wrtr.newLine();
		wrtr.write("ZPC1033,20,RAM");
		wrtr.newLine();
		wrtr.write("AGenericProcessor,110,CPU");
		wrtr.newLine();
		wrtr.close();
		register.loadInventory(inventoryFile);
	}

}
