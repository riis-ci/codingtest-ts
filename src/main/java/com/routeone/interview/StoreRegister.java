package com.routeone.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreRegister {
	private static Logger logger = Logger.getLogger(StoreRegister.class.getName());
	private Inventory storeInventory = null;

	public Receipt checkoutOrder(List<String> items) {
		ReceiptImpl itemsBought = new ReceiptImpl(storeInventory);
		for (String item : items) {
			itemsBought.add(item);
		}
		return itemsBought;
	}

	public void loadInventory(File inventoryFile) {
		Reader rdr = null;
		BufferedReader buff = null;
		try {
			rdr = new FileReader(inventoryFile);
			buff = new BufferedReader(rdr);
			storeInventory = new Inventory(buff);
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE,
					MessageFormat.format("File \"{0}\" was not found", inventoryFile.getAbsolutePath()), e);
			throw new InvalidData(e);
		} finally {
			if (null != buff) {
				try {
					buff.close();
				} catch (IOException e) {
					String logentry = "Encountered a general IO exception";
					logger.log(Level.SEVERE, logentry, e);
				}
			}
			if (null != rdr) {
				try {
					rdr.close();
				} catch (IOException e) {
					String logentry = "Encountered a general IO exception";
					logger.log(Level.SEVERE, logentry, e);
				}
			}
		}
	}

}