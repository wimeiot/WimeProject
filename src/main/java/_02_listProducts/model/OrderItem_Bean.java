package _02_listProducts.model;
//本類別封裝單筆訂單資料
public class OrderItem_Bean {
	String name;
	int number;
	String desc;
	int qty = 0 ; 
	int productID = 0 ;
	int price = 0 ; 
	
	
	public OrderItem_Bean(String name, int qty, int productID, int price) {
		super();
		this.name = name;
		this.qty = qty;
		this.productID = productID;
		this.price = price;
	}


	public OrderItem_Bean(String name, int number, int qty, int productID, int price) {
		super();
		this.name = name;
		this.number = number;
		this.qty = qty;
		this.productID = productID;
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public int getProductID() {
		return productID;
	}


	public void setProductID(int productID) {
		this.productID = productID;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}

//	public OrderItemBean(int bookID, int qty, double price, double discount, 
//			String title, String author, String companyName) {
//		this.bookID = bookID;
//		this.qty = qty;
//		this.price = price;
//		this.discount = discount;
//		this.title = title;
//		this.author = author;
//		this.companyName = companyName;
//	}
	
	
}