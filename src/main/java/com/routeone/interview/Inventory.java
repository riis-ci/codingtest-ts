package com.routeone.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inventory extends HashMap<String, InventoryItem> {
	private static Logger logger = Logger.getLogger(Inventory.class.getName());
	private static final long serialVersionUID = 1L;

	public Inventory() {
		super();
	}

	public Inventory(BufferedReader inventoryReader) {
		String line = null;
		try {
			while (null != (line = inventoryReader.readLine())) {
				String[] values = line.split(",");
				if (values.length != 3)
					throw new InvalidData(MessageFormat
							.format("Input line : \"{0}\" Does not contain the proper number of arguments", line));
				put(values[0], new InventoryItem(values[0], Double.parseDouble(values[1]), values[2]));
			}
			if (isEmpty()) {
				String logentry = "No inventory to load";
				logger.log(Level.SEVERE, logentry);
				throw new InvalidData(logentry);
			}
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, MessageFormat.format("Line \"{0}\" contained an invalid number", line), e);
			throw e;
		} catch (IOException e) {
			String logentry = "Encountered a general IO exception";
			logger.log(Level.SEVERE, logentry, e);
		}
	}

	public Inventory(Map<String, InventoryItem> inventory) {
		super(inventory);
	}
}