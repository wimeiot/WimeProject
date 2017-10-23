package _03_ShoppingCart.model;

import java.util.*;

public class Order_Bean {
	int ordId;
    int cmId;
	int totalPrice;
	java.sql.Timestamp ordDate;
	String Ord_Destination;
	String buyerName;
	String buyerPhone;
	String buyerEMail;
	String buyerAddress;
	String receiverName;
	String receiverPhone;
	String receiverAddress;
	java.sql.Timestamp outDate;
	int ifOut;
	java.sql.Timestamp arriveDate;
	int ifArrive;
	int ifMember;
	List<OrderDetail_Bean> items = new ArrayList<OrderDetail_Bean>();
	
	public Order_Bean() {

	}

	public Order_Bean(int ordId, int cmId, int totalPrice, java.sql.Timestamp ordDate, String buyerName, String buyerPhone,
			String buyerEMail, String buyerAddress, String receiverName, String receiverPhone, String receiverAddress) {
		super();
		this.ordId = ordId;
		this.cmId = cmId;
		this.totalPrice = totalPrice;
		this.ordDate = new java.sql.Timestamp(ordDate.getTime());
		this.buyerName = buyerName;
		this.buyerPhone = buyerPhone;
		this.buyerEMail = buyerEMail;
		this.buyerAddress = buyerAddress;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
	}



public Order_Bean(int cmId, int totalPrice, java.sql.Timestamp ordDate, String ord_Destination, String buyerName,
			String buyerPhone, String buyerEMail, String buyerAddress, String receiverName, String receiverPhone,
			String receiverAddress, java.sql.Timestamp outDate, int ifOut, java.sql.Timestamp arriveDate, int ifArrive, int ifMember) {
		super();
		this.cmId = cmId;
		this.totalPrice = totalPrice;
		this.ordDate = ordDate;
		Ord_Destination = ord_Destination;
		this.buyerName = buyerName;
		this.buyerPhone = buyerPhone;
		this.buyerEMail = buyerEMail;
		this.buyerAddress = buyerAddress;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
		this.outDate = outDate;
		this.ifOut = ifOut;
		this.arriveDate = arriveDate;
		this.ifArrive = ifArrive;
		this.ifMember = ifMember;
	}

//	public Order_Bean(String userId, int totalPrice, Date ordDate, String buyerName, String buyerPhone,
//			String buyerEMail, String buyerAddress, String receiverName, String receiverPhone, String receiverAddress,
//			Date outDate, int ifOut, Date arriveDate, int ifArrive, int ifMember) {
//		super();
//		this.userId = userId;
//		this.totalPrice = totalPrice;
//		this.ordDate = new java.sql.Date(ordDate.getTime());
//		this.buyerName = buyerName;
//		this.buyerPhone = buyerPhone;
//		this.buyerEMail = buyerEMail;
//		this.buyerAddress = buyerAddress;
//		this.receiverName = receiverName;
//		this.receiverPhone = receiverPhone;
//		this.receiverAddress = receiverAddress;
//		this.outDate = new java.sql.Date(outDate.getTime());
//		this.ifOut = ifOut;
//		this.arriveDate = new java.sql.Date(arriveDate.getTime());
//		this.ifArrive = ifArrive;
//		this.ifMember = ifMember;
//	}

public Order_Bean(int cmId, int totalPrice, java.sql.Timestamp ordDate, String ord_Destination, String buyerName,
		String buyerPhone, String buyerEMail, String buyerAddress, String receiverName, String receiverPhone,
		String receiverAddress, java.sql.Timestamp outDate, int ifOut, java.sql.Timestamp arriveDate, int ifArrive, int ifMember,
		List<OrderDetail_Bean> items) {
	super();
	this.cmId = cmId;
	this.totalPrice = totalPrice;
	this.ordDate = ordDate;
	Ord_Destination = ord_Destination;
	this.buyerName = buyerName;
	this.buyerPhone = buyerPhone;
	this.buyerEMail = buyerEMail;
	this.buyerAddress = buyerAddress;
	this.receiverName = receiverName;
	this.receiverPhone = receiverPhone;
	this.receiverAddress = receiverAddress;
	this.outDate = outDate;
	this.ifOut = ifOut;
	this.arriveDate = arriveDate;
	this.ifArrive = ifArrive;
	this.ifMember = ifMember;
	this.items = items;
}

	

	public int getOrdId() {
		return ordId;
	}

	

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public int getcmId() {
		return cmId;
	}

	public void setcmId(int cmId) {
		this.cmId = cmId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = new java.sql.Timestamp(ordDate.getTime());
	}

	
	
	public String getOrd_Destination() {
		return Ord_Destination;
	}

	public void setOrd_Destination(String ord_Destination) {
		Ord_Destination = ord_Destination;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerEMail() {
		return buyerEMail;
	}

	public void setBuyerEMail(String buyerEMail) {
		this.buyerEMail = buyerEMail;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	
	

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = new java.sql.Timestamp(outDate.getTime());
	}

	public int getIfOut() {
		return ifOut;
	}

	public void setIfOut(int ifOut) {
		this.ifOut = ifOut;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = new java.sql.Timestamp(arriveDate.getTime());
	}

	public int getIfArrive() {
		return ifArrive;
	}

	public void setIfArrive(int ifArrive) {
		this.ifArrive = ifArrive;
	}

	public int getIfMember() {
		return ifMember;
	}

	public void setIfMember(int ifMember) {
		this.ifMember = ifMember;
	}

	public List<OrderDetail_Bean> getItems() {
		return items;
	}

	public void setItems(List<OrderDetail_Bean> items) {
		this.items = items;
	}

}
