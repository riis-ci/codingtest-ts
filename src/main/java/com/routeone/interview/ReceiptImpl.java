package com.routeone.interview;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ReceiptImpl implements Receipt {
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

	private Inventory inventory;

	private List<InventoryItem> items = new LinkedList<InventoryItem>();

	public ReceiptImpl(Inventory storeInventory) {
		super();
		this.inventory = storeInventory;
	}

	public String add(String itemName) {
		InventoryItem item = inventory.get(itemName);
		if (null == item)
		{
			throw new InvalidItemException(MessageFormat.format("Sorry but item: {0} is not in inventory", itemName)); 
		}
		items.add(item);
		return getFormattedTotal();

	}

	public String getFormattedTotal() {
		double total = 0;
		for (InventoryItem item : items) {
			total += item.getPrice();
		}
		return currencyFormatter.format(total);
	}

	public List<String> getOrderedItems() {
		Collections.sort(items);
		List<String> exportList = new LinkedList<String>();
		for (InventoryItem item : items) {
			exportList.add(item.getName());	
		}
		return exportList;
	}

}
