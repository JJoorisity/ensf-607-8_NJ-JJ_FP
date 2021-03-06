package client.clientViews;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.border.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Inventory Management System to create the inventory frame run by the
 * Shop Application.
 * 
 * @author NJack & JJoorisity
 * @version 1.0
 * @since 2020-11-26
 */
public class ImsApplication {
	private final JPanel ImsFrame = new JPanel();
	private final JSplitPane splitPane_rs = new JSplitPane();
	private final JSplitPane splitPane_ls = new JSplitPane();
	private final JPanel rightPanel = new JPanel();
	private final JLabel itemResultLbl = new JLabel("Search Results");
	private JScrollPane itemSearchPane;

	private final JPanel purchasePanel = new JPanel();
	private final JLabel purchaseLbl = new JLabel("Purchase Item");
	private final JPanel purchaseInfoPanel = new JPanel();
	private final JLabel custIdLbl = new JLabel("Customer ID:");
	private final JTextField custIdTxt = new JTextField();
	private final JLabel itemIdLbl = new JLabel("Item ID:");
	private final JTextField itemIdTxt = new JTextField();
	private final JLabel purchaseQtyLbl = new JLabel("Purchase Qty:");
	private final JTextField purchaseQtyTxt = new JTextField();
	private final JLabel pMssgLbl = new JLabel("Purchase Executed:");
	private final JTextField pMssgeTxt = new JTextField();
	private final JButton purchaseBtn = new JButton("Purchase");
	private final JButton clearPurchBtn = new JButton("Clear");
	
	private final JPanel searchItemPane = new JPanel();
	private final JLabel searchItemLbl = new JLabel("Search Item");
	private final JPanel itemSearchPanel = new JPanel();
	private final JLabel itemCriteriaLbl = new JLabel("Select search criteria:");
	private ButtonGroup radioGroup = new ButtonGroup();
	private final JRadioButton itemIdRBtn = new JRadioButton("Item ID");
	private final JRadioButton itemDRBtn = new JRadioButton("Item Description");
	private final JLabel lblNewLabel_1 = new JLabel("Enter search parameters:");
	private final JTextField searchItemTxt = new JTextField();
	private final JButton searchItemBtn = new JButton("Search");
	private final JButton clearItemBtn = new JButton("Clear");
	private final JButton searchAllBtn = new JButton("Search All");
	private DefaultTableModel tableModel = new DefaultTableModel();
	private JTable itemTable;
	private final JButton orderBtn = new JButton("Generate Daily Order");

	/**
	 * Create the application.
	 */
	public ImsApplication() {
		initialize();
	}

	/**
	 * Add action listeners to the buttons
	 * @param listener (ActionListener)
	 */
	public void addActionListeners(ActionListener listener) {
		searchItemBtn.addActionListener(listener);
		searchAllBtn.addActionListener(listener);
		clearItemBtn.addActionListener(listener);
		purchaseBtn.addActionListener(listener);
		clearPurchBtn.addActionListener(listener);
		orderBtn.addActionListener(listener);
	}

	/**
	 * Add selection listeners to the table for selection
	 * @param listener (ListSelectionListener)
	 */
	public void addSelectionListeners(ListSelectionListener listener) {
		itemTable.getSelectionModel().addListSelectionListener(listener);
	}

	/**
	 * @return (JButton) the clear items button.
	 */
	public JButton getClearItemBtn() {
		return this.clearItemBtn;
	}

	/**
	 * @return (JButton) the search items button.
	 */
	public JButton getSearchItemBtn() {
		return this.searchItemBtn;
	}

	/**
	 * @return (JButton) the search all items button.
	 */
	public JButton getSearchAllBtn() {
		return this.searchAllBtn;
	}

	/**
	 * @return (JButton) the purchase items button.
	 */
	public JButton getPurchaseBtn() {
		return this.purchaseBtn;
	}

	/**
	 * @return (JButton) the clear purchases button.
	 */
	public JButton getClearPurchBtn() {
		return this.clearPurchBtn;
	}
	
	/**
	 * @return (JButton) the generate orders button.
	 */
	public JButton getOrderBtn() {
		return this.orderBtn;
	}
	
	/**
	 * @return (JTable) the search results table.
	 */
	public JTable getItemTable() {
		return this.itemTable;
	}

	/**
	 * @return (String) the search items text.
	 */
	public String getSearchItemTxt() {
		return searchItemTxt.getText();
	}

	/**
	 * Set the search item text.
	 * @param set (String) text to display.
	 */
	public void setSearchItemTxt(String set) {
		searchItemTxt.setText(set);
	}

	/**
	 * @return (String) the item ID text.
	 */
	public String getItemIdTxt() {
		return itemIdTxt.getText();
	}

