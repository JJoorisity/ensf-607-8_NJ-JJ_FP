package server.serverModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import sharedModel.*;

/**
 * Inventory holds an inventory of items and the daily order. Allows the following operations:
 * - generation of daily order id.
 * - printing of daily order and orderlines.
 * - printing of inventory items.
 * - decrement of items being purchased.
 * @author NJack & JJoorisity
 * @version	1.0
 * @since 2020-11-26
 */
public class Inventory {

	private LinkedHashSet<Item> itemList;
	private Order order;

	/**
	 * Constructor. Create an empty LinkedHashSet of items.
	 */
	public Inventory() {
		this.itemList = new LinkedHashSet<Item>();
	}

	/**
	 * @param items (LinkedHashSet<Item>) assign a new LinkedHashSet of items to inventory.
	 */
	public void setItemList(LinkedHashSet<Item> items) {
		this.itemList = items;
	}
	
	/**
	 * @param items (LinkedHashSet<Item>) assign a new LinkedHashSet of items to inventory.
	 */
	public void addItems(Item item) {
		this.itemList.add(item);
	}

	/**
	 * Decrease quantity of item in inventory user input value.
	 * @param item (Item) the item being purchased.
	 * @param qty (int) the quantity of being purchased.
	 * @return (boolean) true if purchase was successful.
	 */
	public boolean decrement(Item item, int qty) {
		if (item == null) {
			return false;
		}
		// check to see if item inventory is high enough to allow purchase.
		if (item.getQty() - qty >= 0) {
			item.setQty(item.getQty() - qty);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Generates unit hash ID based on todays date.
	 * @return (int) 5 digit unique order ID for current day.
	 */
	public int generateOrderID() {
		LocalDate now = LocalDate.now();
		LocalDate epoch = LocalDate.ofEpochDay(0);
		long dateHash = ChronoUnit.DAYS.between(epoch, now);
		final int prime = 31;
		int result = 1;
		result = prime * result + ((int) dateHash);
		return result = result % 100000; // returns between 00000-99999
	}
	
	public String toString() {
		// TO DO: print item list.
		return "";
	}

	public String printOrder() {
		// TO DO: print Order and OrderLines
		return "";
	}
}
