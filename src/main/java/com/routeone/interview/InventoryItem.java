package com.routeone.interview;

public class InventoryItem implements Comparable<InventoryItem> {
	private String category;
	private String name;
	private double price;

	public InventoryItem(String name, double d, String category) {
		super();
		setName(name);
		setPrice(d);
		setCategory(category);
	}

	private int compareName(InventoryItem o) {
		return this.getName().compareTo(o.getName());
	}

	private int comparePrice(InventoryItem o) {
		int comparison = 0;
		if (o.getPrice() > this.getPrice()) {
			comparison = 1;
		} else if (o.getPrice() < this.getPrice()) {
			comparison = -1;
		}
		return comparison;
	}

	public int compareTo(InventoryItem o) {

		int comparison = 0;
		if ((comparison = comparePrice(o)) == 0) {
			comparison = compareName(o);
		}
		return comparison;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