	/**
	 * Set the item ID text.
	 * @param set (String) text to display.
	 */
	public void setItemIdTxt(String set) {
		itemIdTxt.setText(set);
	}

	/**
	 * @return (String) the purchase quantity text.
	 */
	public String getPurchaseQtyTxt() {
		return purchaseQtyTxt.getText();
	}

	/**
	 * Set the purchase quantity text.
	 * @param set (String) text to display.
	 */
	public void setPurchaseQtyTxt(String set) {
		purchaseQtyTxt.setText(set);
	}

	/**
	 * @return (String) the customer ID text.
	 */
	public String getcustIdTxt() {
		return custIdTxt.getText();
	}

	/**
	 * Set the customer ID text.
	 * @param set (String) text to display.
	 */
	public void setcustIdTxt(String set) {
		custIdTxt.setText(set);
	}

	/**
	 * @return (String) the purchase message text.
	 */
	public String getPMssgeTxt() {
		return pMssgeTxt.getText();
	}

	/**
	 * Set the purchase message text.
	 * @param set (String) text to display.
	 */
	public void setPMssgeTxt(String set) {
		pMssgeTxt.setText(set);
	}

	/**
	 * @return (String) the selection from the radio buttons.
	 */
	public String getSelectedRadioButton() {
		return this.radioGroup.getSelection().getActionCommand();
	}

	/**
	 * Display the search results in the table.
	 * @param output (String) string to be added to the table.
	 */
	public void setSearchResultText(String output) {
		String[] split = output.trim().split(",");
		Object[] rowData = {new Integer(Integer.parseInt(split[0])), split[1], new Integer(Integer.parseInt(split[2])), 
				String.format("%.2f", Float.parseFloat(split[3]))};
		this.tableModel.addRow(rowData);
		tableModel.fireTableRowsInserted(0, tableModel.getRowCount());
	}

	/**
	 * Clear the search results table and set rows to 0.
	 */
	public void resetSearchResultText() {
		itemTable.clearSelection();
		this.tableModel.setRowCount(0);
		tableModel.fireTableRowsDeleted(0, 0);;
	}
	
	/**
	 * Get the inventory frame.
	 * @return (JPanel)
	 */
	public JPanel getImsFrame() {
		return this.ImsFrame;
	}

