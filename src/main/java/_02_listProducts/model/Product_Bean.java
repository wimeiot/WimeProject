package _02_listProducts.model;

import java.sql.Blob;
import java.sql.Clob;

public class Product_Bean {
	private int number; // 產品料號
	private String name; // 產品名稱
	private int productID; // 產品編號
	private int stock; // 庫存數量
	private String desc; // 產品說明
	private int price; // 產品價格
	private String picture; // 產品圖片
	private String softWare; // 軟體下載
	private Clob softDesc; // 軟體說明
	private Blob image; // 產品圖片

	public Product_Bean() {

	}

	public Product_Bean(int number, String name, int productID, int stock, String desc, int price, String picture,
			String softWare, Clob softDesc) {
		super();
		this.number = number;
		this.name = name;
		this.productID = productID;
		this.stock = stock;
		this.desc = desc;
		this.price = price;
		this.picture = picture;
		this.softWare = softWare;
		this.softDesc = softDesc;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getSoftWare() {
		return softWare;
	}

	public void setSoftWare(String softWare) {
		this.softWare = softWare;
	}

	public Clob getSoftDesc() {
		return softDesc;
	}

	public void setSoftDesc(Clob softDesc) {
		this.softDesc = softDesc;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
	
}
