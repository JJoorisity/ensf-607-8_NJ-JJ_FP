package server.serverControllers;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import sharedModel.*;

// Pre-Project Exercise 

// This program allows you to create and manage a store inventory database.
// It creates a database and data table, then populates that table with tools from
// items.txt.
public class DbControllerHelper implements DatabaseTables {
	
	public DbControllerHelper() {
	}
	
	// Fills the data table with all the tools from the text file 'items.txt' if
	// found
	public ArrayList<Object> importFromTxt(String filename) {
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			Scanner sc = new Scanner(new FileReader(filename));
			while (sc.hasNext()) {
				String fileInfo[] = sc.nextLine().split(";");
				if (filename.equals(ITEMFILE))
					list.add(getItemFromTxt(fileInfo));
				else if (filename.equals(SUPPLIERFILE))
					list.add(getSupplierFromTxt(fileInfo));
				else if (filename.equals(CUSTOMERFILE))
					list.add(getCustomerFromTxt(fileInfo));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + filename + " Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		
	public Item getItemFromTxt(String fileInfo[]) {
		// non-electric item:
		if (fileInfo[5] != "NULL") {
			return new Item_Elec( Integer.parseInt(fileInfo[0]),
					fileInfo[1].charAt(0),
					fileInfo[2],
					Integer.parseInt(fileInfo[3]),
					Double.parseDouble(fileInfo[4]),
					Integer.parseInt(fileInfo[5]),
					fileInfo[6],
					Integer.parseInt(fileInfo[7]),
					Integer.parseInt(fileInfo[8]));
		} else {
			return new Item( Integer.parseInt(fileInfo[0]),
					fileInfo[1].charAt(0),
					fileInfo[2],
					Integer.parseInt(fileInfo[3]),
					Double.parseDouble(fileInfo[4]),
					Integer.parseInt(fileInfo[5]));
		}
	}
		
	public Supplier getSupplierFromTxt(String fileInfo[]) {
		if (fileInfo[5] != "NULL") {
			return new Int_Supplier( Integer.parseInt(fileInfo[0]),
					fileInfo[1].charAt(0),
					fileInfo[2],
					fileInfo[3],
					fileInfo[4],
					Double.parseDouble(fileInfo[5]));
		} else {
			return new Supplier( Integer.parseInt(fileInfo[0]),
					fileInfo[1].charAt(0),
					fileInfo[2],
					fileInfo[3],
					fileInfo[4]);
		}
	}
		
	public Customer getCustomerFromTxt(String fileInfo[]) {
		return new Customer(Integer.parseInt(fileInfo[0]),
											 fileInfo[1],
											 fileInfo[2],
											 fileInfo[3],
											 fileInfo[4],
											 fileInfo[5],
											 fileInfo[6].charAt(0));
	}
	
	public String insert() {
		return ("INSERT INTO $tablename VALUES (?)");
	}
	
	public String updateOrderLine() {
		return ("UPDATE " + ORDER_LINES + " SET orderQty = ? WHERE itemId = ? AND orderId= ?");
	}
	
	public String queryOrderLine() {
		return ("SELECT * FROM " + ORDER_LINES + " WHERE itemId = ? AND orderId = ?");
	}
	
	public String queryOrder() {
		return ("SELECT * FROM " + ORDERS + " WHERE orderId = ?");
	}
}