package _03_ShoppingCart.model;

public class OrderDetail_Bean {

	int ordId;
	int productID;
	String productName;
	int amount;
	double unitPrice;
	double subTotal;

	

	public OrderDetail_Bean( int productID, String productName, int amount, double unitPrice, double subTotal) {
		super();
		
		this.productID = productID;
		this.productName = productName;
		this.amount = amount;
		this.unitPrice = unitPrice;
		this.subTotal = subTotal;
	}

	public OrderDetail_Bean() {
		
	}

	

	public int getOrdId() {
		return ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public int getproductId() {
		return productID;
	}

	public void setproductId(int productID) {
		this.productID = productID;
	}



	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	
}