	/**
	 * Initialize the contents of the inventory frame.
	 */
	private void initialize() {
		ImsFrame.setLayout(new BorderLayout(0, 0));
		splitPane_rs.setDividerSize(3);
		splitPane_rs.setBorder(null);

		ImsFrame.add(splitPane_rs);
		splitPane_ls.setDividerSize(3);
		splitPane_ls.setBorder(null);
		splitPane_ls.setOrientation(JSplitPane.VERTICAL_SPLIT);

		splitPane_rs.setLeftComponent(splitPane_ls);
		purchasePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));

		splitPane_ls.setRightComponent(purchasePanel);
		purchasePanel.setLayout(new BorderLayout(0, 0));
		purchaseLbl.setOpaque(true);
		purchaseLbl.setBackground(new Color(0, 51, 102));
		purchaseLbl.setForeground(new Color(255, 255, 255));
		purchaseLbl.setHorizontalAlignment(SwingConstants.CENTER);
		purchaseLbl.setFont(new Font("Tahoma", Font.BOLD, 15));

		purchasePanel.add(purchaseLbl, BorderLayout.NORTH);
		purchaseInfoPanel.setBorder(null);

		custIdTxt.setColumns(10);
		purchasePanel.add(purchaseInfoPanel, BorderLayout.CENTER);
		GridBagLayout gbl_purchaseInfoPanel = new GridBagLayout();
		gbl_purchaseInfoPanel.columnWidths = new int[] { 30, 0, 0, 0, 30 };
		gbl_purchaseInfoPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_purchaseInfoPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		purchaseInfoPanel.setLayout(gbl_purchaseInfoPanel);

		GridBagConstraints gbc_custIdLbl = new GridBagConstraints();
		gbc_custIdLbl.anchor = GridBagConstraints.EAST;
		gbc_custIdLbl.insets = new Insets(0, 0, 5, 5);
		gbc_custIdLbl.gridx = 1;
		gbc_custIdLbl.gridy = 0;
		custIdLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		purchaseInfoPanel.add(custIdLbl, gbc_custIdLbl);

		GridBagConstraints gbc_custIdTxt = new GridBagConstraints();
		gbc_custIdTxt.insets = new Insets(0, 0, 5, 5);
		gbc_custIdTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_custIdTxt.gridx = 2;
		gbc_custIdTxt.gridy = 0;
		purchaseInfoPanel.add(custIdTxt, gbc_custIdTxt);

		GridBagConstraints gbc_itemIdLbl = new GridBagConstraints();
		gbc_itemIdLbl.insets = new Insets(0, 0, 5, 5);
		gbc_itemIdLbl.anchor = GridBagConstraints.EAST;
		gbc_itemIdLbl.gridx = 1;
		gbc_itemIdLbl.gridy = 1;
		itemIdLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		purchaseInfoPanel.add(itemIdLbl, gbc_itemIdLbl);
		itemIdTxt.setColumns(10);

		GridBagConstraints gbc_itemIdTxt = new GridBagConstraints();
		gbc_itemIdTxt.insets = new Insets(0, 0, 5, 5);
		gbc_itemIdTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemIdTxt.gridx = 2;
		gbc_itemIdTxt.gridy = 1;
		purchaseInfoPanel.add(itemIdTxt, gbc_itemIdTxt);

		GridBagConstraints gbc_purchaseQtyLbl = new GridBagConstraints();
		gbc_purchaseQtyLbl.anchor = GridBagConstraints.EAST;
		gbc_purchaseQtyLbl.insets = new Insets(0, 0, 5, 5);
		gbc_purchaseQtyLbl.gridx = 1;
		gbc_purchaseQtyLbl.gridy = 2;
		purchaseQtyLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		purchaseInfoPanel.add(purchaseQtyLbl, gbc_purchaseQtyLbl);
		purchaseQtyTxt.setColumns(10);

		GridBagConstraints gbc_purchaseQtyTxt = new GridBagConstraints();
		gbc_purchaseQtyTxt.insets = new Insets(0, 0, 5, 5);
		gbc_purchaseQtyTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_purchaseQtyTxt.gridx = 2;
		gbc_purchaseQtyTxt.gridy = 2;
		purchaseInfoPanel.add(purchaseQtyTxt, gbc_purchaseQtyTxt);

		GridBagConstraints gbc_clearPurchBtn = new GridBagConstraints();
		gbc_clearPurchBtn.insets = new Insets(0, 0, 5, 5);
		gbc_clearPurchBtn.gridx = 2;
		gbc_clearPurchBtn.gridy = 5;
		clearPurchBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		purchaseInfoPanel.add(clearPurchBtn, gbc_clearPurchBtn);

		GridBagConstraints gbc_pMssgLbl = new GridBagConstraints();
		gbc_pMssgLbl.insets = new Insets(0, 0, 5, 5);
		gbc_pMssgLbl.gridx = 1;
		gbc_pMssgLbl.gridy = 4;
		pMssgLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		purchaseInfoPanel.add(pMssgLbl, gbc_pMssgLbl);
		pMssgeTxt.setBackground(Color.WHITE);
		pMssgeTxt.setEditable(false);
		pMssgeTxt.setColumns(10);

		GridBagConstraints gbc_pMssgeTxt = new GridBagConstraints();
		gbc_pMssgeTxt.insets = new Insets(0, 0, 0, 5);
		gbc_pMssgeTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_pMssgeTxt.gridx = 1;
		gbc_pMssgeTxt.gridy = 5;
		purchaseInfoPanel.add(pMssgeTxt, gbc_pMssgeTxt);

		GridBagConstraints gbc_purchaseBtn = new GridBagConstraints();
		gbc_purchaseBtn.insets = new Insets(0, 0, 0, 5);
		gbc_purchaseBtn.gridx = 2;
		gbc_purchaseBtn.gridy = 3;
		purchaseBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		purchaseInfoPanel.add(purchaseBtn, gbc_purchaseBtn);
		searchItemPane.setBorder(new LineBorder(Color.LIGHT_GRAY));

		splitPane_ls.setLeftComponent(searchItemPane);
		searchItemPane.setLayout(new BorderLayout(0, 0));
		searchItemLbl.setHorizontalAlignment(SwingConstants.CENTER);
		searchItemLbl.setOpaque(true);
		searchItemLbl.setForeground(new Color(255, 255, 255));
		searchItemLbl.setBackground(new Color(0, 51, 102));
		searchItemLbl.setFont(new Font("Tahoma", Font.BOLD, 15));

		searchItemPane.add(searchItemLbl, BorderLayout.NORTH);

		searchItemPane.add(itemSearchPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_itemSearchPanel = new GridBagLayout();
		gbl_itemSearchPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_itemSearchPanel.columnWidths = new int[] { 30, 30, 30, 30, 30 };
		gbl_itemSearchPanel.columnWeights = new double[] { 1.0 };
		gbl_itemSearchPanel.rowWeights = new double[] { Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		itemSearchPanel.setLayout(gbl_itemSearchPanel);

		GridBagConstraints gbc_itemCriteriaLbl = new GridBagConstraints();
		gbc_itemCriteriaLbl.anchor = GridBagConstraints.WEST;
		gbc_itemCriteriaLbl.insets = new Insets(10, 5, 5, 5);
		gbc_itemCriteriaLbl.gridx = 1;
		gbc_itemCriteriaLbl.gridy = 1;
		itemCriteriaLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		itemSearchPanel.add(itemCriteriaLbl, gbc_itemCriteriaLbl);

		GridBagConstraints gbc_itemIdRBtn = new GridBagConstraints();
		gbc_itemIdRBtn.anchor = GridBagConstraints.WEST;
		gbc_itemIdRBtn.insets = new Insets(0, 10, 5, 5);
		gbc_itemIdRBtn.gridx = 1;
		gbc_itemIdRBtn.gridy = 2;
		itemIdRBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		itemSearchPanel.add(itemIdRBtn, gbc_itemIdRBtn);

		GridBagConstraints gbc_itemDRBtn = new GridBagConstraints();
		gbc_itemDRBtn.anchor = GridBagConstraints.WEST;
		gbc_itemDRBtn.insets = new Insets(0, 10, 5, 5);
		gbc_itemDRBtn.gridx = 1;
		gbc_itemDRBtn.gridy = 3;
		itemDRBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		itemSearchPanel.add(itemDRBtn, gbc_itemDRBtn);

		GridBagConstraints gbc_searchAllBtn = new GridBagConstraints();
		gbc_searchAllBtn.insets = new Insets(0, 0, 5, 5);
		gbc_searchAllBtn.gridx = 3;
		gbc_searchAllBtn.gridy = 3;
		searchAllBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		itemSearchPanel.add(searchAllBtn, gbc_searchAllBtn);

		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		itemSearchPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		GridBagConstraints gbc_searchItemBtn = new GridBagConstraints();
		gbc_searchItemBtn.insets = new Insets(0, 0, 5, 5);
		gbc_searchItemBtn.gridx = 3;
		gbc_searchItemBtn.gridy = 2;
		searchItemBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		itemSearchPanel.add(searchItemBtn, gbc_searchItemBtn);
		searchItemTxt.setColumns(10);

		GridBagConstraints gbc_searchItemTxt = new GridBagConstraints();
		gbc_searchItemTxt.insets = new Insets(0, 0, 5, 5);
		gbc_searchItemTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchItemTxt.gridx = 1;
		gbc_searchItemTxt.gridy = 6;
		itemSearchPanel.add(searchItemTxt, gbc_searchItemTxt);

		GridBagConstraints gbc_clearItemBtn = new GridBagConstraints();
		gbc_clearItemBtn.insets = new Insets(0, 0, 5, 5);
		gbc_clearItemBtn.gridx = 3;
		gbc_clearItemBtn.gridy = 6;
		clearItemBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		itemSearchPanel.add(clearItemBtn, gbc_clearItemBtn);
		rightPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));

		splitPane_rs.setRightComponent(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		itemResultLbl.setBackground(new Color(0, 51, 102));
		itemResultLbl.setForeground(new Color(255, 255, 255));
		itemResultLbl.setHorizontalAlignment(SwingConstants.CENTER);
		itemResultLbl.setOpaque(true);
		itemResultLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		rightPanel.add(itemResultLbl, BorderLayout.NORTH);
		itemSearchPane = new JScrollPane();
		itemSearchPane.setBackground(Color.WHITE);
		rightPanel.add(itemSearchPane, BorderLayout.CENTER);
		tableModel.setColumnIdentifiers(new String[] {
				"Item ID", "Item Name", "Quantity", "Price"
			});
		
		itemTable = new JTable(tableModel) {
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		
		// change the alignment
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);
		itemTable.getColumnModel().getColumn(0).setCellRenderer(center);
		itemTable.getColumnModel().getColumn(2).setCellRenderer(center);
		right.setHorizontalAlignment(JLabel.RIGHT);
		itemTable.getColumnModel().getColumn(3).setCellRenderer(right);
		itemTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		itemTable.setBackground(Color.WHITE);
		
		itemSearchPane.setViewportView(itemTable);
		orderBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		rightPanel.add(orderBtn, BorderLayout.SOUTH);

		this.radioGroup.add(itemIdRBtn);
		itemIdRBtn.setActionCommand("itemId");
		itemIdRBtn.setSelected(true); // first radio button should always be selected. No null searches allowed
		this.radioGroup.add(itemDRBtn);
		itemDRBtn.setActionCommand("itemDesc");

	}

}
